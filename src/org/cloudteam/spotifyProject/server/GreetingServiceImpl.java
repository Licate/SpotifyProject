package org.cloudteam.spotifyProject.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.cloudteam.spotifyProject.client.GreetingService;
import org.cloudteam.spotifyProject.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	private String hostServer = "https://spotifycharts.com";
	private String pathApi = "/api/";
	private String urlServer = hostServer + pathApi;

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	
	public String greetGetCountries() throws Exception {
		String result = getHTML(this.urlServer + "?type=regional");
		return result;
	}
	
	public String greetGetRecurences() throws Exception {
		String result = getHTML(this.urlServer + "?type=regional&country=global");
		return result;
	}
	
	public String greetGetDates() throws Exception {
		String result = getHTML(this.urlServer + "?type=regional&country=global&recurrence=daily");
		return result;
	}
	
	private String addParameter(String name, String param) {
		return name + "=" + param + "&";
	}
	
	public String greetGetCharts(String type, String country, String recurrence, String date, int limit) throws Exception {
		StringBuilder sb = new StringBuilder(this.urlServer);
		sb.append("?");
		sb.append(addParameter("type", type));
		sb.append(addParameter("country", country));
		sb.append(addParameter("recurrence", recurrence));
		sb.append(addParameter("date", date));
		sb.append(addParameter("limit", Integer.toString(limit)));
		System.out.println(sb.toString());
		String result = getHTML(sb.toString());
		return result;
	}
	
	public static String getHTML(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString();
	   }
}
