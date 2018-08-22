package com.example.slavick.chatforwork;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ChatActivity extends AppCompatActivity {
    public static final String SP_NAME = "spname";
    public static final String SP_SAVE = "spsave";



    EditText anotherUser;
    Button goToChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        anotherUser = findViewById(R.id.anotherUser);
        goToChat = findViewById(R.id.go_to_chat);
        goToChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anotherUser.getText().toString().length() > 2){
                    Intent intent = new Intent();
                    SharedPreferences sharedPreferences = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString(SP_SAVE, anotherUser.getText().toString());
                    SharedPreferences sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
                    sp.edit().putString("user", intent.getStringExtra("USER"));
                    Intent intent1 = new Intent(ChatActivity.this, DialogActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }
}
