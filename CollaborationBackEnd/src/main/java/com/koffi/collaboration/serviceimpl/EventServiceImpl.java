package com.koffi.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.EventDAO;
import com.koffi.collaboration.domain.Event;
import com.koffi.collaboration.service.EventService;


@Transactional
@Service
public class EventServiceImpl implements EventService{

	@Autowired
	EventDAO eventDAO;
	public boolean addEvent(Event event) {
		// TODO Auto-generated method stub
		return eventDAO.addEvent(event);
	}

	public boolean deleteEvent(int id) {
		// TODO Auto-generated method stub
		return eventDAO.deleteEvent(id);
	}

	public Event getEvent(int id) {
		// TODO Auto-generated method stub
		return eventDAO.getEvent(id);
	}

	public List<Event> listEvent() {
		// TODO Auto-generated method stub
		return eventDAO.listEvent();
	}

}
