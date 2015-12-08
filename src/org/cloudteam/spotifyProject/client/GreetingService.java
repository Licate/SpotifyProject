package org.cloudteam.spotifyProject.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	String greetGetCountries() throws Exception;
	String greetGetRecurences() throws Exception;
	String greetGetDates() throws Exception;
	String greetGetCharts(String type, String country, String recurrence, String date, int limit) throws Exception;
}
