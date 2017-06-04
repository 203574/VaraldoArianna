package it.polito.tdp.ariannavaraldo.model;

import java.sql.Time;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class SecurityDesk {

	private int number;
	private Time next;
	private Time open;
	private Time close;
	private Queue<PersonDeparture> queue;


	public SecurityDesk(int number) {
		this.number = number;
	}


	public Time getNext() {
		return next;
	}


	public void setNext(Time next) {
		this.next = next;
	}


	public Time getOpen() {
		return open;
	}


	public void setOpen(Time open) {
		this.open = open;
	}


	public Time getClose() {
		return close;
	}


	public void setClose(Time close) {
		this.close = close;
	}


	public int getNumber() {
		return number;
	}


	public Queue<PersonDeparture> getQueue() {
		if(queue == null)
			queue = new PriorityQueue<PersonDeparture>(new Comparator<PersonDeparture>() {
				@Override
				public int compare(PersonDeparture o1, PersonDeparture o2) {
					if(o1.getArea(Commons.AREA_SECURITY).getEnd().after(o2.getArea(Commons.AREA_SECURITY).getEnd()))
						return 1;
					if(o1.getArea(Commons.AREA_SECURITY).getEnd().before(o2.getArea(Commons.AREA_SECURITY).getEnd()))
						return -1;
					return 0;
				}
			});
		return queue;
	}

}
