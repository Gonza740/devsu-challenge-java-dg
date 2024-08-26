package com.devsu.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private Gateway gateway;
    private Contact contact;
    private Info info;
    @Data
    public static class Contact {
        private String email;
        private String name;
        private String url;
    }

    @Data
    public static class Info {
        private String title;
        private String version;
        private String description;
    }

    @Data
    public static class Gateway{
        private String url;
    }
}
