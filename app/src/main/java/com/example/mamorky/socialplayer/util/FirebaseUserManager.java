package com.example.mamorky.socialplayer.util;

import android.widget.Toast;

import com.example.mamorky.socialplayer.ui.base.BaseContext;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mamorky on 13/01/18.
 */

public class FirebaseUserManager {

    private static FirebaseUserManager firebaseManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference fb_SocialPlayer;
    private DatabaseReference fb_userPreferences;

    private String idUser;

    private FirebaseUserManager() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        fb_SocialPlayer = firebaseDatabase.getReference("DbSocialPlayer");
        idUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        fb_userPreferences = fb_SocialPlayer.child(idUser).child("preferences");
    };

    public static FirebaseUserManager getInstance(){
        if(firebaseManager == null)
            firebaseManager = new FirebaseUserManager();

        return firebaseManager;
    }

    public static void destroyInstance(){
        firebaseManager = null;
    }

    public DatabaseReference getUserPrefences(){
        return fb_userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        fb_userPreferences.setValue(userPreferences, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError != null){
                    Toast.makeText(BaseContext.getContext(),"No se pudieron subir las preferencias",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
