package it.polito.tdp.ariannavaraldo.model;

import java.util.HashMap;
import java.util.Random;

public class PersonArrival {
	private int number;
	private HashMap<Integer, PersonArea> areas = new HashMap<Integer, PersonArea>();
	private Flight flight;
	private int BaggageReclaimUnitNumber;
    //Minuti impiegati da quando l'aereo atterra a quando il passeggero arriva nella zona arrivi dell'aeroporto
    private int timeInArrivalArea;;
    //Tempo impiegato per ritirare il bagaglio in minuti
    private int timeForBaggageReclaim;
    //Tempo impiegato per accedere all'uscita in minuti
    private int timeToExitArea;
    
	public PersonArrival(int number, Flight flight, ConfigArrival config) {
		Random r = new Random();
		this.number = number;
		this.flight = flight;
		timeInArrivalArea = (r.nextInt(config.getMaxTimeInArrivalArea()-config.getMinTimeInArrivalArea())+config.getMinTimeInArrivalArea())*Commons.MINUTE;
		timeForBaggageReclaim = (r.nextInt(config.getMaxTimeInBaggageArea()-config.getMinTimeInBaggageArea())+config.getMinTimeInBaggageArea())*Commons.MINUTE;
		timeToExitArea = (r.nextInt(config.getMaxTimeInExitArea()-config.getMinTimeInExitArea())+config.getMinTimeInExitArea())*Commons.MINUTE;
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

	public int getBaggageReclaimUnitNumber() {
		return BaggageReclaimUnitNumber;
	}

	public void setBaggageReclaimUnitNumber(int baggageReclaimUnitNumber) {
		BaggageReclaimUnitNumber = baggageReclaimUnitNumber;
	}

	public int getTimeInArrivalArea() {
		return timeInArrivalArea;
	}

	public void setTimeInArrivalArea(int timeInArrivalArea) {
		this.timeInArrivalArea = timeInArrivalArea;
	}

	public int getTimeForBaggageReclaim() {
		return timeForBaggageReclaim;
	}

	public void setTimeForBaggageReclaim(int timeForBaggageReclaim) {
		this.timeForBaggageReclaim = timeForBaggageReclaim;
	}

	public int getTimeToExitArea() {
		return timeToExitArea;
	}

	public void setTimeToExitArea(int timeToExitArea) {
		this.timeToExitArea = timeToExitArea;
	}


}
