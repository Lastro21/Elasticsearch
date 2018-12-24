package com.elasticsearch.elasticsearch.controllers;

import com.elasticsearch.elasticsearch.models.TestUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
final public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);
    private static final String strPUT = "http://localhost:9200/test_index/_doc/1";

    private TestUser testUser;
    private HttpHeaders header;
    private RestTemplate restTemplate;

    @RequestMapping(value = "/")
    public void firstController() {

        LOGGER.info("Access to the controller");

        testUser.setName("Any_name");
        testUser.setAge(12345);
        testUser.setCurrentDate(new Date().toString());

        header.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<TestUser> requestBody = new HttpEntity<>(testUser, header);

        try {
            ResponseEntity<Void> resultQuery = restTemplate.exchange(strPUT, HttpMethod.PUT, requestBody, Void.class);
            if (resultQuery.getStatusCode().toString() == "200 OK" || resultQuery.getStatusCode().toString() == "201 OK") {
                LOGGER.info("PUT request failed");
            } else {
                LOGGER.info("PUT request completed");
            }
        } catch (RestClientException e) {
            LOGGER.debug("Error access Elasticsearch " + e);
        }
    }

    @Autowired
    public void setTestUser(TestUser testUser) {
        this.testUser = testUser;
    }
    @Autowired
    public void setHeader(HttpHeaders header) {
        this.header = header;
    }
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
