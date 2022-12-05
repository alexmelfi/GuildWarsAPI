package characters.api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class JSONReader {public static String getJSON(String u) {

    StringBuilder informationString = new StringBuilder();
    try {
        String apiKey = "A50898E0-A073-6E43-853B-1EC39916F8BEB0122E7D-BBC7-4EF6-994C-55F0CA4EC1E0";
        URL url = new URL(u + apiKey);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Check if connect is made
        int responseCode = conn.getResponseCode();

        // 200 OK
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            //Close the scanner
            scanner.close();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return informationString.toString();
}
}
