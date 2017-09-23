package com.harish.car.tracker.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.harish.car.tracker.entity.Reading;
import com.harish.car.tracker.entity.Tires;

public interface TiresRepository extends Repository<Tires, String>{

	public Tires save(Tires t);


}
