package com.example.mamorky.socialplayer.util;

import android.text.format.Time;

import com.example.mamorky.socialplayer.data.db.pojo.User;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mamorky on 13/11/17.
 */

public class CommonUtils {

    /**
     * Método que prueba que la contraseña tenga los siguientes requisitos
     * Debe contener al menos un digito del 0-9
     * Debe contener al menos un carácter en minuscula
     * Debe contener al menos un carácter en mayúscula
     * Y debe de tener una longitud de almenos 6 carácteres
     * */
    public static boolean isPasswordValid(String password){
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*[0-9]).{6,}";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isUserExists(String name, String password, ArrayList<User> usersExists){
        for (int i = 0; i < usersExists.size(); i++) {
            if(usersExists.get(i).getUser().equals(name))
                if(usersExists.get(i).getPassword().equals(password))
                    return true;
        }
        return false;
    }
}
