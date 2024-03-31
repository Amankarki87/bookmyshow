package com.bookMyShow.bookMyShow.Dto;

import com.bookMyShow.bookMyShow.models.ShowSeatState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowSeatResponseDto {
    private Long showId;
    private Long seatId;
    private ShowSeatState state;
}
