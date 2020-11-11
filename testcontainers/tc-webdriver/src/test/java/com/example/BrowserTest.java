package com.example;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("In Browser Test")
@Testcontainers
@ContextConfiguration(initializers = BrowserTest.Initializer.class)
class BrowserTest {

  @Container
  private BrowserWebDriverContainer<?> chrome = new BrowserWebDriverContainer<>()
      .withCapabilities(new ChromeOptions())
      .withRecordingMode(VncRecordingMode.RECORD_ALL, new File("./target/"));

  @LocalServerPort
  private int localPort;

  private String serverUrl(String url) {
    return "http://host.testcontainers.internal:" + localPort + url;
  }

  @Test
  @DisplayName("Test Home Page Loads Quotes")
  public void testHomePage() {
    RemoteWebDriver driver = chrome.getWebDriver();
    driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
    driver.get(serverUrl("/"));
    System.out.println(driver.getTitle());
  }

  public static class Initializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      applicationContext.addApplicationListener((ApplicationListener<WebServerInitializedEvent>) event -> {
        org.testcontainers.Testcontainers.exposeHostPorts(event.getWebServer().getPort());
      });
    }
  }
}
