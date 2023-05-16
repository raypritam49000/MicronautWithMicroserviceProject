package com.hotel.service.service;

import com.hotel.service.dto.HotelDto;

import java.util.List;

public interface HotelService {
    public HotelDto createHotel(HotelDto hotelDto);
    public HotelDto updateHotel(String hotelId,HotelDto hotelDto);
    public List<HotelDto> getHotels();
    public HotelDto getHotel(String hotelId);
    public void deleteHotel(String hotelId);
}
