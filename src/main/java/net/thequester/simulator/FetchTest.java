package net.thequester.simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.thequester.websupport.model.Filter;
import net.thequester.websupport.serializator.JsonSerializer;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author tdubravcevic
 */
public class FetchTest {

	public static void main(String[] args) throws Exception {

		Filter filter = new Filter(0.0,0.0,123.0);

		JsonSerializer serializer = new JsonSerializer();
		String json = serializer.serialize(filter);

		sendPost(json);
	}


	private static void sendPost(String json) throws Exception {

		String url = "http://localhost:8080/fetch";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(json);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Body : " + json);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

}
