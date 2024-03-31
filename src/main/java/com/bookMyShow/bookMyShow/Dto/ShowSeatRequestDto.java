package com.bookMyShow.bookMyShow.Dto;

import com.bookMyShow.bookMyShow.models.ShowSeatState;
import lombok.Data;

@Data
public class ShowSeatRequestDto {
    private Long showId;
    private Long seatId;
    private ShowSeatState state;
}
