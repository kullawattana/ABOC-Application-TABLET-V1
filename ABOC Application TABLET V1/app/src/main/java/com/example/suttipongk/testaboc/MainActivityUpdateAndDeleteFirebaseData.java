package com.example.suttipongk.testaboc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.suttipongk.util.FirebaseNotificationUtil;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class MainActivityUpdateAndDeleteFirebaseData extends ActionBarActivity implements View.OnClickListener{

  Button save;
  static Firebase myFirebaseRef;
  EditText nameEditText;
  EditText messageEditText;
  ProgressBar progressBar;
  static final String TAG = "Main Acvity";
  ArrayAdapter<String> valuesAdapter;
  ArrayList<String> displayArray;
  ArrayList<String> keysArray;
  ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main_firebase_update_and_delete);

      save = (Button)findViewById(R.id.save);
      nameEditText = (EditText)findViewById(R.id.name);
      messageEditText= (EditText)findViewById(R.id.message);
      progressBar = (ProgressBar)findViewById(R.id.progressBar);
      listView = (ListView)findViewById(R.id.listView);

      displayArray  = new ArrayList<>();
      keysArray = new ArrayList<>();
      valuesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,displayArray);
      listView.setAdapter(valuesAdapter);
      listView.setOnItemClickListener(itemClickListener);

      Firebase.setAndroidContext(this);
      myFirebaseRef = new Firebase("https://aboc-afe9a.firebaseio.com");
      myFirebaseRef.addChildEventListener(childEventListener);

      save.setOnClickListener(this);
  }

  private void showProgressBar(){
      InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
      progressBar.setVisibility(View.VISIBLE);
  }

  private void hideProgressBar(){
    progressBar.setVisibility(View.INVISIBLE);
  }
  @Override
  public void onClick(View v) {
      showProgressBar();
      switch (v.getId()){
        case R.id.save:
          String nameString = nameEditText.getText().toString();
          String messageString = messageEditText.getText().toString();
          save(nameString,messageString);
          break;
      }
  }

  private void save(String name, String message){
      myFirebaseRef.child(name).setValue(message, new Firebase.CompletionListener() {
        @Override
        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
          nameEditText.setText("");
          messageEditText.setText("");
          hideProgressBar();
        }
      });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu_main, menu);
      return true;
  }

  ChildEventListener childEventListener = new ChildEventListener() {
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Log.d(TAG, dataSnapshot.getKey() + ":" + dataSnapshot.getValue().toString());

        String keyAndValue = null;
        String chkKey = dataSnapshot.getValue().toString().substring(1,5);
        Log.d(TAG, chkKey);
        if(chkKey.equals("Fall")){
          keyAndValue = dataSnapshot.getValue().toString().substring(6, dataSnapshot.getValue().toString().length() - 1);
          FirebaseNotificationUtil firebaseMessaging = new FirebaseNotificationUtil();
          firebaseMessaging.pushFCMNotificationFallDetection(keyAndValue);
        } else {
          for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
            FirebaseQueryProfile post = postSnapshot.getValue(FirebaseQueryProfile.class);
            keyAndValue = post.getName() + " " + post.getSurname() + " " + post.getAddress() + " " + post.getTel() + " " + post.getEmailAddress() + " " + post.getTakeCareType() + " " + post.getTimeFallDetection();
          }
        }

  //    String keyAndValue = "Key: " +dataSnapshot.getKey().toString() + "\t Value: " +  dataSnapshot.getValue().toString();
  //    String keyAndValue = dataSnapshot.getValue().toString();

        displayArray.add(keyAndValue);
        keysArray.add(dataSnapshot.getKey().toString());
        updateListView();
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        String changedKey = dataSnapshot.getKey();
        int changedIndex = keysArray.indexOf(changedKey);
        String keyAndValue = "Key: " +dataSnapshot.getKey().toString() + "\t Value: " +  dataSnapshot.getValue().toString();
        displayArray.set(changedIndex,keyAndValue);
        updateListView();
      }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {
        String deletedKey = dataSnapshot.getKey();
        int removedIndex = keysArray.indexOf(deletedKey);
        keysArray.remove(removedIndex);
        displayArray.remove(removedIndex);
        updateListView();
      }

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        Log.d(TAG, dataSnapshot.getKey() +":" + dataSnapshot.getValue().toString());
      }

      @Override
      public void onCancelled(FirebaseError firebaseError) {}
  };

  private void updateListView(){
      valuesAdapter.notifyDataSetChanged();
      listView.invalidate();
      Log.d(TAG, "Length: " + displayArray.size());
  }

  AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      String clickedKey = keysArray.get(position);
      myFirebaseRef.child(clickedKey).removeValue();
      Log.d(TAG,clickedKey);
    }
  };

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
      int id = item.getItemId();
      if (id == R.id.action_settings) {
        return true;
      }
      return super.onOptionsItemSelected(item);
    }
}
