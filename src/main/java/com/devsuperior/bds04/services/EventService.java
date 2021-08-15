package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.Repositories.CityRepository;
import com.devsuperior.bds04.Repositories.EventRepository;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	@Autowired
	private CityRepository cityRepository;

	@Transactional(readOnly = true)
	public Page<EventDTO> findAllPaged(Pageable pageable) {
		Page<Event> page = repository.findAll(pageable);
		return page.map(x -> new EventDTO(x));
	}

	public EventDTO insert(EventDTO dto) {

		Event event = new Event();

		setEntityWithDTO(dto, event);

		event = repository.save(event);

		return new EventDTO(event);

	}

	@Transactional
	public void setEntityWithDTO(EventDTO dto, Event entity) {

		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());

		City city = cityRepository.findById(dto.getCityId()).get();

		entity.setCity(city);

	}

}
