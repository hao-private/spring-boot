package hello.rest.client;

import org.junit.Assert;
import org.junit.Test;

import com.connected.rest.client.SpringRestClient;


public class SpringRestClientTest {

	@Test
	public void test() {
		SpringRestClient client = new SpringRestClient();
		Assert.assertEquals("Hello, World!", client.greeting(""));
	}

}
