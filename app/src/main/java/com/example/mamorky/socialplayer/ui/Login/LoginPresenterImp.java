package com.example.mamorky.socialplayer.ui.Login;

/**
 * Created by mamorky on 10/11/17.
 */

public class LoginPresenterImp implements LoginPresenter,LoginInteractor.OnLoginFinishedListener{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImp(LoginView loginView) {
        this.loginView = loginView;
        loginInteractor = new LoginInteractorImp();
    }
    
    @Override
    public void validateCredentials(String user, String password) {
        loginInteractor.validateCredentials(user,password,this);
    }

    @Override
    public void onUserEmptyError() {
        loginView.setUserEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        loginView.setPassordEmptyError();
    }

    @Override
    public void onPasswordError() {
        loginView.setPasswordError();
    }

    @Override
    public void onUserIncorrect() {
        loginView.setUserIncorrect();
    }

    @Override
    public void onSucess() {
        loginView.navigateToHome();
    }

    @Override
    public void onDestroy() {
        loginView = null;
        loginInteractor = null;
    }
}
