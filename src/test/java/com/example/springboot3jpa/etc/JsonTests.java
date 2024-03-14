package com.example.springboot3jpa.etc;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonTests {

    @Test
    void jsonTest() {
        try {
            HttpClient client = HttpClients.custom().build();
            HttpUriRequest request = RequestBuilder.get()
                    .setUri("http://localhost:8080")
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .build();
            HttpResponse response = client.execute(request);

            StringBuilder stringBuilder = new StringBuilder();
            try {
                InputStream ips = response.getEntity().getContent();
                InputStreamReader sr = new InputStreamReader(ips);
                BufferedReader br = new BufferedReader(sr);

                while (br.ready()) {
                    String line = br.readLine();
                    System.out.println(line);
                    stringBuilder.append(line);
                }

            } catch (Exception e) {
                e.getCause();
            }
            String json = stringBuilder.toString();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json);
            JSONArray imagesResults = (JSONArray) obj.get("images_results");

            List<HashMap<String, Object>> list = new ArrayList<>();
            for (int i = 0; i < imagesResults.size(); i++) {
                JSONObject image = (JSONObject) imagesResults.get(i);

                HashMap<String, Object> map  = new HashMap<String, Object>();
                map.put("position", Integer.parseInt(image.get("original_width").toString()));
                map.put("thumbnail", image.get("link").toString());
                map.put("related_content_id", image.get("link").toString());
                map.put("source", Boolean.parseBoolean(image.get("is_product").toString()));
                map.put("source_logo", Integer.parseInt(image.get("original_height").toString()));
                map.put("title", image.get("source").toString());
                map.put("link", Integer.parseInt(image.get("position").toString()));
                map.put("original", image.get("thumbnail").toString());
                map.put("original_width", image.get("source_logo").toString());
                map.put("original_height", image.get("serpapi_related_content_link").toString());
                list.add(map);
            }
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
