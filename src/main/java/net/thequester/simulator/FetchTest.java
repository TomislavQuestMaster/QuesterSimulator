package net.thequester.simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.thequester.websupport.model.Filter;
import net.thequester.websupport.model.QuestDetails;
import net.thequester.websupport.serializator.JsonSerializer;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tdubravcevic
 */
public class FetchTest {

	public static void main(String[] args) throws Exception {

		Filter filter = new Filter(45.793364, 15.946323, 1113.32);

		JsonSerializer serializer = new JsonSerializer();
		String json = serializer.serialize(filter);

		String quests = sendPost("Cvjetno");


		//List<QuestDetails> details = (List<QuestDetails>) serializer.deserializeList(quests, QuestDetails.class);

		//System.out.println(details.get(0).getQuestName());

		//DownloadTest.saveFileFromUrlWithJavaIO("new.txt", "http://localhost:8080/download");
	}


	private static String sendPost(String json) throws Exception {

		String url = "http://localhost:8080/download";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


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
		String quests = response.toString();

		System.out.println(quests);

		return quests;
	}

}
