import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers.*;


public class Rest_Assured {
	String baseURL = "https://itunes.apple.com";
	String path = "/lookup?id=909253";
	
	
	@Test
	public void testStatus(){
		String url = baseURL + path;
		int statuscode = given()
				.contentType(ContentType.TEXT)
				.get(url)
				.statusCode();
		
		System.out.println("StatusCode:" + statuscode);
		assertTrue(statuscode == 200);
}
	@Test
	public void testHeaderContent(){
		String url = baseURL + path;
		String headerline = with()
				.contentType(ContentType.TEXT)
				.get(url).andReturn().contentType();	
			assertTrue(headerline.contentEquals("text/javascript; charset=utf-8"));
			System.out.println("status=" + headerline);
		 }
	@Test
	public void testStatusOK(){
		String url = baseURL + path;
		String statusline = given()
			.contentType(ContentType.TEXT)
			.get(url).thenReturn().statusLine();	
		assertTrue(statusline.contains("OK"));
		System.out.println("status=" + statusline);
	}
	@Test
	public void testElementResponse() throws IOException{
		String url = baseURL + path;
		String response = given()
			.contentType(ContentType.TEXT)
			.get(url)	
		.then()
		.extract()
		.response().asString();
		
		boolean results = response.matches("results");
		System.out.println(response);
		assertNotNull(results);
		}
	@Test
	public void testElementCount(){
		
		String url = baseURL + path;
		String json = given()
			.contentType(ContentType.TEXT)
			.get(url)	
			.asString();
		
		JsonPath jsonpath = new JsonPath(json).setRoot("results");
		List<String> address_comp = jsonpath.get();
		System.out.println(address_comp);
		System.out.println(address_comp.size());
		assertTrue(address_comp.size() == 1);
	}
	
}