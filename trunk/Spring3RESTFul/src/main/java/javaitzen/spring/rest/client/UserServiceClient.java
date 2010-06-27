package javaitzen.spring.rest.client;

import javaitzen.spring.rest.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("userClient")
public class UserServiceClient {

    @Autowired
    protected RestTemplate restTemplate;
    private final static String serviceUrl = "http://127.0.0.1:7001/Spring3RESTFul-1/app/users/";

    public User getUserDetails(String firstName) {
        return restTemplate.getForObject(serviceUrl + "/{firstName}", User.class, firstName);
    }

}
