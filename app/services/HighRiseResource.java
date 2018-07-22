package services;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class HighRiseResource {

	private static String TOKEN = "e5b794a0faa94b8f8b7c9287752a49da";
	private static String PASSWORD = "X";
	private static HighRiseResource singleInstance;
	public static String URL = "https://olladuwovi0932.highrisehq.com/";
	public static String PEOPLE_RESOURCE = URL + "people.xml";
	public static String PEOPLE_BY_TAG_RESOURCE = URL + "people.xml?tag_id=";
	public static String COMPANIES_RESOURCE = URL + "companies.xml";
	public static String COMPANIES_BY_TAG_RESOURCE = URL + "companies.xml?tag_id=";
	public static String TAGS_RESOURCE = URL + "tags.xml";

	private Client client = Client.create();

	private HighRiseResource() {
		this.client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(TOKEN, PASSWORD));
	}

	public static HighRiseResource instance() {

		if (singleInstance != null)
			return singleInstance;

		return singleInstance = new HighRiseResource();

	}

	public String getResource(String resource) {
		return client.resource(resource).accept("application/xml").get(ClientResponse.class).getEntity(String.class);
	}

}
