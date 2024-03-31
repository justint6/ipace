package com.tayhantechnologies.ipace.Entities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created by jt070 on 11/04/2018.
 * This Class is used to so CORS doesn't stop the REST addresses being access correctly. The below /** sets this to allow the request to
 * done from anywhere.
 */
@Configuration
public class RestConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", config);
        CorsFilter bean = new CorsFilter(source);
        return bean;
    }
}
