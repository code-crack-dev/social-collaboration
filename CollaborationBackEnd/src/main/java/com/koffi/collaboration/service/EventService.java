package com.koffi.collaboration.service;

import java.util.List;

import com.koffi.collaboration.domain.Event;

public interface EventService {

	public boolean addEvent(Event event);

	public boolean deleteEvent(int id);

	public Event getEvent(int id);

	public List<Event> listEvent();
}
