package nl.hu.v1wac.firstapp.persistence;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Country;
public class CountryDAO extends BaseDAO {
	private List<Country> selectCountry(String query) {
		List<Country> results = new ArrayList<Country>();
	try (Connection con = super.getConneciton()) {
		Statement stmt = con.createStatement();
		ResultSet dbResultSet = stmt.executeQuery(query);
		
		while (dbResultSet.next()) {
			String name = dbResultSet.getString("name");
			String code = dbResultSet.getString("code");
			String continent = dbResultSet.getString("continent");
			String region = dbResultSet.getString("region");
			String capital = dbResultSet.getString("capital");
			double surface = dbResultSet.getDouble("surfacearea");
			int population = dbResultSet.getInt("population");
			String government = dbResultSet.getString("governmentform");
			double latitude = dbResultSet.getDouble("latitude");
			double longitude = dbResultSet.getDouble("longitude");
			Country newCountry = new Country(code, name, capital, continent, region, surface, population, government, latitude, longitude);
			results.add(newCountry);
		}
	} catch (SQLException sqle) {sqle.printStackTrace(); }
	return results;
	}
	
	public List<Country> findAll() {
		return selectCountry("SELECT * FROM country");
	}
	
	public List<Country> find10LargestPopulations() {
		List<Country> lijst = selectCountry("SELECT * FROM country");
		Collections.sort(lijst, new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return c2.getPopulation() - c1.getPopulation();
			};
		});
		return lijst.subList(0, 10);
	}
	
	public List<Country> find10LargestSurfaces() {
		List<Country> lijst = selectCountry("SELECT * FROM country");
		Collections.sort(lijst, new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return (int)(c2.getSurface() - c1.getSurface());
			};
		});
		return lijst.subList(0, 10);
	}
	
	
	public Country getCountryByCode(String code) {
		Country result = null;
		List<Country> lijst = selectCountry("SELECT * FROM country");
		for(Country c : lijst) {
			if (c.getCode().equals(code)) {
				result = c;
				break;
			}
		}
		return result;
	}
	
	public boolean delete(Country c) {
		boolean result = false;
		
			String query = "DELETE FROM country WHERE code = '" + c.getCode() + "'"; 
					
			try (Connection con = super.getConneciton()) {
				
				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(query) == 1) { // 1 row updated!
					result = true;
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		
		
		return result;
	}
	
	public Country update(Country c) {
		Country result = null;
		String query = "UPDATE country SET code = "+ c.getCode() +", name = value2 "+ c.getName() +", continent = "+c.getContinent()+" , region = "+ c.getRegion() +", surfacearea = "+ c.getSurface()+" , population = "+ c.getPopulation()+", governmentform = "+c.getGovernment()+", latitude = "+c.getLatitude()+", longitude = "+ c.getLongitude()+", capital = "+c.getCapital()+"  WHERE name = "+ c.getName();
		try (Connection con = super.getConneciton()) {
			
			Statement stmt = con.createStatement();
			if (stmt.executeUpdate(query) == 1) { // 1 row updated!
				
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		
		return c;
	}
	
	public Country save(Country c) {
		Country result = c;
		String query = "INSERT INTO country (code, name, continent, region, surfacearea, population, governmentform, latitude, longitude, capital) VALUES "
				+ "('" + c.getCode() + "','" + c.getName() + "','" + "Asia" + "','" + c.getRegion() + "','" + c.getSurface() + "','" + c.getPopulation() + "','" + c.getGovernment() + "','" + c.getLatitude() + "','" + c.getLongitude() + "','" + c.getCapital() + "')"; 
		
		try (Connection con = super.getConneciton()) {
			
			Statement stmt = con.createStatement();
			if (stmt.executeUpdate(query) == 1) { // 1 row updated!
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
	}
	
}
