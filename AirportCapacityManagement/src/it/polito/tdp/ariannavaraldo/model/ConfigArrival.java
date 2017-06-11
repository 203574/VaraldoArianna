package it.polito.tdp.ariannavaraldo.model;

public class ConfigArrival {

	private int numBaggageReclaim = 20;

	private int timeReopenBaggageReclaim = 20;

	private int minTimeInArrivalArea = 15;
	private int maxTimeInArrivalArea = 20;
	
	private int minTimeInExitArea = 3;
	private int maxTimeInExitArea = 8;
	
	private int minTimeInBaggageArea = 15;
	private int maxTimeInBaggageArea = 35;

	
	
	public int getNumBaggageReclaim() {
		return numBaggageReclaim;
	}

	public void setNumBaggageReclaim(int numBaggageReclaim) {
		this.numBaggageReclaim = numBaggageReclaim;
	}

	public int getTimeReopenBaggageReclaim() {
		return timeReopenBaggageReclaim;
	}

	public void setTimeReopenBaggageReclaim(int timeReopenBaggageReclaim) {
		this.timeReopenBaggageReclaim = timeReopenBaggageReclaim;
	}

	public int getMinTimeInArrivalArea() {
		return minTimeInArrivalArea;
	}

	public void setMinTimeInArrivalArea(int minTimeInArrivalArea) {
		this.minTimeInArrivalArea = minTimeInArrivalArea;
	}

	public int getMaxTimeInArrivalArea() {
		return maxTimeInArrivalArea;
	}

	public void setMaxTimeInArrivalArea(int maxTimeInArrivalArea) {
		this.maxTimeInArrivalArea = maxTimeInArrivalArea;
	}

	public int getMinTimeInExitArea() {
		return minTimeInExitArea;
	}

	public void setMinTimeInExitArea(int minTimeInExitArea) {
		this.minTimeInExitArea = minTimeInExitArea;
	}

	public int getMaxTimeInExitArea() {
		return maxTimeInExitArea;
	}

	public void setMaxTimeInExitArea(int maxTimeInExitArea) {
		this.maxTimeInExitArea = maxTimeInExitArea;
	}

	public int getMinTimeInBaggageArea() {
		return minTimeInBaggageArea;
	}

	public void setMinTimeInBaggageArea(int minTimeInBaggageArea) {
		this.minTimeInBaggageArea = minTimeInBaggageArea;
	}

	public int getMaxTimeInBaggageArea() {
		return maxTimeInBaggageArea;
	}

	public void setMaxTimeInBaggageArea(int maxTimeInBaggageArea) {
		this.maxTimeInBaggageArea = maxTimeInBaggageArea;
	}
}
