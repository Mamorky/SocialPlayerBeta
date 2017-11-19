package com.example.mamorky.socialplayer.ui.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.MenuPrincipal;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;

public class LoginViewImp extends BaseActivity implements LoginView {

    Button btnSignIn,btnSignGoogle;
    EditText edtUser;
    EditText edtPassword;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignGoogle = (Button)findViewById(R.id.btnGoogle);
        edtUser = (EditText)findViewById(R.id.edtUser);
        edtPassword = (EditText)findViewById(R.id.edtPassword);

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
        Intent i = new Intent(LoginViewImp.this,MenuPrincipal.class);
        startActivity(i);
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
