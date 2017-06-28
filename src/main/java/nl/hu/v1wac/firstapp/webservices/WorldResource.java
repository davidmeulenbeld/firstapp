package nl.hu.v1wac.firstapp.webservices;

import java.io.InputStream;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.firstapp.model.*;
@Path("/countries")
public class WorldResource {
	@GET
	
	@Produces("application/json")
	public String getLanden() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Country o : service.getAllCountries()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", o.getCode());
			job.add("name", o.getName());
			job.add("capital", o.getCapital());
			job.add("surface", o.getSurface());
			job.add("government", o.getGovernment());
			job.add("lat", o.getLatitude());
			job.add("iso3", o.getIso3Code());
			job.add("continent", o.getContinent());
			job.add("region", o.getRegion());
			job.add("population", o.getPopulation());
			job.add("lng", o.getLongitude());
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public String getLand(@PathParam("id") String id) {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Country c = service.getCountryByCode(id);
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("code", c.getCode());
		job.add("name", c.getName());
		job.add("capital", c.getCapital());
		job.add("surface", c.getSurface());
		job.add("government", c.getGovernment());
		job.add("lat", c.getLatitude());
		job.add("iso3", c.getIso3Code());
		job.add("continent", c.getContinent());
		job.add("region", c.getRegion());
		job.add("population", c.getPopulation());
		job.add("lng", c.getLongitude());
		jab.add(job);
		JsonArray array = jab.build();
		return array.toString();
	}
	@Path("largestsurfaces")
	@GET
	@Produces("application/json")
	public String getbiggest() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Country o : service.get10LargestSurfaces()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", o.getCode());
			job.add("name", o.getName());
			job.add("capital", o.getCapital());
			job.add("surface", o.getSurface());
			job.add("government", o.getGovernment());
			job.add("lat", o.getLatitude());
			job.add("iso3", o.getIso3Code());
			job.add("continent", o.getContinent());
			job.add("region", o.getRegion());
			job.add("population", o.getPopulation());
			job.add("lng", o.getLongitude());
			jab.add(job);
		}
		
		
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@Path("largestpopulations")
	@GET
	@Produces("application/json")
	public String getlargest() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Country o : service.get10LargestPopulations()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", o.getCode());
			job.add("name", o.getName());
			job.add("capital", o.getCapital());
			job.add("surface", o.getSurface());
			job.add("government", o.getGovernment());
			job.add("lat", o.getLatitude());
			job.add("iso3", o.getIso3Code());
			job.add("continent", o.getContinent());
			job.add("region", o.getRegion());
			job.add("population", o.getPopulation());
			job.add("lng", o.getLongitude());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
}
	
	@POST
	  @Produces("application/json")
  public String createCountry(InputStream is) {
		WorldService service = ServiceProvider.getWorldService();
		JsonObject object = Json.createReader(is).readObject();
		String nm = object.getString("name");
		String code = object.getString("code");
		String capital = object.getString("capital");
		String continent = object.getString("continent");
		String region = object.getString("region");
		String surfacestring = object.getString("surface");
		double surface = Double.parseDouble(surfacestring);
		String populationString = object.getString("population");
		int population = Integer.parseInt(populationString);
		String government = object.getString("government");
		String latitudestring = object.getString("latitude");
		double latitude = Double.parseDouble(latitudestring);
		String longitudestring = object.getString("longitude");
		double longitude = Double.parseDouble(longitudestring);
	    Country c = new Country(code, code, nm, capital, continent, region, surface, population, government, latitude, longitude);
	    
	    service.saveCountry(c);
	    return nm;
	  }
	@DELETE
	  @Path("{id}")
		@RolesAllowed("user")
	  public Response deleteCustomer(@PathParam("id") String id) {
	    Country found = null;
	    WorldService service = ServiceProvider.getWorldService();
	    Country c = service.getCountryByCode(id);
	    if (service.deleteLand(c) == true) {
	    	return Response.ok().build();
	    }
	    else {
	    	return Response.status(Response.Status.FORBIDDEN).build();
	    }
	  }
	
	

}
	
