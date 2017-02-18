package hello.rest.client;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import hello.greeting.Greeting;
import lombok.extern.log4j.Log4j;

@Log4j
public class SpringRestClient {
	
	private static String clientCredentials = "spring-client:secret";
	private static String REST_SERVICE_URI = "http://localhost:8080";
	private static String AUTH_URI = REST_SERVICE_URI + "/oauth/token";
	private static String PASSWORD_GRANT = "?grant_type=password&username=Viktor&password=Viktor";
	private static String ACCESS_TOKEN = "?access_token=";
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}
	
	private HttpHeaders getHeadersWithClientCredentials() {
		HttpHeaders headers = getHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encode(clientCredentials.getBytes())));
		return headers;
	}
	
	private String sendTokenRequest() {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		ResponseEntity<Object> response = restTemplate.exchange(AUTH_URI + PASSWORD_GRANT, HttpMethod.POST, request, Object.class);
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)response.getBody();
		if(map != null) {
			return (String)map.get("access_token");
		} else {
			log.error("no access token is returned!");
			return "";
		}
	}
	
	public String greeting(String name) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(getHeaders());
		ResponseEntity<Greeting> response = restTemplate.exchange(REST_SERVICE_URI + "/greeting" + ACCESS_TOKEN + sendTokenRequest(), 
						HttpMethod.GET, request, Greeting.class);
		Greeting greeting = response.getBody();
		return greeting.getContent();
	}
	
	public static void main(String[] args) {
		SpringRestClient client = new SpringRestClient();
		String result = client.greeting("");
		System.out.println(result);
	}
}
