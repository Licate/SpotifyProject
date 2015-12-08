package org.cloudteam.spotifyProject.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	void greetGetRecurences(AsyncCallback<String> callback) throws Exception;
	void greetGetDates(AsyncCallback<String> callback) throws Exception;
	void greetGetCountries(AsyncCallback<String> callback) throws Exception;
	void greetGetCharts(String type, String country, String recurrence, String date, int limit, AsyncCallback<String> callback) throws Exception;
}
