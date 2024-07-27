package com.bookMyShow.bookMyShow.Dto;

import com.bookMyShow.bookMyShow.models.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookingRequestDto {
    private int amount;
    private List<Long> seatIds;
    private Long userId;
    private Long showId;
}
