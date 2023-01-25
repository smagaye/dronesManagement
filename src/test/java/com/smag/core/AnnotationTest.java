package com.smag.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.smag.dronesmanagement.DronesManagementApplication;

public interface AnnotationTest {

    @Target( {ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Tag("UnitTest")
    public @interface UnitTest {
    }

    @Target( {ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @SpringBootTest(
            webEnvironment = SpringBootTest.WebEnvironment.MOCK,
            classes = DronesManagementApplication.class)
          @AutoConfigureMockMvc
          @TestPropertySource(
            locations = "classpath:application.properties")
    @Tag("IntegrationTest")
    public @interface IntegrationTest {
    }
}
