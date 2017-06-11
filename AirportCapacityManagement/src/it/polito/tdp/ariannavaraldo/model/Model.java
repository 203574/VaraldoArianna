package it.polito.tdp.ariannavaraldo.model;

import java.util.List;

import it.polito.tdp.ariannavaraldo.dao.AirportDAO;
import it.polito.tdp.ariannavaraldo.dao.ArrivalDAO;
import it.polito.tdp.ariannavaraldo.dao.DepartureDAO;

public class Model
{
	private ConfigDeparture configDeparture = new ConfigDeparture();
	private ConfigArrival configArrival = new ConfigArrival();
	public List<Airport> getAirports()
	{
		AirportDAO adao = new AirportDAO();
		return adao.getAllAirports();
	}

	public List<Flight> getDepartureFlights(String iata, int month, int day, int from, int to)
	{
		DepartureDAO depdao = new DepartureDAO();
		return depdao.getDepartureFlights(iata, month, day, from, to);
	}
	
	public List<Flight> getArrivalFLights(String iata, int month, int day, int from, int to)
	{
		ArrivalDAO adao = new ArrivalDAO();
		return adao.getArrivalFlights(iata, month, day, from, to);
	}

	public ConfigDeparture getConfigDeparture()
	{
		return configDeparture;
	}

	public ConfigArrival getConfigArrival() {
		return configArrival;
	}
	
}
