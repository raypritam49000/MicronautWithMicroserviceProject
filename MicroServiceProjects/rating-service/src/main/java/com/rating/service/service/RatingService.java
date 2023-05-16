package com.rating.service.service;

import com.rating.service.dto.RatingDto;

import java.util.List;


public interface RatingService {

    //create
    RatingDto create(RatingDto rating);


    //get all ratings
    List<RatingDto> getRatings();

    //get all by UserId
    List<RatingDto> getRatingByUserId(String userId);

    //get all by hotel
    List<RatingDto> getRatingByHotelId(String hotelId);


}