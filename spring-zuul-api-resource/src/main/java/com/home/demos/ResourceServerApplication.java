package com.home.demos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.StringJoiner;

@SpringBootApplication
@EnableWebMvc
@RestController
public class ResourceServerApplication extends SpringBootServletInitializer {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

    @GetMapping("/api/{id}")
    public ApiResponse apiMethod(@PathVariable final long id, HttpServletRequest req, HttpServletResponse res) throws UnknownHostException {
        final StringJoiner additionalInfo = new StringJoiner("; ");
        if (req.getHeader("end-to-end-id") != null) {
            additionalInfo.add(String.format("end-to-end-id: %s", req.getHeader("end-to-end-id")));
        }

        if (req.getHeader("route-address") != null) {
            additionalInfo.add(String.format("route-address: %s", req.getHeader("route-address")));
        }

        return new ApiResponse(
                new Random().nextLong(),
                String.format(
                        "Successfully routed to %s:%s for resource %s",
                        InetAddress.getLocalHost().getHostAddress(),
                        environment.getProperty("server.port"),
                        id
                ),
                String.format("custom headers received: %s", additionalInfo.toString())
        );
    }

}
