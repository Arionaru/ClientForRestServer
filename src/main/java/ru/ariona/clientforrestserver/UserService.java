package ru.ariona.clientforrestserver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserManagementClient client;

    private Gson gson = new Gson();

    public List<User> getUsers() {
        List<User> users = null;
        try {
            users = gson.fromJson(client.getUsers(),new TypeToken<List<User>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(Long id) {
        User user = null;
        try {
            user = gson.fromJson(client.getUserById(id), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String addUser(User user) {
        return client.addUser(user);
    }



    public String editUser(User user) {
        System.out.println(user);
        return client.editUser(user);
    }

    public String deleteUser(Long id) {
        return client.deleteUser(id);
    }
}
