package it.polito.tdp.ariannavaraldo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.ariannavaraldo.model.Flight;

public class ArrivalDAO 
{

	public List<Flight> getArrivalFlights(String iata, int month, int day, int from, int to)
	{
		List<Flight> flights = new ArrayList<Flight>();
		String sql="select dept_time, arr_time ,carrier, fl_num, dest, t2.name, seats, passengers, t2.isNY"
				+ " from schedules, airport t2 where iata_faa = origin and dest=? "
				+ " and months=? and day_of_week=? and HOUR(arr_time) between ? and ? order by arr_time";
		try
		{
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, iata);
			st.setInt(2, month);
			st.setInt(3, day);
			st.setInt(4, from);
			st.setInt(5, to-1);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				Time departureTime = rs.getTime(1);
				Time arrivalTime = rs.getTime(2);
				String carrier = rs.getString(3);
				int flightNum = rs.getInt(4);
				String airport = rs.getString(5);
				String airportDescr = rs.getString(6);
				int seats = rs.getInt(7);
				int passengers = rs.getInt(8);
				boolean nazionale = (rs.getInt(9)==0?false:true);
				
				Flight f = new Flight(departureTime, arrivalTime, carrier, flightNum, airport, airportDescr, seats, passengers, nazionale);
				flights.add(f);
			}
			
			rs.close();
			conn.close();
			return flights;
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Errore nel DB");
			
		}
		
		
	}
	
}
