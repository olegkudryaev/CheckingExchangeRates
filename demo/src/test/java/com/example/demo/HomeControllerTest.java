package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HomeControllerTest {
    @Autowired
    private HomeController controller;

    @Test
    void contextLoads() {
         assertThat(controller).isNotNull();
    }
}
