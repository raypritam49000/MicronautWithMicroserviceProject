package com.rating.service.serviceImpl;

import com.rating.service.dto.RatingDto;
import com.rating.service.entity.Rating;
import com.rating.service.repository.RatingRepository;
import com.rating.service.service.RatingService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private ModelMapper modelMapper;

    @Inject
    public void setRatingRepository(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Inject
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RatingDto create(RatingDto rating) {
        return modelMapper.map(ratingRepository.save(modelMapper.map(rating, Rating.class)),RatingDto.class);
    }

    @Override
    public List<RatingDto> getRatings() {
        return ratingRepository.findAll()
                .stream()
                .map(rating -> this.modelMapper.map(rating,RatingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId)
                .stream()
                .map(rating -> this.modelMapper.map(rating,RatingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId)
                .stream()
                .map(rating -> this.modelMapper.map(rating,RatingDto.class))
                .collect(Collectors.toList());
    }
}
