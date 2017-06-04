package it.polito.tdp.ariannavaraldo.test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import it.polito.tdp.ariannavaraldo.core.AirportSimulator;
import it.polito.tdp.ariannavaraldo.model.ConfigDeparture;
import it.polito.tdp.ariannavaraldo.model.Flight;

public class TestMain {

	public static void main(String[] args) {
		Calendar instance = Calendar.getInstance();
		int offset = instance.get(Calendar.ZONE_OFFSET);
		Random r = new Random();

		List<Flight> flights = new ArrayList<Flight>();
		for (int i= 0; i < 300; i++){
			int occupancy = r.nextInt(183-120)+120;
			int orap = r.nextInt(20-6)+6;
			int oraa = orap+2;
			int minuto = r.nextInt(59);
			int inter = r.nextInt(100);
			Time departureTime = new Time(1000*60*60*orap+1000*60*minuto-offset); //partenza ore 7:05
			Time arrivalTime = new Time(1000*60*60*oraa+1000*60*minuto-offset); //arrivo ore 9:10
			Flight f = new Flight(departureTime, arrivalTime,
					"AA", i, "LGA","Los Angeles", 183, occupancy, (inter<50?false:true));
			flights.add(f);
		}
		ConfigDeparture config = new ConfigDeparture();
		AirportSimulator simulator = new AirportSimulator(config);
		try {
			simulator.startSimulation(flights);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
