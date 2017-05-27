package rest.rest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import com.github.tomakehurst.wiremock.WireMockServer;

import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class RestRunning2 {
	
	WireMockServer wireMockServer = new WireMockServer();
	CloseableHttpClient httpClient = HttpClients.createDefault();
	
	

	public RestRunning2(){
		super();
		//get();
	}
	
		
	public JSONObject jprocessing() {
	    try {
	        JSONParser parser = new JSONParser();
	        //Use JSONObject for simple JSON and JSONArray for array of JSON.
	        JSONObject data = (JSONObject) parser.parse(
	              new FileReader("src/test/resources/jsonString.json"));//path to the JSON file.

	        String json = data.toJSONString();
	        return data;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
		
	}
	
	
	private String convertResponseToString(HttpResponse response) {
		InputStream responseStream;
		try {
			responseStream = response.getEntity().getContent();
		    Scanner scanner = new Scanner(responseStream, "UTF-8");
		    String responseString = scanner.useDelimiter("\\Z").next();
		    scanner.close();
		    return responseString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@When("^users upload data on a project$")
	public void users_upload_data_on_a_project() throws Throwable {
			//stub here
			wireMockServer.start();
			
			configureFor("localhost", 8080);
			stubFor(post(urlEqualTo("/create"))
			  .withHeader("content-type", equalTo("application/json"))
			  .withRequestBody(containing("testing-framework"))
			  .willReturn(aResponse().withStatus(200)));

	}

	@Then("^the server should handle it and return a success status$")
	public void the_server_should_handle_it_and_return_a_success_status() throws Throwable {
		
		HttpPost request = new HttpPost("http://localhost:8080/create");
		JSONObject data =jprocessing();
		String jsonString=data.toString();
		StringEntity entity;
		try {
			entity = new StringEntity(jsonString);
			System.out.println(jsonString);
			request.addHeader("content-type", "application/json");
			request.setEntity(entity);
		
			HttpResponse response = httpClient.execute(request);
			System.out.println(response);

			assertEquals(200, response.getStatusLine().getStatusCode());
//			System.out.println(postRequestedFor(urlEqualTo("/create")));
			verify(postRequestedFor(urlEqualTo("/create"))
			  .withHeader("content-type", equalTo("application/json")));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		wireMockServer.stop();
	}

	@When("^users want to get information on the Cucumber project$")
	public void users_want_to_get_information_on_the_Cucumber_project() throws Throwable {
		wireMockServer.start();
		 
		JSONObject data =jprocessing();
		String jsonString=data.toString();
		
		configureFor("localhost", 8080);
		stubFor(get(urlEqualTo("/projects/cucumber"))
		  .withHeader("accept", equalTo("application/json"))
		  .willReturn(aResponse().withBody(jsonString)));
		
	}

	@Then("^the requested data is returned$")
	public void the_requested_data_is_returned() throws Throwable {
		HttpGet request = new HttpGet("http://localhost:8080/projects/cucumber");
		request.addHeader("accept", "application/json");
		try {
			HttpResponse httpResponse = httpClient.execute(request);
			String responseString = convertResponseToString(httpResponse);
			assertThat(responseString, containsString("testing-framework\":\"cucumber"));
			assertThat(responseString, containsString("website\":\"cucumber.io"));
			verify(getRequestedFor(urlEqualTo("/projects/cucumber"))
			  .withHeader("accept", equalTo("application/json")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		wireMockServer.stop();
	}

}
