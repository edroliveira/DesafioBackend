package com.desafio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.demo.model.Forecast;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {} 
