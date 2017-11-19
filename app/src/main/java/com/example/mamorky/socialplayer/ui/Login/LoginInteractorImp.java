package com.example.mamorky.socialplayer.ui.Login;

import android.text.TextUtils;

import com.example.mamorky.socialplayer.data.db.repositories.UserRepository;
import com.example.mamorky.socialplayer.util.CommonUtils;

/**
 * Created by mamorky on 10/11/17.
 */

public class LoginInteractorImp implements LoginInteractor{

    private UserRepository repository;

    public LoginInteractorImp(){
        repository = UserRepository.getInstance();
    }

    @Override
    public void validateCredentials(String user, String password, OnLoginFinishedListener listener) {
        if(TextUtils.isEmpty(password))
            listener.onPasswordEmptyError();
        else if (TextUtils.isEmpty(user))
            listener.onUserEmptyError();
        else if (!CommonUtils.isPasswordValid(password))
            listener.onPasswordError();
        else if(!CommonUtils.isUserExists(user,password,repository.getUser()))
            listener.onUserIncorrect();
        else
            listener.onSucess();
    }
}
