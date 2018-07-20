import com.google.gson.JsonObject;
import me.jay.AnonAPI;
import org.json.JSONObject;

import java.io.File;

/**
 * @author Jay, 2018
 */
public class AnonTest {
    private static AnonAPI api = new AnonAPI("b298008a2fe1cdf4");

    public static void main(String... args) {
        try {
            File file = new File(System.getProperty("user.dir") + "/test.jpg");
            System.out.println("Contacting with a 'post' request for file: " + file.getName());
            String URL = api.upload(file);
            System.out.println(URL + "\r\n");

            System.out.println("Contacting with a 'get' request for file: " + file.getName());
            String id = URL.substring(api.getID("https://anonfile.com/L4m6w1feb0\n"));
            System.out.println("ID: " + id);
            System.out.println("Body: \r\n" + api.get(id));

            System.out.println("Contacting with a 'get' request but printing out in the form of a jsonobject as returned.\r\n");
            JSONObject contacted = api.getAsObject(id);
            JSONObject object = contacted.getJSONObject("data");
            //print out stuff
            System.out.println("full-url: " + object.getJSONObject("file").getJSONObject("url").get("full"));
            System.out.println("short-url: " + object.getJSONObject("file").getJSONObject("url").get("short"));

            JSONObject metadataObject = contacted.getJSONObject("data").getJSONObject("file").getJSONObject("metadata");

            System.out.println("id: " + metadataObject.get("id"));
            System.out.println("name: " + metadataObject.get("name"));
            System.out.println("Size-Bytes: " + metadataObject.getJSONObject("size").get("bytes"));
            System.out.println("Size-Readable: " + metadataObject.getJSONObject("size").get("readable"));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
