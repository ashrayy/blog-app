package com.example.blogapp.util;

import com.example.blogapp.model.Role;
import com.example.blogapp.payload.UserDTO;
import com.example.blogapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public static Role getDummyRole(){
        Role role = new Role();
        role.setName("ADMIN");
        role.setId(1);
        return role;
    }

    public static User getDummyUserData(){
        User usr = new User();
        usr.setId(2);
        usr.setPassword("12344");
        usr.setEmail("fsdsdf.sffsd@g.com");
        usr.setAbout("sfsfd");
        usr.setName("fdsfds");
        return usr;
    }

    public static List<User> getDummyUserListData(){
        List<User> usrList = new ArrayList<>();
        User usr = new User();
        usr.setId(2);
        usr.setPassword("12344");
        usr.setEmail("fsdsdf.sffsd@g.com");
        usr.setAbout("sfsfd");
        usr.setName("fdsfds");
        usrList.add(usr);
        return usrList;
    }

    public static UserDTO getDummyUserDTOData(){
        UserDTO usr = new UserDTO();
        usr.setId(2);
        usr.setPassword("12344");
        usr.setEmail("fsdsdf.sffsd@g.com");
        usr.setAbout("sfsfd");
        usr.setName("fdsfds");
        return usr;
    }
}
