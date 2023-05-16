package com.hotel.service.controllers;

import com.hotel.service.dto.HotelDto;
import com.hotel.service.service.HotelService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

@Controller("/api/v1/hotels")
public class HotelController {

    private HotelService hotelService;

    @Inject
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Get(uri = "/", produces = "text/plain")
    public String index() {
        return "Example Response";
    }

    @Post("/createHotel")
    public HotelDto createHotel(@Body HotelDto hotelDto) {
        return hotelService.createHotel(hotelDto);
    }

    @Put("/updateHotel/{hotelId}")
    public HotelDto updateHotel(@PathVariable("hotelId") String hotelId, @Body HotelDto hotelDto) {
        return hotelService.updateHotel(hotelId, hotelDto);
    }

    @Get("/getHotel/{hotelId}")
    public HotelDto getHotel(@PathVariable("hotelId") String hotelId) {
        return hotelService.getHotel(hotelId);
    }

    @Get("/getHotels")
    public List<HotelDto> getHotels() {
        return hotelService.getHotels();
    }

    @Delete(uri = "/deleteHotels/{hotelId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<?> deleteHotel(@PathVariable("hotelId") String hotelId) {
        hotelService.deleteHotel(hotelId);
        return HttpResponse.status(HttpStatus.OK).status(200).body(Map.of("success",true,"status","Success","message","hotel has been deleted"));
    }


}