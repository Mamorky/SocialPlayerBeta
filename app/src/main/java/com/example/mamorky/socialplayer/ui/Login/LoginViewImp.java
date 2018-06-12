package com.example.mamorky.socialplayer.ui.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.PrincipalActivity;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;
import com.example.mamorky.socialplayer.util.GoogleSignInUtilities;
import com.example.mamorky.socialplayer.util.StyleUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginViewImp extends BaseActivity implements LoginView, GoogleApiClient.OnConnectionFailedListener {

    SignInButton btnSignGoogle;
    private GoogleApiClient googleApiClient;
    private static final int SIGNINCODE = 777;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient
                .Builder(LoginViewImp.this)
                .enableAutoManage(LoginViewImp.this,LoginViewImp.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();


        //Bloquear giro de pantalla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_login_app);

        btnSignGoogle = (SignInButton) findViewById(R.id.btnGoogle);

        View.OnClickListener clickSignIn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == btnSignGoogle){
                    Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(intent,SIGNINCODE);
                }
            }
        };

        btnSignGoogle.setOnClickListener(clickSignIn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    navigateToHome();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGNINCODE){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(googleSignInResult);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    private void handleSignInResult(GoogleSignInResult googleSignInResult) {
        if(googleSignInResult.isSuccess()){
            firebaseAuthWithGoogle(googleSignInResult.getSignInAccount());
        }
        else
            Toast.makeText(this,"Error al iniciar sesion",Toast.LENGTH_LONG).show();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) {
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(),null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful())
                    Toast.makeText(getApplicationContext(),"No se pudo autentificar con firebase",Toast.LENGTH_SHORT).show();
                else
                    navigateToHome();
            }
        });
    }

    @Override
    public void navigateToHome() {
        Intent i = new Intent(LoginViewImp.this,PrincipalActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(firebaseAuthListener != null)
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
