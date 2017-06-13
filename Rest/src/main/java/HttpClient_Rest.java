import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.junit.Test;

public class HttpClient_Rest {
	String baseURL = "http://maps.googleapis.com";
	String path = "/maps/api/geocode/json";
	@Test
	public void testStatus() throws URISyntaxException, IOException{
		String url = baseURL + path;
	    URIBuilder uriBuilder = new URIBuilder();
	    uriBuilder.setPath(url);
        uriBuilder.setParameter("address","6500 Dublin Blvd, Dublin, CA");
        uriBuilder.setParameter("key", "AIzaSyCDGo-krDrpU6MuNV0udWj6GyJ82Wt0gqo");
        URI uri = uriBuilder.build();
        
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		
		try {
			response1.addHeader("Content-Type","application/json");
		    System.out.println(response1.getStatusLine());
		    assertTrue(response1.getStatusLine().getStatusCode()==200);
		    System.out.println(response1.getStatusLine());
		    System.out.println(response1.toString());
		     
		    JSONObject json = new JSONObject(response1);
		    System.out.println(json.length());
		    
		    //System.out.println(json.get("results.address_components"));
		    //equals(json.length()==522);
		    //System.out.println(json);

  
		} finally {
		    response1.close();
		}
	}

}
