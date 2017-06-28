package nl.hu.v1wac.firstapp.model;

public class CountryServiceProvider {
	private static WorldService worldService = new WorldService();

	public static WorldService getWorldService() {
		return worldService;
	}
}
