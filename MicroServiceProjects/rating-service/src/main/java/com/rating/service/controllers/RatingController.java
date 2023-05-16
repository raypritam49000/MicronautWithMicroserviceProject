package com.rating.service.controllers;

import com.rating.service.dto.RatingDto;
import com.rating.service.service.RatingService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;


@Controller("/api/v1/ratings")
public class RatingController {

    private RatingService ratingService;

    @Inject
    public void setRatingService(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Post("/createRating")
    public HttpResponse<RatingDto> createRating(@Body RatingDto ratingDto) {
        return HttpResponse.status(HttpStatus.CREATED).body(ratingService.create(ratingDto));
    }

    @Get("/getAllRatings")
    public HttpResponse<List<RatingDto>> getAllRating() {
        return HttpResponse.status(HttpStatus.OK).status(200).body(ratingService.getRatings());
    }

    @Get("/getAllRatings/user/{userId}")
    public HttpResponse<List<RatingDto>> getAllRatingByUserId(@PathVariable("userId") String userId) {
        return HttpResponse.status(HttpStatus.OK).status(200).body(ratingService.getRatingByUserId(userId));
    }

    @Get("/getAllRatings/hotel/{hotelId}")
    public HttpResponse<List<RatingDto>> getAllRatingByHotelId(@PathVariable("hotelId") String hotelId) {
        return HttpResponse.status(HttpStatus.OK).status(200).body(ratingService.getRatingByHotelId(hotelId));
    }


}