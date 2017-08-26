package com.example.suttipongk.testaboc;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;

/**
 * Created by TOPPEE on 8/19/2017.
 */

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved = null;
    ArrayList<String> firebaseQueryProfile = new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE
    public Boolean save(FirebaseQueryProfile firebaseQueryProfile) {
        if(firebaseQueryProfile == null) {
            saved=false;
        } else {
            try {
                db.child("FirebaseQueryProfile").push().setValue(firebaseQueryProfile);
                saved=true;
            } catch (DatabaseException e) {
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }

    //READ
    public ArrayList<String> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return firebaseQueryProfile;
    }

    //Feed Data to List
    private void fetchData(DataSnapshot dataSnapshot) {
        firebaseQueryProfile.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String name = ds.getValue(FirebaseQueryProfile.class).getName();
            String surname = ds.getValue(FirebaseQueryProfile.class).getSurname();
            String address = ds.getValue(FirebaseQueryProfile.class).getAddress();
            String tel = ds.getValue(FirebaseQueryProfile.class).getTel();
            String email = ds.getValue(FirebaseQueryProfile.class).getEmailAddress();
            String takeCareType = ds.getValue(FirebaseQueryProfile.class).getTakeCareType();
            String timeFallDetection = ds.getValue(FirebaseQueryProfile.class).getTimeFallDetection();
            firebaseQueryProfile.add(name);
        }
    }

}
