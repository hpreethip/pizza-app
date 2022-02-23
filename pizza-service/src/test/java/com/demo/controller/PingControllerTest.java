package com.demo.controller;

import static org.hamcrest.Matchers.hasToString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.constant.RequestUri;

@SpringBootTest
@AutoConfigureMockMvc
class PingControllerTest {
	
	/**
	 * Logger to log messages of {@link PingControllerTest}
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PingControllerTest.class);

	/**
	 * Autowired bean of {@link MockMvc}
	 */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Ping Pong test case using Mockito
     */
    @Test
    void pingShouldReturnPongMessage() {
    	LOGGER.info("Ping Pong test case");
        try {
			mockMvc.perform(get(RequestUri.PING))
			        .andDo(print())
			        .andExpect(status().isOk())
			        .andExpect(content().string(hasToString("pong")));
		} catch (Exception ex) {
			LOGGER.error("Exception in Ping Pong test case due to {}", ex.getMessage(), ex);
		}
    }
    
}
