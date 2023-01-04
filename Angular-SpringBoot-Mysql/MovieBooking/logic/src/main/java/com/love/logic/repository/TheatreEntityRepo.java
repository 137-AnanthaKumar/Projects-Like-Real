package com.love.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.love.logic.models.TheatreEntity;



@Repository
public interface TheatreEntityRepo extends JpaRepository<TheatreEntity, Integer> {

}
