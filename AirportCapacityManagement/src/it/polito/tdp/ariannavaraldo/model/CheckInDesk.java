package it.polito.tdp.ariannavaraldo.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CheckInDesk {

	private int number;
	private int timeReopen;
	private List<CheckInActivity> activities;
	private CheckInActivity currentActivity = null;



	public CheckInDesk(int number, int timeReopen) {
		this.number = number;
		this.timeReopen = timeReopen;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<CheckInActivity> getActivities() {
		if(activities==null)
			activities = new ArrayList<CheckInActivity>();
		return activities;
	}

	public void setActivities(List<CheckInActivity> activities) {
		this.activities = activities;
	}

	public boolean isOpen(Time t){
		for(CheckInActivity activity: activities)
			if(t.compareTo(activity.getOpen())>=0 && t.compareTo(activity.getClose()) <= 0)
				return true;
		return false;
	}

	public boolean isFree(Time t){
		if(getActivities().size()==0)
			return true;
		CheckInActivity last = getLastActivity();

		if(t.getTime()-last.getClose().getTime() > timeReopen)
			return true;
		return false;
	}

	private CheckInActivity getLastActivity() {

		CheckInActivity activity = null;
		for(CheckInActivity act : getActivities())
			if(activity==null || act.getClose().after(activity.getClose()))
				activity=act;
		return activity;
	}


	public CheckInActivity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(CheckInActivity currentActivity) {
		this.currentActivity = currentActivity;
	}


}
