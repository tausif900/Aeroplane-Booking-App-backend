package com.aeroplaneBooking.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeroplaneBooking.entities.PassengerEntity;

public interface PassengerRepository extends JpaRepository<PassengerEntity, Integer>{

}
