package net.thequester.simulator;


import net.thequester.websupport.model.QuestDetails;
import net.thequester.websupport.serializator.JsonSerializer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author tdubravcevic
 */
public class DownloadTest {


    public static void main(String[] args) {

        // Make sure that this directory exists
        try {

            System.out.println("Downloading");

            QuestDetails details = new QuestDetails();
            details.setId(1);
            details.setQuestName("Cvjetno");
            details.setOwner("tomo");

            JsonSerializer serializer = new JsonSerializer();
            String quests = serializer.serialize(details);

            saveFileFromUrlWithJavaIO("quest.zip", quests);

            System.out.println("Downloaded");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveFileFromUrlWithJavaIO(String fileName, String json) throws IOException {

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

        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(con.getInputStream());
            fout = new FileOutputStream(fileName);

            byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null)
                in.close();
            if (fout != null)
                fout.close();
        }
    }

}
