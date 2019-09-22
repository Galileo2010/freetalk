package com.twu67.demo;

import com.twu67.demo.Controller.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

  @Autowired
  private HomeController homeController;

  @Test
  public void shouldReturnSuccess() {
    assertThat(homeController.homePage()).isEqualTo("This is home page.");
    assertThat(homeController.payByCard("4111111111111111", "534", "11-2020", 52.04)).contains("SUCCESS");
  }
}
