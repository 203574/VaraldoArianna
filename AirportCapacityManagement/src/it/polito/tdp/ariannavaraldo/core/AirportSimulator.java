package it.polito.tdp.ariannavaraldo.core;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import it.polito.tdp.ariannavaraldo.model.CheckInActivity;
import it.polito.tdp.ariannavaraldo.model.CheckInDesk;
import it.polito.tdp.ariannavaraldo.model.Commons;
import it.polito.tdp.ariannavaraldo.model.ConfigDeparture;
import it.polito.tdp.ariannavaraldo.model.Flight;
import it.polito.tdp.ariannavaraldo.model.PersonDeparture;
import it.polito.tdp.ariannavaraldo.model.PersonArea;
import it.polito.tdp.ariannavaraldo.model.SecurityDesk;

public class AirportSimulator {


	ConfigDeparture config;

	List<CheckInDesk> airportCheckInDescks;
	List<SecurityDesk> airportSecurityDescks;
	Queue<PersonDeparture> toSecurityDesk;
	Queue<PersonDeparture> inDutyFreeArea;

	public AirportSimulator(ConfigDeparture config) {
		this.config = config;
	}


	public void startSimulation(List<Flight> flights) throws Exception {


		airportCheckInDescks = new ArrayList<CheckInDesk>();
		for(int i=0; i<config.getNumCheckInDesk();i++)
			airportCheckInDescks.add(new CheckInDesk(i,config.getTimeReopenCheckinDesk()));

		airportSecurityDescks = new ArrayList<SecurityDesk>();
		for(int i=0; i<config.getNumSecurityDesk();i++)
			airportSecurityDescks.add(new SecurityDesk(i));

		toSecurityDesk = new PriorityQueue<PersonDeparture>(new Comparator<PersonDeparture>() {
			@Override
			public int compare(PersonDeparture o1, PersonDeparture o2) {
				if(o1.getArea(Commons.AREA_TO_SECURITY).getEnd().after(o2.getArea(Commons.AREA_TO_SECURITY).getEnd()))
					return 1;
				if(o1.getArea(Commons.AREA_TO_SECURITY).getEnd().before(o2.getArea(Commons.AREA_TO_SECURITY).getEnd()))
					return -1;
				return 0;
			}
		});

		inDutyFreeArea = new PriorityQueue<PersonDeparture>(new Comparator<PersonDeparture>() {
			@Override
			public int compare(PersonDeparture o1, PersonDeparture o2) {
				if(o1.getArea(Commons.AREA_DUTY).getStart().after(o2.getArea(Commons.AREA_DUTY).getStart()))
					return 1;
				if(o1.getArea(Commons.AREA_DUTY).getStart().before(o2.getArea(Commons.AREA_DUTY).getStart()))
					return -1;
				return 0;
			}
		});

		for (Flight flight : flights) {

			Queue<PersonDeparture> arrived = createArrivalEvents(flight);

			if(!flight.isNazionale()){
				List<CheckInDesk> flightCheckInDesks = selectCheckinDeskForFlight(flight);
				distributePassengerToCheckInDesk(flightCheckInDesks,arrived);
				for(CheckInDesk desk:flightCheckInDesks)
					dequeueToSecurityDescks(desk.getCurrentActivity().getQueue(), true);
			}else{
				dequeueToSecurityDescks(arrived, false);
			}
		}

		distributePassengerToSecurityDesk(toSecurityDesk);

		while (!inDutyFreeArea.isEmpty()){
			PersonDeparture p = inDutyFreeArea.remove();

			/*
			String s= p.getNumber() + "\t flight: " + p.getFlight().getFlightNum() + " - " + p.getFlight().getDepartureTime() + " -\t "
					+ " Arrivo: " + p.getArea(Commons.AREA_ARRIVO).getStart() + " " + p.getArea(Commons.AREA_ARRIVO).getEnd();
			if(p.getArea(Commons.AREA_CHECK_IN)!=null)
					s+= " CheckIn: " +p.getCheckInNumber() + " " + p.getArea(Commons.AREA_CHECK_IN).getStart() + " " + p.getArea(Commons.AREA_CHECK_IN).getEnd();
			s+= " TrasferimentoSecurity: " + p.getArea(Commons.AREA_TO_SECURITY).getStart() + " " + p.getArea(Commons.AREA_TO_SECURITY).getEnd();
			s+= " CheckSecurity: "+p.getSecurityCheckNumber() + " " + p.getArea(Commons.AREA_SECURITY).getStart() + " " + p.getArea(Commons.AREA_SECURITY).getEnd();
			s+= " Duty: " + p.getArea(Commons.AREA_DUTY).getStart() + " " + p.getArea(Commons.AREA_DUTY).getEnd();
			s+= " EMBARC: " + p.getArea(Commons.AREA_EMBARC).getStart() + " " + p.getArea(Commons.AREA_EMBARC).getEnd();

			System.out.println(s);
			*/
			if(p.isLate())
				p.getFlight().setEsito("KO");
			
		}
	}


	private void dequeueToSecurityDescks(Queue<PersonDeparture> queue, boolean fromCheckInArea) {
		for(int i=0; i< queue.size();i++){
//		while(!queue.isEmpty()){
			PersonDeparture p = queue.remove();
			PersonArea pa = new PersonArea();
			pa.setArea(Commons.AREA_TO_SECURITY);
			Time start, end;
			if(fromCheckInArea)
				start=new Time(p.getArea(Commons.AREA_CHECK_IN).getEnd().getTime());
			else
				start=new Time(p.getArea(Commons.AREA_ARRIVO).getEnd().getTime());
			end = new Time(start.getTime()+p.getTimeToSecurityArea());
			pa.setStart(start);
			pa.setEnd(end);
			p.getAreas().put(Commons.AREA_TO_SECURITY,pa);

			toSecurityDesk.add(p);


		}

	}


	private void distributePassengerToSecurityDesk(Queue<PersonDeparture> passengers) {
		SecurityDesk s=null;
		while(!passengers.isEmpty()){

			PersonDeparture p =passengers.remove();
			s = getBestSecurityDesk(p.getArea(Commons.AREA_TO_SECURITY).getEnd());

			PersonArea pa = new PersonArea();
			pa.setArea(Commons.AREA_SECURITY);
			pa.setStart(new Time(s.getNext().getTime()));
			pa.setEnd(new Time(pa.getStart().getTime()+p.getTimeForSecurityCheck()));
			if(s.getNext().compareTo(new Time(p.getFlight().getDepartureTime().getTime()-config.getMinTimeInDutyFree()-5*Commons.MINUTE))>0){
				p.setLate(true);
			}
			p.getAreas().put(Commons.AREA_SECURITY,pa);
			p.setSecurityCheckNumber(s.getNumber());
			s.getQueue().add(p);
			s.setNext(new Time(pa.getEnd().getTime()));

			PersonArea paD = new PersonArea();
			paD.setArea(Commons.AREA_DUTY);
			Time startD, endD;
			startD = new Time(p.getArea(Commons.AREA_SECURITY).getEnd().getTime());
			endD = new Time(p.getFlight().getDepartureTime().getTime()-p.getTimeInEmbarc());
			paD.setStart(startD);
			paD.setEnd(endD);
			p.getAreas().put(Commons.AREA_DUTY,paD);

			PersonArea paE = new PersonArea();
			paE.setArea(Commons.AREA_EMBARC);
			Time startE, endE;
			startE = new Time(p.getArea(Commons.AREA_DUTY).getEnd().getTime());
			endE = new Time(p.getFlight().getDepartureTime().getTime());
			paE.setStart(startE);
			paE.setEnd(endE);
			p.getAreas().put(Commons.AREA_EMBARC,paE);

			inDutyFreeArea.add(p);
		}

	}

	private SecurityDesk getBestSecurityDesk(Time end) {
		SecurityDesk s = null;
		for(SecurityDesk desk: airportSecurityDescks){
			if(desk.getNext()==null){
				desk.setOpen(new Time(end.getTime()));
				desk.setNext(new Time(end.getTime()));
				return desk;
			}
			if(s==null || s.getNext().compareTo(desk.getNext())>=0)
				s=desk;
		}
		return s;
	}


	private void distributePassengerToCheckInDesk(List<CheckInDesk> flightCheckInDesks, Queue<PersonDeparture> passengers) {
		CheckInDesk c=null;
		while(!passengers.isEmpty()){
			PersonDeparture p =passengers.remove();
			for(CheckInDesk desk:flightCheckInDesks)
				if(c==null
					|| desk.getCurrentActivity().getNext().compareTo(desk.getCurrentActivity().getOpen())==0
					|| c.getCurrentActivity().getNext().compareTo(desk.getCurrentActivity().getNext())>=0)
					c=desk;
			CheckInActivity ca = c.getCurrentActivity();
			PersonArea pa = new PersonArea();
			pa.setArea(Commons.AREA_CHECK_IN);
			pa.setStart(new Time(ca.getNext().getTime()));
			pa.setEnd(new Time(pa.getStart().getTime()+p.getTimeForCheckIn()));
			p.getAreas().put(Commons.AREA_CHECK_IN,pa);
			p.setCheckInNumber(c.getNumber());
			ca.getQueue().add(p);
			ca.setNext(new Time(pa.getEnd().getTime()));
		}
		for(CheckInDesk desk:flightCheckInDesks){
			desk.getCurrentActivity().setClose(new Time(desk.getCurrentActivity().getNext().getTime()));
		}

	}


	private List<CheckInDesk> selectCheckinDeskForFlight(Flight flight) throws Exception {
		int num_checkInDesk = 1;
		while(num_checkInDesk*config.getPaxCheckInDesk() <= flight.getPassengers())
			num_checkInDesk++;

		List<CheckInDesk> flightCheckInDesks = new ArrayList<CheckInDesk>();
		Time openCheckInTime = new Time(flight.getDepartureTime().getTime()-Commons.HOUR*2);
		Time closeCheckInTime = new Time(flight.getDepartureTime().getTime()-Commons.MINUTE*30);
		for(CheckInDesk checkIn: airportCheckInDescks){
			if(checkIn.isFree(openCheckInTime)){
				CheckInActivity ca = new CheckInActivity();
				ca.setOpen(openCheckInTime);
				ca.setClose(closeCheckInTime);
				ca.setFlight(flight);
				checkIn.getActivities().add(ca);
				checkIn.setCurrentActivity(ca);
				flightCheckInDesks.add(checkIn);
			}
			if(flightCheckInDesks.size()==num_checkInDesk)
				break;
		}

		if(flightCheckInDesks.size()!=num_checkInDesk)
			throw new Exception("Non ci sono check-in desck disponibili");

		return flightCheckInDesks;

	}


	private Queue<PersonDeparture> createArrivalEvents(Flight flight) {


		//Creo una coda prioritaria per poter poi recuperare gli elementi in base all'ora di accodamento al checkIn
		Queue<PersonDeparture> queue = new PriorityQueue<PersonDeparture>(new Comparator<PersonDeparture>() {
			@Override
			public int compare(PersonDeparture o1, PersonDeparture o2) {
				if(o1.getArea(Commons.AREA_ARRIVO).getEnd().after(o2.getArea(Commons.AREA_ARRIVO).getEnd()))
					return 1;
				if(o1.getArea(Commons.AREA_ARRIVO).getEnd().before(o2.getArea(Commons.AREA_ARRIVO).getEnd()))
					return -1;
				return 0;
			}
		});

		int i=0;
        while (i++ <= flight.getPassengers()) {
            PersonDeparture person = new PersonDeparture(i,flight,config);
            PersonArea area = new PersonArea();
            area.setStart(person.getActualArrivalInAirport());
            area.setEnd(new Time(area.getStart().getTime()+person.getMillisInArrivalArea()));
            person.getAreas().put(Commons.AREA_ARRIVO, area);
            queue.add(person);
            flight.getPersons().add(person);
        }
        return queue;
	}

}
