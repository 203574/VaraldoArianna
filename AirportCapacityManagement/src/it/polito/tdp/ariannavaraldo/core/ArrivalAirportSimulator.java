package it.polito.tdp.ariannavaraldo.core;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import it.polito.tdp.ariannavaraldo.model.BaggageReclaimActivity;
import it.polito.tdp.ariannavaraldo.model.BaggageReclaimUnit;
import it.polito.tdp.ariannavaraldo.model.Commons;
import it.polito.tdp.ariannavaraldo.model.ConfigArrival;
import it.polito.tdp.ariannavaraldo.model.Flight;
import it.polito.tdp.ariannavaraldo.model.PersonArea;
import it.polito.tdp.ariannavaraldo.model.PersonArrival;

public class ArrivalAirportSimulator {


	ConfigArrival config;

	List<BaggageReclaimUnit> airportBaggageReclaimUnit;
	Queue<PersonArrival> inExitArea;

	public ArrivalAirportSimulator(ConfigArrival config) {
		this.config = config;
	}


	public void startSimulation(List<Flight> flights) throws Exception {


		airportBaggageReclaimUnit = new ArrayList<BaggageReclaimUnit>();
		for(int i=0; i<config.getNumBaggageReclaim();i++)
			airportBaggageReclaimUnit.add(new BaggageReclaimUnit(i,config.getTimeReopenBaggageReclaim()));


		inExitArea = new LinkedList<PersonArrival>();

		for (Flight flight : flights) {

			Queue<PersonArrival> arrived = createLandingEvents(flight);

			if(!flight.isNazionale()){
				BaggageReclaimUnit baggageReclaimUnit = selectBaggageReclaimUnitForFlight(flight);
				doPassengerReclaimBaggage(baggageReclaimUnit,arrived);
				dequeueToExiArea(baggageReclaimUnit.getCurrentActivity().getQueue(),true);
			}else{
				dequeueToExiArea(arrived, false);
			}
		}


		for(Flight flight: flights)
			flight.setEsito("OK");
		while (!inExitArea.isEmpty()){
			PersonArrival p = inExitArea.remove();
/*
  		String s= p.getNumber() + "\t flight: " + p.getFlight().getFlightNum() + " - " + p.getFlight().getDepartureTime() + " -\t "
 
					+ " Transfer arrivi: " + p.getArea(Commons.AREA_TRANSFER_TO_ARRIVAL).getStart() + " " + p.getArea(Commons.AREA_TRANSFER_TO_ARRIVAL).getEnd();
			if(p.getArea(Commons.AREA_BAGGAGE_RECLAIM)!=null)
					s+= " Baggage Reclaim: " +p.getBaggageReclaimUnitNumber() + " " + p.getArea(Commons.AREA_BAGGAGE_RECLAIM).getStart() + " " + p.getArea(Commons.AREA_BAGGAGE_RECLAIM).getEnd();
			s+= " Exit: " + p.getArea(Commons.AREA_EXIT).getStart() + " " + p.getArea(Commons.AREA_EXIT).getEnd();

			System.out.println(s);
*/
		}
	}




	private void dequeueToExiArea(Queue<PersonArrival> queue, boolean fromBaggageReclaim) {
		while(!queue.isEmpty()){
			PersonArrival p = queue.remove();
			PersonArea pa = new PersonArea();
			pa.setArea(Commons.AREA_EXIT);
			Time start, end;
			if(fromBaggageReclaim)
				start=new Time(p.getArea(Commons.AREA_BAGGAGE_RECLAIM).getEnd().getTime());
			else
				start=new Time(p.getArea(Commons.AREA_TRANSFER_TO_ARRIVAL).getEnd().getTime());
			end = new Time(start.getTime()+p.getTimeToExitArea());
			pa.setStart(start);
			pa.setEnd(end);
			p.getAreas().put(Commons.AREA_EXIT,pa);

			inExitArea.add(p);
		}
	}


	private void doPassengerReclaimBaggage(BaggageReclaimUnit baggageReclaimUnit, Queue<PersonArrival> queue) {
		while(!queue.isEmpty()){
			PersonArrival p = queue.remove();
			PersonArea pa = new PersonArea();
			pa.setArea(Commons.AREA_BAGGAGE_RECLAIM);
			Time start, end;
			start=new Time(p.getArea(Commons.AREA_TRANSFER_TO_ARRIVAL).getEnd().getTime());
			end = new Time(start.getTime()+p.getTimeForBaggageReclaim());
			pa.setStart(start);
			pa.setEnd(end);
			p.getAreas().put(Commons.AREA_BAGGAGE_RECLAIM,pa);

			baggageReclaimUnit.getCurrentActivity().getQueue().add(p);
		}
		
	}




	private BaggageReclaimUnit selectBaggageReclaimUnitForFlight(Flight flight) throws Exception {

		Time openBaggageUnitTime = new Time(flight.getArrivalTime().getTime()+Commons.MINUTE*10);
		Time closeBaggageUnitTime = new Time(flight.getDepartureTime().getTime()-Commons.MINUTE*40);
		BaggageReclaimUnit selected=null;
		for(BaggageReclaimUnit unit: airportBaggageReclaimUnit){
			if(unit.isFree(openBaggageUnitTime)){
				BaggageReclaimActivity bra = new BaggageReclaimActivity();
				bra.setOpen(openBaggageUnitTime);
				bra.setClose(closeBaggageUnitTime);
				bra.setFlight(flight);
				unit.getActivities().add(bra);
				unit.setCurrentActivity(bra);
				selected=unit;
				break;
			}
		}

		if(selected==null)
			throw new Exception("Il numero di unità per il ritiro bagagli disponibili non è sufficiente. Aumentarlo per effettuare la simulazione");
		
		return selected;

	}


	private Queue<PersonArrival> createLandingEvents(Flight flight) {


		//Creo una coda prioritaria per poter poi recuperare gli elementi in base all'ora di arrivo nell'area arrivi
		Queue<PersonArrival> queue = new PriorityQueue<PersonArrival>(new Comparator<PersonArrival>() {
			@Override
			public int compare(PersonArrival o1, PersonArrival o2) {
				if(o1.getArea(Commons.AREA_TRANSFER_TO_ARRIVAL).getEnd().after(o2.getArea(Commons.AREA_TRANSFER_TO_ARRIVAL).getEnd()))
					return 1;
				if(o1.getArea(Commons.AREA_TRANSFER_TO_ARRIVAL).getEnd().before(o2.getArea(Commons.AREA_TRANSFER_TO_ARRIVAL).getEnd()))
					return -1;
				return 0;
			}
		});

        for (int i=0;i < flight.getPassengers();i++) {
        	PersonArrival person = new PersonArrival(i,flight,config);
            PersonArea area = new PersonArea();
            area.setStart(new Time(flight.getArrivalTime().getTime()));
            area.setEnd(new Time(area.getStart().getTime()+person.getTimeInArrivalArea()));
            person.getAreas().put(Commons.AREA_TRANSFER_TO_ARRIVAL, area);
            queue.add(person);
            flight.getArrivalPersons().add(person);
        }
        return queue;
	}

}
