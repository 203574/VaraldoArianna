package it.polito.tdp.ariannavaraldo.model;

import java.sql.Time;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheckInActivity {

	private Time open;
	private Time close;
	private Flight flight;
	private Queue<PersonDeparture> queue;
	private Time next;


	public Time getOpen() {
		return open;
	}
	public void setOpen(Time open) {
		this.open = open;
		this.next = new Time(open.getTime());
	}
	public Time getClose() {
		return close;
	}
	public void setClose(Time close) {
		this.close = close;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Queue<PersonDeparture> getQueue() {
		if(queue == null)
			queue = new PriorityQueue<PersonDeparture>(new Comparator<PersonDeparture>() {
				@Override
				public int compare(PersonDeparture o1, PersonDeparture o2) {
					if(o1.getArea(Commons.AREA_CHECK_IN).getEnd().after(o2.getArea(Commons.AREA_CHECK_IN).getEnd()))
						return 1;
					if(o1.getArea(Commons.AREA_CHECK_IN).getEnd().before(o2.getArea(Commons.AREA_CHECK_IN).getEnd()))
						return -1;
					return 0;
				}
			});
		return queue;
	}
	public void setQueue(Queue<PersonDeparture> queue) {
		this.queue = queue;
	}
	public Time getNext() {
		return next;
	}
	public void setNext(Time next) {
		this.next = next;
	}

}
