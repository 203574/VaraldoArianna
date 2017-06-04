package it.polito.tdp.ariannavaraldo.model;

public class ConfigDeparture {

	private int paxCheckInDesk = 50;

	private int numCheckInDesk =150;

	private int timeReopenCheckinDesk = Commons.MINUTE*20;

	private int numSecurityDesk = 10;

	private int maxAdvanceArrival = 20;
	private int minAdvanceArrival = -10;

	private int minTimeInArrivalArea = 3;
	private int maxTimeInArrivalArea = 6;

	private int minTimeForCheckIn = 1;
	private int maxTimeForCheckIn = 3;

	private int minTimeToSecurityArea = 3;
	private int maxTimeToSecurityArea = 7;

	private int minTimeForSecurityCheck = 1;
	private int maxTimeForSecurityCheck = 2;

	private int minTimeInDutyFree = 5;

	private int minTimeInEmbarc = 10;
	private int maxTimeInEmbarc = 30;

	public ConfigDeparture(int paxCheckInDesk, int numCheckInDesk, int timeReopenCheckinDesk, int numSecurityDesk,
			int maxAdvanceArrival, int minAdvanceArrival, int minTimeInArrivalArea, int maxTimeInArrivalArea,
			int minTimeForCheckIn, int maxTimeForCheckIn, int minTimeToSecurityArea, int maxTimeToSecurityArea,
			int minTimeForSecurityCheck, int maxTimeForSecurityCheck, int minTimeInDutyFree, int minTimeInEmbarc,
			int maxTimeInEmbarc) {
		super();
		this.paxCheckInDesk = paxCheckInDesk;
		this.numCheckInDesk = numCheckInDesk;
		this.timeReopenCheckinDesk = timeReopenCheckinDesk;
		this.numSecurityDesk = numSecurityDesk;
		this.maxAdvanceArrival = maxAdvanceArrival;
		this.minAdvanceArrival = minAdvanceArrival;
		this.minTimeInArrivalArea = minTimeInArrivalArea;
		this.maxTimeInArrivalArea = maxTimeInArrivalArea;
		this.minTimeForCheckIn = minTimeForCheckIn;
		this.maxTimeForCheckIn = maxTimeForCheckIn;
		this.minTimeToSecurityArea = minTimeToSecurityArea;
		this.maxTimeToSecurityArea = maxTimeToSecurityArea;
		this.minTimeForSecurityCheck = minTimeForSecurityCheck;
		this.maxTimeForSecurityCheck = maxTimeForSecurityCheck;
		this.minTimeInDutyFree = minTimeInDutyFree;
		this.minTimeInEmbarc = minTimeInEmbarc;
		this.maxTimeInEmbarc = maxTimeInEmbarc;
	}

	public ConfigDeparture() {
	}

	public int getMaxAdvanceArrival() {
		return maxAdvanceArrival;
	}
	public void setMaxAdvanceArrival(int maxAdvanceArrival) {
		this.maxAdvanceArrival = maxAdvanceArrival;
	}
	public int getPaxCheckInDesk() {
		return paxCheckInDesk;
	}
	public int getNumCheckInDesk() {
		return numCheckInDesk;
	}
	public int getTimeReopenCheckinDesk() {
		return timeReopenCheckinDesk;
	}
	public int getNumSecurityDesk() {
		return numSecurityDesk;
	}
	public int getMinAdvanceArrival() {
		return minAdvanceArrival;
	}
	public int getMinTimeInArrivalArea() {
		return minTimeInArrivalArea;
	}
	public int getMaxTimeInArrivalArea() {
		return maxTimeInArrivalArea;
	}
	public int getMinTimeForCheckIn() {
		return minTimeForCheckIn;
	}
	public int getMaxTimeForCheckIn() {
		return maxTimeForCheckIn;
	}
	public int getMinTimeToSecurityArea() {
		return minTimeToSecurityArea;
	}
	public int getMaxTimeToSecurityArea() {
		return maxTimeToSecurityArea;
	}
	public int getMinTimeForSecurityCheck() {
		return minTimeForSecurityCheck;
	}
	public int getMaxTimeForSecurityCheck() {
		return maxTimeForSecurityCheck;
	}
	public int getMinTimeInDutyFree() {
		return minTimeInDutyFree;
	}
	public int getMinTimeInEmbarc() {
		return minTimeInEmbarc;
	}
	public int getMaxTimeInEmbarc() {
		return maxTimeInEmbarc;
	}

	public void setPaxCheckInDesk(int paxCheckInDesk) {
		this.paxCheckInDesk = paxCheckInDesk;
	}

	public void setNumCheckInDesk(int numCheckInDesk) {
		this.numCheckInDesk = numCheckInDesk;
	}

	public void setTimeReopenCheckinDesk(int timeReopenCheckinDesk) {
		this.timeReopenCheckinDesk = timeReopenCheckinDesk;
	}

	public void setNumSecurityDesk(int numSecurityDesk) {
		this.numSecurityDesk = numSecurityDesk;
	}

	public void setMinAdvanceArrival(int minAdvanceArrival) {
		this.minAdvanceArrival = minAdvanceArrival;
	}

	public void setMinTimeInArrivalArea(int minTimeInArrivalArea) {
		this.minTimeInArrivalArea = minTimeInArrivalArea;
	}

	public void setMaxTimeInArrivalArea(int maxTimeInArrivalArea) {
		this.maxTimeInArrivalArea = maxTimeInArrivalArea;
	}

	public void setMinTimeForCheckIn(int minTimeForCheckIn) {
		this.minTimeForCheckIn = minTimeForCheckIn;
	}

	public void setMaxTimeForCheckIn(int maxTimeForCheckIn) {
		this.maxTimeForCheckIn = maxTimeForCheckIn;
	}

	public void setMinTimeToSecurityArea(int minTimeToSecurityArea) {
		this.minTimeToSecurityArea = minTimeToSecurityArea;
	}

	public void setMaxTimeToSecurityArea(int maxTimeToSecurityArea) {
		this.maxTimeToSecurityArea = maxTimeToSecurityArea;
	}

	public void setMinTimeForSecurityCheck(int minTimeForSecurityCheck) {
		this.minTimeForSecurityCheck = minTimeForSecurityCheck;
	}

	public void setMaxTimeForSecurityCheck(int maxTimeForSecurityCheck) {
		this.maxTimeForSecurityCheck = maxTimeForSecurityCheck;
	}

	public void setMinTimeInDutyFree(int minTimeInDutyFree) {
		this.minTimeInDutyFree = minTimeInDutyFree;
	}

	public void setMinTimeInEmbarc(int minTimeInEmbarc) {
		this.minTimeInEmbarc = minTimeInEmbarc;
	}

	public void setMaxTimeInEmbarc(int maxTimeInEmbarc) {
		this.maxTimeInEmbarc = maxTimeInEmbarc;
	}

}
