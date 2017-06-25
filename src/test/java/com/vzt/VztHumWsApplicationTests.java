package com.vzt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@org.springframework.boot.test.IntegrationTest("server.port:0")
public class VztHumWsApplicationTests {

	@Test
	public void contextLoads() {
	}

}
