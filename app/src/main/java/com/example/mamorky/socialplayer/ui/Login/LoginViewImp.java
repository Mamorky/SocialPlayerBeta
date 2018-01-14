package com.example.mamorky.socialplayer.ui.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.PrincipalActivity;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;
import com.example.mamorky.socialplayer.util.MyPreferenceOption;
import com.example.mamorky.socialplayer.util.StyleUtils;

public class LoginViewImp extends BaseActivity implements LoginView {

    Button btnSignIn,btnSignGoogle;
    EditText edtUser;
    EditText edtPassword;
    LoginPresenter loginPresenter;
    CheckBox chkRememberMe;

    SharedPreferences preferences;

    boolean rememberme = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        rememberme = preferences.getBoolean(MyPreferenceOption.REMEMBERME,false);

        if(rememberme){
            navigateToHome();
        }

        //Bloquear giro de pantalla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignGoogle = (Button)findViewById(R.id.btnGoogle);
        edtUser = (EditText)findViewById(R.id.edtUser);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        chkRememberMe = (CheckBox)findViewById(R.id.chkRememberme);

        loginPresenter = new LoginPresenterImp(this);

        View.OnClickListener clickSignIn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == btnSignIn){
                    loginPresenter.validateCredentials(edtUser.getText().toString(),edtPassword.getText().toString());
                }
                if(v == btnSignGoogle)
                    navigateToHome();
            }
        };

        btnSignIn.setOnClickListener(clickSignIn);
        btnSignGoogle.setOnClickListener(clickSignIn);
    }

    @Override
    public void navigateToHome() {
        if(rememberme != true)
            preferences.edit().putBoolean(MyPreferenceOption.REMEMBERME,chkRememberMe.isChecked()).commit();
        Intent i = new Intent(LoginViewImp.this,PrincipalActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void setUserEmptyError() {
        onError(R.string.errorUsuarioEmpty);
    }

    @Override
    public void setPassordEmptyError() {
        onError(R.string.errorPasswordEmpty);
    }

    @Override
    public void setPasswordError() {
        onError(R.string.errorIncorrectUserPass);
    }

    @Override
    public void setUserIncorrect() {
        onError(R.string.errorIncorrectUserPass);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }
}
