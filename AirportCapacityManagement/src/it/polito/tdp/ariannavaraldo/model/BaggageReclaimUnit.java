package it.polito.tdp.ariannavaraldo.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class BaggageReclaimUnit {
	private int number;
	private int timeReopen;
	private List<BaggageReclaimActivity> activities;
	private BaggageReclaimActivity currentActivity = null;

	public BaggageReclaimUnit(int number, int timeReopen) {
		super();
		this.number = number;
		this.timeReopen = timeReopen;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<BaggageReclaimActivity> getActivities() {
		if(activities==null)
			activities = new ArrayList<BaggageReclaimActivity>();
		return activities;
	}

	public void setActivities(List<BaggageReclaimActivity> activities) {
		this.activities = activities;
	}

	public boolean isOpen(Time t){
		for(BaggageReclaimActivity activity: activities)
			if(t.compareTo(activity.getOpen())>=0 && t.compareTo(activity.getClose()) <= 0)
				return true;
		return false;
	}

	public boolean isFree(Time t){
		if(getActivities().size()==0)
			return true;
		BaggageReclaimActivity last = getLastActivity();

		if(t.getTime()-last.getClose().getTime() > timeReopen*Commons.MINUTE)
			return true;
		return false;
	}

	private BaggageReclaimActivity getLastActivity() {

		BaggageReclaimActivity activity = null;
		for(BaggageReclaimActivity act : getActivities())
			if(activity==null || act.getClose().after(activity.getClose()))
				activity=act;
		return activity;
	}


	public BaggageReclaimActivity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(BaggageReclaimActivity currentActivity) {
		this.currentActivity = currentActivity;
	}



}
