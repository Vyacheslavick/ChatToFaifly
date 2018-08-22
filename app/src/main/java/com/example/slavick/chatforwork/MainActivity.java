package com.example.slavick.chatforwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    public String user_name = "FileName";
    TextView reg;
    Button login;
    TextView enterName;
    EditText userLogin;
    EditText userPass;

    public static final int REQUEST = 15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg = findViewById(R.id.reg);
        enterName = findViewById(R.id.enterName);
        userLogin = findViewById(R.id.userLogin);
        userPass = findViewById(R.id.userPass);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivityForResult(intent, REQUEST);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream name = openFileInput(user_name.concat(userLogin.getText().toString()));
                    FileInputStream pass = openFileInput(userPass.getText().toString().concat(userLogin.getText().toString()));
                    BufferedReader nameReader = new BufferedReader(new InputStreamReader(name));
                    BufferedReader passReader = new BufferedReader(new InputStreamReader(pass));
                    StringBuilder nameBuilder = new StringBuilder();
                    StringBuilder passBuilder = new StringBuilder();

                    String login;

                    String password;

                    while ((login = nameReader.readLine()) != null) {
                        nameBuilder.append(login);
                    }
                    while ((password = passReader.readLine()) != null) {
                        passBuilder.append(password);
                    }

                    Intent intent = new Intent(MainActivity.this, ChatActivity.class);

                } catch (FileNotFoundException e) {
                    login.setText(R.string.user_not_found);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
