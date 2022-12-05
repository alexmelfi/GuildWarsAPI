package characters.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            //https://api.guildwars2.com/v2/account?access_token=42320C11-95A9-214D-A5F9-8CF38EF39753C20F18CD-15DB-4530-8689-549B96E1EC4D

            String JSONString = JSONReader.getJSON("https://api.guildwars2.com/v2/characters?access_token=");

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

                JSONString = JSONReader.getJSON("https://api.guildwars2.com/v2/characters/" +
                            chars.get(i) + "/core?access_token=");

                players.add(new Gson().fromJson(JSONString, Player.class));
                System.out.println(players.get(i).toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}