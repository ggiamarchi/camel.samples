package com.github.camel.samples.cxf.flight.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.camel.samples.cxf.flight.service.nowrapper.FindFlightFault;
import com.github.camel.samples.cxf.flight.service.nowrapper.FlightEndPoint;
import com.github.camel.samples.cxf.flight.types.FindFlight;
import com.github.camel.samples.cxf.flight.types.FindFlightResponse;
import com.github.camel.samples.cxf.flight.types.Flight;

@Service
public class FlightServiceNoWrapper implements FlightEndPoint {

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
	public FindFlightResponse findFlight(FindFlight in) throws FindFlightFault {
		FindFlightResponse response = new FindFlightResponse();
		response.getFlight().add(flight.get("0"));
		return response;
	}

}
