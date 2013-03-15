package com.github.camel.samples.cxf.flight.routes;

import java.util.Date;
import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.camel.samples.cxf.flight.impl.FlightServiceNoWrapper;
import com.github.camel.samples.cxf.flight.impl.FlightServiceWrapper;
import com.github.camel.samples.cxf.flight.service.wrapper.FindFlightFault;
import com.github.camel.samples.cxf.flight.types.Flight;

public class Routes extends RouteBuilder {

	private static final String LOG = "log:" + Routes.class + "?showAll=true&multiline=true";
	
	@Autowired
	private CxfEndpoint fligthEndpointUnWrapped;

	@Autowired
	private CxfEndpoint fligthEndpointWrapped;

	@Autowired
	private FlightServiceWrapper flightServiceUnWrapper;	

	@Autowired
	private FlightServiceNoWrapper flightServiceWrapped;

	@Override
	public void configure() throws Exception {

		from(fligthEndpointWrapped)
		.to(LOG)
		.bean(flightServiceWrapped);

		from(fligthEndpointUnWrapped)
		.to(LOG)
		.bean(new UnWrappedMessageProcessor())
		.to(LOG);

	}
	
	public class UnWrappedMessageProcessor {
		public Object [] call(Object [] params) throws FindFlightFault {
			List<Flight> r = flightServiceUnWrapper.findFlight(
					(String) params[0],
					(String) params[1],
					(Date) params[2]
				);
			return new Object [] { r };			
		}
	}

}
