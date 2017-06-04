package it.polito.tdp.ariannavaraldo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import it.polito.tdp.ariannavaraldo.model.Airport;

public class AirportDAO 
{
	public List<Airport> getAllAirports()
	{
		List<Airport> apts = new ArrayList<Airport>();
		String sql="select name, city, `IATA_FAA` from airport where isNY=1 and hasschedule=1";
		try
		{
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				String name = rs.getString(1);
				String city = rs.getString(2);
				String iata = rs.getString(3);
				
				Airport a = new Airport(name, city, iata);
				apts.add(a);
				
			}
			
			rs.close();
			conn.close();
			return apts;
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Errore nel DB");
			
		}
		
		
	}
}
