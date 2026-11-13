package com.aeroplaneBooking.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeroplaneBooking.entities.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

}
