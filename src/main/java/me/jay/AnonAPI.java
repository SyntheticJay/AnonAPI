package me.jay;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import me.jay.exception.GetRequestException;
import me.jay.exception.InvalidTokenException;
import me.jay.exception.PostRequestException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;

/**
 * @author Jay, 2018
 *
 * TODO: Utilize the api token.
 *
 * This is AnonAPI, a simple library designed to contact AnonFile
 * to send 'get' and 'post' requests with ease.
 */
public class AnonAPI {
    /*
     * API Token for account uploading or anything of the sort.
     */
    private String apiToken;

    /**
     * A variable which is assigned to true if the programmer is using an account token.
     */
    private boolean usingAccount;

    /**
     * GSON instantiation
     */
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Constructor which passes in the APIToken if you want to upload straight from your account.
     * @param apiToken
     */
    public AnonAPI(String apiToken) {
        /*
         * If token is equal to null.
         */
        if (apiToken == null || apiToken == "") {
            this.usingAccount = false;
            throw new InvalidTokenException();
        } else {
            this.usingAccount = true;
            this.apiToken = apiToken;
        }
    }

    /**
     * Contact's AnonAPI with a 'post' request to upload the specified file.
     * @param file - File to upload
     * @return - URL which can be reformed into a string.
     */
    public String upload(File file) {
       try {
           /* Creating response */
           HttpResponse<JsonNode> response = Unirest.post("https://api.anonfiles.com/upload").field("file", file).asJson();
           /* Instantiating body as a JSONObject */
           JSONObject object = response.getBody().getObject();
           /* Creating a new string combining the data, file, url, with the string short */
           String url = object.getJSONObject("data")
                   .getJSONObject("file")
                   .getJSONObject("url")
                   .getString("short");
           /* returning the body */
           return new URL(url).toString();
       }catch(Exception e) {
           throw new PostRequestException();
       }
    }

    /**X
     * Contact's AnonAPI with a 'get' request to return a STRING based javascript object notation based text about the specified file.
     * @param id - File id
     * @return - Response body
     */
    public String get(String id) {
        try {
            /* Create response */
            HttpResponse<JsonNode> response = Unirest.get("https://api.anonfiles.com/v2/file/{id}/info".replace("{id}", id)).asJson();
            /* Instantiate a new variable with the body as the value. */
            String body = response.getBody().toString();
            /* Instantiate our parser */
            JsonParser parser = new JsonParser();
            /* Instantiate our JsonElement which parses through the body using JsonParser */
            JsonElement element = parser.parse(body);
            /* Return the body but with pretty formatting as stated in the GSON variable #setPrettyPrinting() */
            return gson.toJson(element);
        }catch(Exception e) {
            e.printStackTrace();
            throw new GetRequestException();
        }
    }

    /**
     * Contact's AnonAPI with a 'get' request to return the javascript object notation based text but in the form of a JSONObject, may be easier for people who want to
     * select out the values they want/
     * @param id - File id
     * @return - JSONObject
     */
    public JSONObject getAsObject(String id) {
        try {
            /* Create response */
            HttpResponse<JsonNode> response = Unirest.get("https://api.anonfiles.com/v2/file/{id}/info".replace("{id}", id)).asJson();
            /* Return the body as a JSONObject */
            return response.getBody().getObject();
        }catch(Exception e) {
            throw new GetRequestException();
        }
    }

    /**
     * Simple substring to return ONLY the id of the file.
     * @param link - Link
     * @return - The ID
     */
    public String getID(String link) {
        return link.substring("https://anonfile.com/".length());
    }
}
