package ru.ariona.clientforrestserver;

import org.apache.http.client.ClientProtocolException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    private final String HOST = "http://localhost:8080/api/users/";

    private RestTemplate restTemplate;

    public UserService() {
        restTemplate = new RestTemplate();
    }

    public List<User> getUsers() throws ClientProtocolException {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(HOST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {});

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new ClientProtocolException("Unexpected response status: " + responseEntity.getStatusCodeValue());
        }

        return responseEntity.getBody();
    }

    public User getUserById(Long id) throws ClientProtocolException{
        System.out.println(restTemplate.getForEntity(HOST+id,User.class));
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(HOST+id,User.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new ClientProtocolException("Unexpected response status: " + responseEntity.getStatusCodeValue());
        }
        return responseEntity.getBody();
    }

    public void addUser(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders);
        restTemplate.put(HOST,httpEntity);
    }

    public void deleteUser(Long id) {
        restTemplate.delete(HOST+id);
    }
}
