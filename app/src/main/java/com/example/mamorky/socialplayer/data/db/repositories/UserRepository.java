package com.example.mamorky.socialplayer.data.db.repositories;

import com.example.mamorky.socialplayer.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by mamorky on 8/11/17.
 */

public class UserRepository {
    private ArrayList<User> users;
    private static UserRepository userRepository;

    static {
        userRepository = new UserRepository();
    }

    private UserRepository(){
        users = new ArrayList<>();
        inicialize();
    }

    private void inicialize(){
        addUser(new User(1,"mamorky","123456Aa","Andrés","andres_ball@hotmail.com",true,true));
        addUser(new User(2,"jose","123","Jose Antonio","pepe@hotmail.com",false,true));
        addUser(new User(3,"ismael","123","Ismael","ismael@hotmail.com",true,false));
    }

    public static UserRepository getInstance(){
        if(userRepository == null)
            userRepository = new UserRepository();
        return userRepository;
    }

    public void addUser(User user){
        users.add(user);
    }

    public ArrayList<User> getUser(){return users;}

    /**
     * Método que comprueba si el usuario existe en la base de datos
     @return boolean*/
    public boolean isUserExists(User user){
        return true;
    }
}
