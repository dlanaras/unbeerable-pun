package unbeerable_pun.github;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BeerAdmin {
    public static Map<Integer, String> styles = new HashMap<>();
    public static Map<String, Beer> beers = new HashMap<>();

    public static String apiKey;

    public static void loadConfig() throws IOException {
        String fileName = "config.json";
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(br);

        apiKey = node.get("apiKey").asText();
    }

    public static JsonNode apiCall(String endpoint, String params) throws IOException {
        URL url = new URL("https://api.brewerydb.com/v2/" + endpoint + "?" + params
                + "&key=" + apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(responseStream);
        return node;
    }

    public static JsonNode apiCall(String endpoint) throws IOException {
        return apiCall(endpoint, "");
    }

    public static void loadBeerStyles() throws IOException {
        JsonNode node = BeerAdmin.apiCall("styles");
        for (JsonNode style : node.get("data")) {
            styles.put(style.get("id").asInt(), style.get("name").asText());
        }
    }

    public static void printBeerStyles() {
        for (Map.Entry<Integer, String> entry : styles.entrySet()) {
            System.out.println(entry.getKey() + "::" + entry.getValue());
        }
    }

    public static void printBeerStyles(String search) {
        for (Map.Entry<Integer, String> entry : styles.entrySet()) {
            if (entry.getValue().contains(search)) {
                System.out.println(entry.getKey() + "::" + entry.getValue());
            }
        }
    }

    public static int getBeerListForStyle(int styleId, int page) throws IOException {
        JsonNode node = BeerAdmin.apiCall("beers", "styleId=" + styleId + "&p=" + page);
        for (JsonNode beer : node.get("data")) {
            Beer b = new Beer();
            b.name = beer.get("name").asText();
            if (beer.has("description"))
                b.description = beer.get("description").asText();
            beers.put(beer.get("id").asText(), b);
        }

        return node.get("numberOfPages").asInt();
    }

    public static void getBeerListForStyle(int styleId) throws IOException {
        int numPages = getBeerListForStyle(styleId, 1);
        for (int i = 2; i <= numPages; i++) {
            getBeerListForStyle(styleId, i);
        }
    }

    public static void printBeerList() {
        for (Map.Entry<String, Beer> entry : beers.entrySet()) {
            System.out.println(entry.getKey() + "::" + entry.getValue().name);
        }
    }

    public static void printBeer(String id) {
        Beer b = beers.get(id);
        System.out.println(id + "::" + b.name);
        System.out.println(b.description);
    }
}
