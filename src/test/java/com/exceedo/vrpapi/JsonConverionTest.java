package com.exceedo.vrpapi;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.exceedo.vrpapi.domain.RouteType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverionTest {

	@Test
	public void testRouteTypeConversion() {

		RouteType routeType = new RouteType();
		routeType.setId(1);
		routeType.setName("Truck");
		routeType.setMinValue(0);
		routeType.setMaxValue(25);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonString = mapper.writeValueAsString(routeType);
			System.out.println(jsonString);
			RouteType fromString = mapper.readValue(jsonString, RouteType.class);
			assertNotNull(jsonString);
			assertEquals(fromString, routeType);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
