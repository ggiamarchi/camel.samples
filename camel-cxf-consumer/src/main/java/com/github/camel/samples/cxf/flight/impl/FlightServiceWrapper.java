package com.github.camel.samples.cxf.flight.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.camel.samples.cxf.flight.service.wrapper.FindFlightFault;
import com.github.camel.samples.cxf.flight.service.wrapper.FlightEndPoint;
import com.github.camel.samples.cxf.flight.types.Flight;

@Service
public class FlightServiceWrapper implements FlightEndPoint {

	private static Map<String, Flight> flight = new HashMap<String, Flight>();
	
	static {
		Flight f = new Flight();
		f.setFrom("Paris");
		f.setTo("Londres");
		f.setDepartureDate(Calendar.getInstance().getTime());
		f.setArrivalDate(Calendar.getInstance().getTime());
		flight.put("0", f);
	}

	@Override
	public List<Flight> findFlight(String from, String to, Date date) throws FindFlightFault {
		List<Flight> list = new ArrayList<Flight>();
		list.add(flight.get("0"));
		return list;
	}

}
