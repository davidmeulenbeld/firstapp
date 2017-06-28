package nl.hu.v1wac.firstapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.hu.v1wac.firstapp.persistence.*;

public class WorldService {
	CountryDAO countryDAO = new CountryDAO();
	
	
	public List<Country> getAllCountries() {
		return countryDAO.findAll();
	}
	public WorldService() {
		
	}
	
	//public List<Country> getAllCountries() {
	//	return allCountries; 
		
	//}
	
	public List<Country> get10LargestPopulations() {
		return countryDAO.find10LargestPopulations();
	}

	public List<Country> get10LargestSurfaces() {
		return countryDAO.find10LargestSurfaces();
	}
	
	public Country getCountryByCode(String code) {
		return countryDAO.getCountryByCode(code);
	}
	
	public boolean deleteLand(Country c) {
		return countryDAO.delete(c);
	}
	
	public Country saveCountry(Country c) {
		return countryDAO.save(c);
	}
}
