package cryptoTrader.Trading;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * The AvailableCryptoList class contains implementation for getting all available cryptos
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class AvailableCryptoList {
    private static AvailableCryptoList instance = null;

    private final Map<String, String> availableCryptosMap = new HashMap<>();
    private final List<String> availableCryptosList = new ArrayList<>();

    private AvailableCryptoList() {
        findAvailableCryptos();
    }

    public static AvailableCryptoList getInstance() {
        if (instance == null)
            instance = new AvailableCryptoList();

        return instance;
    }

    /**+
     * method to make api call for fetching available cryptos
     */
    private void findAvailableCryptos() {
        String urlString =
                "https://api.coingecko.com/api/v3/coins/markets" +
                        "?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";
//		ALPHAVANTAGE API KEY = VNEY4VV2AWF1EB51
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            if (responsecode == 200) {
                String inline = "";
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                sc.close();
                JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
                int size = jsonArray.size();

                String name, id, symbol;
                for (int i = 0; i < size; i++) {
                    JsonObject object = jsonArray.get(i).getAsJsonObject();
                    name = object.get("name").getAsString();
                    id = object.get("id").getAsString();
                    symbol = object.get("symbol").getAsString();

                    availableCryptosMap.put(symbol.toUpperCase(), id);
                    availableCryptosList.add(symbol);
                }
            }

        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**+
     * method to get the coin id from symbol
     * @param cryptoSymbol coin symbol
     * @return coin id
     */
    public String getCryptoID(String cryptoSymbol) {
        return availableCryptosMap.get(cryptoSymbol);
    }

}
