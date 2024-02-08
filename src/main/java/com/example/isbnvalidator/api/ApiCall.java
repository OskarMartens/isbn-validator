package com.example.isbnvalidator.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;



@Service
public class ApiCall {

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    private HttpRequest buildHttpRequest(String isbn) {

        return HttpRequest
                .newBuilder()
                .uri(URI.create("https://openlibrary.org/search.json?q=" + isbn))
                .build();
    }

    private HttpResponse<String> getHttpResponse(String isbn) throws IOException, InterruptedException {
        HttpRequest request = buildHttpRequest(isbn);
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private String bookNameFromJson(String jsonBody) {

        JSONObject jsonObject = new JSONObject(jsonBody);
        JSONArray docsArray = jsonObject.getJSONArray("docs");
        JSONObject docsObject = docsArray.getJSONObject(0);
        return docsObject.getString("title");
    }

    public String getBookTitle(String isbn) throws IOException, InterruptedException{
        HttpResponse<String> response = getHttpResponse(isbn);
        return bookNameFromJson(response.body());
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        ApiCall apiCall = new ApiCall();
        System.out.println(apiCall.getBookTitle("014143984X"));
    }
}
