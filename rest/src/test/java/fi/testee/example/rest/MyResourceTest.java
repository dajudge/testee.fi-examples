package fi.testee.example.rest;

import fi.testee.junit4.TestEEfi;
import fi.testee.rest.RestServer;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(TestEEfi.class)
public class MyResourceTest {
    private final OkHttpClient client = new OkHttpClient();

    @Resource
    private RestServer restServer;

    @Test
    public void returns_request() throws IOException {
        // Prepare the request body
        final JSONObject requestBody = new JSONObject();
        requestBody.put("value", "Hello world");

        // Get the port at which our test server is running
        final int port = restServer.getPort();
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .post(RequestBody.create(MediaType.parse("application/json"), requestBody.toString()))
                .url("http://localhost:" + port + "/rest/example/resource")
                .build();

        // Assert the response
        final Response response = client.newCall(request).execute();
        try (final ResponseBody body = response.body()) {
            final String string = body.string();
            assertEquals("Wrong response code. Body: " + string, 200, response.code());
            assertEquals("Hello world", new JSONObject(string).get("value"));
        }
    }

}
