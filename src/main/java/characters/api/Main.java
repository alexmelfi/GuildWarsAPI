package characters.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            //https://api.guildwars2.com/v2/account?access_token=42320C11-95A9-214D-A5F9-8CF38EF39753C20F18CD-15DB-4530-8689-549B96E1EC4D

            String JSONString = getJSON("https://api.guildwars2.com/v2/characters?access_token=");

            Gson gson = new Gson();
            List<String> chars = gson.fromJson(JSONString, new TypeToken<List<String>>(){}.getType());

                /*
                // This block was for testing JSON reading.
                String toReplace = JSONString.toString().replaceAll("\\s", "\n");
                for (int i = 0; i < 5; i++)
                    toReplace = toReplace.replaceAll("\n\n", "\n");

                System.out.println(toReplace);
                */

            List<Player> players = new ArrayList<Player>();
            for (int i = 0; i < chars.size(); i++) {

                JSONString = getJSON("https://api.guildwars2.com/v2/characters/" +
                            chars.get(i) + "/core?access_token=");

                players.add(new Gson().fromJson(JSONString, Player.class));
                System.out.println(players.get(i).toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJSON(String u) {

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