package com.user.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingDto {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
    private HotelDto hotel;
}
