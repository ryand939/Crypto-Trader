package cryptoTrader.Trading;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The DataFetcher class is a concrete implementation of IDataFetcher interface.
 * It contains implementation for fetching price of coins by making api calls
 *
 * @author Ryan Douglas Daer (rdaer2), Chang Hui Hou (chou24), Inderjit Singh (iinderji), Maxime Savehilaghi (msavehil)
 */
public class DataFetcher implements IDataFetcher {

    /**+
     * method to make api call to fetch coin prices
     * @param ids csv of coins for which we want to fetch the price
     * @return json object containing coin id and price
     */
    private JsonObject getDataForCryptos(String ids) {

        String urlString = String.format(
                "https://api.coingecko.com/api/v3/simple/price?ids=%s&vs_currencies=usd", ids);


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
                JsonObject jsonObject = new JsonParser().parse(inline).getAsJsonObject();
                return jsonObject;
            }

        } catch (IOException e) {
            System.out.println("Something went wrong with the API call.");
        }
        return null;
    }

    /**+
     * method to fetch coin prices
     * @param coinList List of coins for which we want to fetch the price
     * @return map containing coin id and price
     */
    @Override
    public Map<String, Double> getPricesForCoinList(List<String> coinList) {
        String ids = String.join(",", coinList);
        double price = 0.0;

        JsonObject jsonObject = getDataForCryptos(ids);

        Map<String, Double> coinPrice = new HashMap<String, Double>();

        if (jsonObject != null) {
            for (String coin : coinList) {
                coinPrice.put(coin, jsonObject.get(coin).getAsJsonObject().get("usd").getAsDouble());
            }
        }
        return coinPrice;
    }

}
