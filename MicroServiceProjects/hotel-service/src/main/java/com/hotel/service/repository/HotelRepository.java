package com.hotel.service.repository;

import com.hotel.service.entity.Hotel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,String> {
    public List<Hotel> findByUserId(String userId);
}
