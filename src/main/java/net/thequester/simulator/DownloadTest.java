package net.thequester.simulator;


import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

            saveFileFromUrlWithJavaIO("new.txt", "http://localhost:8080/download");

            System.out.println("Downloaded");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveFileFromUrlWithJavaIO(String fileName, String fileUrl) throws IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(fileUrl).openStream());
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
