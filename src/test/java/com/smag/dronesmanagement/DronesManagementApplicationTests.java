package com.smag.dronesmanagement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DronesManagementApplication.class)
      @AutoConfigureMockMvc
      @TestPropertySource(
        locations = "classpath:application.properties")
@DisplayName("Test Class Application")
class DronesManagementApplicationTests {

	@Test
	void contextLoads() {
	}

}
