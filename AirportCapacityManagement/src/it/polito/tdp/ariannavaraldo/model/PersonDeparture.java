package it.polito.tdp.ariannavaraldo.model;

import java.sql.Time;
import java.util.HashMap;
import java.util.Random;

public class PersonDeparture {

	private int number;
	private HashMap<Integer, PersonArea> areas = new HashMap<Integer, PersonArea>();
	private Flight flight;
	private int checkInNumber;
	private int securityCheckNumber;
    //Tempo di anticipo/ritardo rispetto all'orario giusto (1 ora per nazionali, 2 ore per internazionali)
    private int timeAdvance;
    //Tempo impiegato nell'area di arrivo per accedere al banco di checkIn o avviarsi verso la security area
    private int timeInArrivalArea;;
    //Tempo impiegato per fare il checkIn
    private int timeForCheckIn;
    //Tempo impiegato per accedere all'area security
    private int timeToSecurityArea;;
    //Tempo impiegato per fare il securityCheck
    private int timeForSecurityCheck;
    //Tempo impiegato nell'area di imbarco
    private int timeInEmbarc;
    // Tempo consigliato di anticipo rispetto all'orario del volo
    private long estimatedArrivalInAirport;
    private boolean late = false;
    
	public PersonDeparture(int number, Flight flight, ConfigDeparture config) {
		Random r = new Random();
		this.number = number;
		this.flight = flight;
		timeAdvance = (r.nextInt(config.getMaxAdvanceArrival()-config.getMinAdvanceArrival())+config.getMinAdvanceArrival())*Commons.MINUTE;
		timeInArrivalArea = (r.nextInt(config.getMaxTimeInArrivalArea()-config.getMinTimeInArrivalArea())+config.getMinTimeInArrivalArea())*Commons.MINUTE;
		timeForCheckIn = (r.nextInt(config.getMaxTimeForCheckIn()-config.getMinTimeForCheckIn())+config.getMinTimeForCheckIn())*Commons.MINUTE;
		timeToSecurityArea = (r.nextInt(config.getMaxTimeToSecurityArea()-config.getMinTimeToSecurityArea())+config.getMinTimeToSecurityArea())*Commons.MINUTE;
		timeForSecurityCheck = (r.nextInt(config.getMaxTimeForSecurityCheck()-config.getMinTimeForSecurityCheck())+config.getMinTimeForSecurityCheck())*Commons.MINUTE;
		timeInEmbarc = (r.nextInt(config.getMaxTimeInEmbarc()-config.getMinTimeInEmbarc())+config.getMinTimeInEmbarc())*Commons.MINUTE;
		//Per i voli nazionali devo arrivare un'ora prima mentre per gli internazionali 2 ore prima
		int multiplier=1;
		if(!flight.isNazionale())
			multiplier=2;
		estimatedArrivalInAirport = Commons.HOUR*multiplier;
	}

	public HashMap<Integer, PersonArea> getAreas() {
		return areas;
	}

	public PersonArea getArea(int areaId) {
		return areas.get(areaId);
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getMillisInArrivalArea() {
		return timeInArrivalArea;
	}

	public Time getActualArrivalInAirport() {
		return new Time(flight.getDepartureTime().getTime()-estimatedArrivalInAirport-timeAdvance);
	}

	public int getTimeForCheckIn() {
		return timeForCheckIn;
	}

	public int getCheckInNumber() {
		return checkInNumber;
	}

	public void setCheckInNumber(int checkInNumber) {
		this.checkInNumber = checkInNumber;
	}

	public int getTimeToSecurityArea() {
		return timeToSecurityArea;
	}

	public int getTimeForSecurityCheck() {
		return timeForSecurityCheck;
	}

	public void setSecurityCheckNumber(int number) {
		this.securityCheckNumber = number;
	}

	public int getSecurityCheckNumber() {
		return securityCheckNumber;
	}

	public int getTimeInEmbarc() {
		return timeInEmbarc;
	}

	public boolean isLate() {
		return late;
	}

	public void setLate(boolean late) {
		this.late = late;
	}

}
