package com.devsuperior.bds04.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.Repositories.CityRepository;
import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;

	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}

	public CityDTO insert(CityDTO dto) {

		City city = new City();

		setEntityWithDTO(dto, city);

		city = repository.save(city);

		return new CityDTO(city);

	}

	public void setEntityWithDTO(CityDTO dto, City entity) {

		entity.setName(dto.getName());

	}

}
