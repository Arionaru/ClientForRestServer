package ru.ariona.clientforrestserver;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class UserManagementClient {

    private final String HOST = "http://localhost:8080/api/users/";
    private Gson gson = new Gson();
    private CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public String getUsers() throws IOException{
        HttpGet httpGet = new HttpGet(HOST);
        return getResponse(httpGet);
    }

    public String getUserById(Long id) throws IOException{
        HttpGet httpGet = new HttpGet(HOST + id);
        return getResponse(httpGet);
    }

    private String getResponse(HttpGet httpGet) throws IOException{
        return httpClient.execute(httpGet, httpResponse -> {
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpResponse.getEntity().getContent()));

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                return sb.toString();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        });
    }

    public String addUser(User user)  {
        HttpPost httpPost = new HttpPost(HOST);

        String response = null;
        try {
            httpPost.setHeader("Content-type","application/json; charset=UTF-8");
            httpPost.setEntity(new StringEntity(gson.toJson(user),"UTF-8"));
            response = httpClient.execute(httpPost, getResponseHandler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String editUser(User user) {
        HttpPut httpPut = new HttpPut(HOST);
        String response = null;
        try {
            httpPut.setEntity(new StringEntity(gson.toJson(user), "UTF-8"));
            httpPut.setHeader("Content-type", "application/json; charset=UTF-8");
            response = httpClient.execute(httpPut, getResponseHandler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String deleteUser(Long id) {
        HttpDelete httpDelete = new HttpDelete(HOST + id);
        String response = null;
        try {
            response = httpClient.execute(httpDelete, getResponseHandler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private ResponseHandler<String> getResponseHandler() {
        return response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }
}
