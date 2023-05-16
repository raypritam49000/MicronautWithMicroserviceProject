package com.hotel.service.serviceImpl;

import com.hotel.service.dto.HotelDto;
import com.hotel.service.entity.Hotel;
import com.hotel.service.exceptions.ResourceNotFound;
import com.hotel.service.repository.HotelRepository;
import com.hotel.service.service.HotelService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;
    private ModelMapper modelMapper;

    @Inject
    public void setHotelRepository(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Inject
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        return modelMapper.map(hotelRepository.save(modelMapper.map(hotelDto, Hotel.class)), HotelDto.class);
    }

    @Override
    public HotelDto updateHotel(String hotelId, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFound("Hotel not found with id : " + hotelId));
        hotel.setAbout(hotelDto.getAbout());
        hotel.setName(hotelDto.getName());
        hotel.setLocation(hotelDto.getLocation());
        return modelMapper.map(hotelRepository.update(hotel), HotelDto.class);
    }

    @Override
    public List<HotelDto> getHotels() {
        return hotelRepository.findAll().stream().map(hotel -> modelMapper.map(hotel, HotelDto.class)).collect(Collectors.toList());
    }

    @Override
    public HotelDto getHotel(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFound("Hotel not found with id : " + hotelId));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public void deleteHotel(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFound("Hotel not found with id : " + hotelId));
        hotelRepository.delete(hotel);
    }

}
