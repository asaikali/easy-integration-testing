package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
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

@Testcontainers
@ActiveProfiles("test")
@DisplayName("In Browser Test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
    driver.get(serverUrl("/"));

    assertThat(driver.getTitle()).isEqualTo("Billboard");

    FluentWait<WebDriver> wait = new FluentWait<>(driver);
    wait.pollingEvery(Duration.ofSeconds(2));
    wait.withTimeout(Duration.ofSeconds(30));
    wait.ignoring(NoSuchElementException.class);

    List<String> messages = new ArrayList<>();

    wait.until(webDriver -> {
      WebElement element = webDriver.findElement(By.id("message"));
      String message = element.getText();
      messages.add(message);
      if (messages.size() >= 10) {
        return true;
      } else {
        return false;
      }
    });

    assertThat(messages).containsAnyOf(
        "Failure is success in progress -- Anonymous",
        "Never, never, never give up -- Winston Churchill",
        "The shortest answer is doing -- Lord Herbert",
        "Success demands singleness of purpose -- Vincent Lombardi",
        "While there's life, there's hope -- Marcus Tullius Cicero"
    );
  }

  public static class Initializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      applicationContext
          .addApplicationListener((ApplicationListener<WebServerInitializedEvent>) event -> {

            /*
             * When testing with Selenium WebDriver, chrome is running inside a docker container.
             * SpringBoot app is running outside of docker on the docker host, therefore, you must
             * allow the docker container running chrome to open a network connection to the host
             * machine. Testcontainers.exposeHostPorts() must be called before the chrome container
             * is launched by this Junit5 test. Therefore, we us a Spring ApplicationContextInitializer
             * to configure Test Containers after Spring Boot has launched the app server but before
             * any JUnit5 methods or configuration executes. From with a docker contanier lanuched by
             * Test containers you can connect to the host docker machine via
             * "http://host.testcontainers.internal"
             */
            org.testcontainers.Testcontainers.exposeHostPorts(event.getWebServer().getPort());
          });
    }
  }
}
