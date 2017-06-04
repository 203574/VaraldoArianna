package it.polito.tdp.ariannavaraldo.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Flight {

	private Time departureTime;
	private Time arrivalTime;
	private String carrier;
	private int flightNum;
	private String airport;
	private String airportDescr;
	private int seats;
	private int passengers;
	private boolean nazionale;
	List<PersonDeparture> persons = new ArrayList<PersonDeparture>();
	private String esito = "";
	private FlightStatistics statistics=null;
	
	public class FlightStatistics{
		private Map<Integer, Integer> pieAreasData;

		public Map<Integer, Integer> getPieAreasData() {
			if(pieAreasData == null)
				pieAreasData = new Hashtable<Integer,Integer>();
			return pieAreasData;
		}

	}
	
	public void calculateStatistics(){
		if(persons.size()==0)
			return;
		
		int totCodaCheckIn=0;
		int totCodaSecuruty=0;
		int totDuty=0;
		int totEmbarc=0;
		for(PersonDeparture p:persons){
			try{
			
			if(!isNazionale())
				totCodaCheckIn += p.getArea(Commons.AREA_CHECK_IN).getEnd().getTime()-p.getArea(Commons.AREA_ARRIVO).getEnd().getTime();
			totCodaSecuruty += p.getArea(Commons.AREA_SECURITY).getEnd().getTime()-p.getArea(Commons.AREA_TO_SECURITY).getEnd().getTime();
			totDuty += p.getArea(Commons.AREA_DUTY).getEnd().getTime()-p.getArea(Commons.AREA_DUTY).getStart().getTime();
			totEmbarc += p.getArea(Commons.AREA_EMBARC).getEnd().getTime()-p.getArea(Commons.AREA_EMBARC).getStart().getTime();
			}catch(Exception e){}
		}
		statistics = new FlightStatistics();
		statistics.getPieAreasData().put(1, new Integer(totCodaCheckIn/persons.size()/Commons.MINUTE));
		statistics.getPieAreasData().put(2, new Integer(totCodaSecuruty/persons.size()/Commons.MINUTE));
		statistics.getPieAreasData().put(3, new Integer(totDuty/persons.size()/Commons.MINUTE));
		statistics.getPieAreasData().put(4, new Integer(totEmbarc/persons.size()/Commons.MINUTE));
		
	}

	
	
	public Flight(Time departureTime, Time arrivalTime, String carrier, int flightNum,
			String airport, String airportDescr, int seats, int passengers,
			boolean nazionale) {
		super();
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.carrier = carrier;
		this.flightNum = flightNum;
		this.airport = airport;
		this.airportDescr = airportDescr;
		this.seats = seats;
		this.passengers = passengers;
		this.nazionale = nazionale;
	}
	public boolean isNazionale() {
		return nazionale;
	}
	public void setNazionale(boolean nazionale) {
		this.nazionale = nazionale;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public int getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public int getPassengers() {
		return passengers;
	}

	public Time getDepartureTime() {
		return departureTime;
	}
	public String getAirport() {
		return airport;
	}
	public void setAirport(String airport) {
		this.airport = airport;
	}
	public String getAirportDescr() {
		return airportDescr;
	}
	public void setAirportDescr(String airportDescr) {
		this.airportDescr = airportDescr;
	}
	public List<PersonDeparture> getPersons() {
		return persons;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}



	public FlightStatistics getStatistics() {
		return statistics;
	}
}
