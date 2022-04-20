package com.sumanasaha.checkoutservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith( SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = CheckoutServiceApplication.class)
class CheckoutServiceImplApplicationTests {

	private static final String SWAGGER_ENDPOINT = "/swagger";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void testSwagger(){
		ResponseEntity<String> response = restTemplate.getForEntity( SWAGGER_ENDPOINT, String.class );
		assertThat(response.getStatusCode()).isEqualTo( HttpStatus.OK );
		assertThat( response.getBody() ).contains( "swagger-ui" );
		assertThat( response.getHeaders().getContentType() ).isEqualTo( MediaType.TEXT_HTML );
	}

}
