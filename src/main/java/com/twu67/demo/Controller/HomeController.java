package com.twu67.demo.Controller;


import com.twu67.demo.CounterVisit;
import com.twu67.demo.model.CreditCard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;

@RestController
@Api("swaggerTestController")
public class HomeController {
  private static final String DEFAULT_URL = "https://test-payment-gateway.freewheelers.bike/authorise";


  private final CounterVisit counterVisit;

  public HomeController(CounterVisit counterVisit) {
    this.counterVisit = counterVisit;
  }

  @ApiOperation(value = "home page", notes = "visit home page")
  @GetMapping(value = "/")
  public String homePage() {
    counterVisit.Increment();
    return "This is home page.";
  }

  @ApiOperation(value = "demo", notes = "to show the use of swagger ui")
  @PostMapping(value = "/pay")
  public String payByCard(@RequestParam String ccNumber, @RequestParam String csc, @RequestParam String expiry, @RequestParam Double amount) {
    RestTemplate restTemplate = new RestTemplate();
    try {
      URI paymentGateway = new URL(DEFAULT_URL).toURI();
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_XML);

      String xmlString = new CreditCard(ccNumber, csc, expiry).toString(amount);

      HttpEntity<String> request = new HttpEntity<>(xmlString, headers);
      ResponseEntity<String> response = restTemplate.postForEntity(paymentGateway, request, String.class);
      return response.getBody();
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }
}