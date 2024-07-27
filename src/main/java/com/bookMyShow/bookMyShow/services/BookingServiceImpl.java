package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.ErrorMessages;
import com.bookMyShow.bookMyShow.exceptions.ElementNotFoundException;
import com.bookMyShow.bookMyShow.models.*;
import com.bookMyShow.bookMyShow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 2)
    public Booking createBooking(
            List<Long> seatIds,
            Long showId,
            Long userId,
            int amount
    ) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ElementNotFoundException(ErrorMessages.USER_DOES_NOT_EXIST);
        }

        List<Seat> seats = seatRepository.findAllByIdIn(seatIds);

        if (seats.isEmpty()) {
            throw new ElementNotFoundException(ErrorMessages.NO_SEAT_FOUND);
        }

        Optional<Show> showOptional = showRepository.findById(showId);

        if (showOptional.isEmpty()) {
            throw new ElementNotFoundException(ErrorMessages.SHOW_DOES_NOT_EXIST);
        }

        getAndLockShowSeats(seatIds,showOptional.get());

        Booking booking = new Booking();
        booking.setAmount(amount);
        booking.setSeats(seats);
        booking.setShow(showOptional.get());
        booking.setBookedBy(user.get());
        booking.setTicketStatus(TicketStatus.PROCESSING);

        List<Payment> payments = new ArrayList<>();
        payments.add(Payment.ONLINE);
        booking.setPayment(payments);

        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 2)
    private List<ShowSeat> getAndLockShowSeats(List<Long> seatIds, Show show) {
        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatInAndShow(seatIds, show);

        for (ShowSeat showSeat: showSeats) {
            if (!(showSeat.getState().equals(ShowSeatState.AVAILABLE))) {
                throw new ElementNotFoundException(ErrorMessages.SEAT_NOT_AVAILABLE);
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for (ShowSeat showSeat: showSeats) {
            showSeat.setState(ShowSeatState.LOCKED);
            showSeat.setLockedAt(new Date());
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        return showSeats;
    }
}
