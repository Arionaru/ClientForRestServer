package ru.ariona.clientforrestserver;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class ClientController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUserList(Model model) {
        List<User> users;
        try {
            users = userService.getUsers();
        } catch (ClientProtocolException e) {
            users = new ArrayList<>();
        }
        model.addAttribute("users",users);
        return "users";
    }

    @GetMapping("/add")
    public String addUserPage() {
        return "adduser";
    }

    @PostMapping("/add")
    public String addUserToDB(User user) {
        userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping("{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        User user;
        try {
            user = userService.getUserById(id);
        } catch (ClientProtocolException e) {
            user = new User();
        }
        model.addAttribute("user", user);


        return "userbyid";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam Map<String,String> form,
                           @RequestParam Long userId) {
        User user;
        try {
            user = userService.getUserById(userId);
        } catch (ClientProtocolException e) {
            user = new User();
        }
        user.setFirstName(form.get("firstName"));
        user.setLastName(form.get("lastName"));
        user.setAddress(form.get("address"));
        user.setAboutMe(form.get("aboutMe"));
        userService.addUser(user);
        return "redirect:/user";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return "redirect:/user";
    }
}
