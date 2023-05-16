package com.user.service.api.serviceImpl;
import com.user.service.api.dto.HotelDto;
import com.user.service.api.dto.RatingDto;
import com.user.service.api.dto.UserDto;
import com.user.service.api.entity.User;
import com.user.service.api.exceptions.ResourceNotFound;
import com.user.service.api.repository.UserRepository;
import com.user.service.api.service.UserService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;


@Singleton
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RestTemplate restTemplate;

    @Inject
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Inject
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }


    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found with id : " + userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        return modelMapper.map(userRepository.update(user), UserDto.class);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found with id : " + userId));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        RatingDto[] ratingsOfUser = restTemplate.getForObject("http://localhost:7777/api/v1/ratings/getAllRatings/user/" + userDto.getUserId(), RatingDto[].class);
        LOGGER.info("{} ", (Object) ratingsOfUser);
        assert ratingsOfUser != null;
        List<RatingDto> ratings = Arrays.stream(ratingsOfUser).toList();
        List<RatingDto> ratingList = ratings.stream().peek(rating -> {
            ResponseEntity<HotelDto> forEntity = restTemplate.getForEntity("http://localhost:6666/api/v1/hotels/getHotel/"+rating.getHotelId(), HotelDto.class);
            rating.setHotel(forEntity.getBody());
        }).toList();

        userDto.setRatingDtoList(ratingList);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
        return userDtoList.stream().peek(userDto -> {
            RatingDto[] ratingsOfUser = restTemplate.getForObject("http://localhost:7777/api/v1/ratings/getAllRatings/user/" + userDto.getUserId(), RatingDto[].class);
            assert ratingsOfUser != null;
            List<RatingDto> ratingDtoList = Arrays.stream(ratingsOfUser).toList()
                    .stream().peek(ratingDto -> {
                        ResponseEntity<HotelDto> forEntity = restTemplate.getForEntity("http://localhost:6666/api/v1/hotels/getHotel/"+ratingDto.getHotelId(), HotelDto.class);
                        ratingDto.setHotel(forEntity.getBody());
                    }).toList();
            userDto.setRatingDtoList(ratingDtoList);
        }).toList();
    }



    @Override
    public void deleteByUserId(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found with id : " + userId));
        userRepository.delete(user);
    }
}
