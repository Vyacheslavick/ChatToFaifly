package com.example.slavick.chatforwork;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegistrationActivity extends AppCompatActivity {

    public String user_name = "FIleName";
    TextView nameCheck;
    TextView passCheck;
    EditText newName;
    EditText newPass;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        nameCheck = findViewById(R.id.nameCheck);
        passCheck = findViewById(R.id.passCheck);
        newName = findViewById(R.id.newName);
        newPass = findViewById(R.id.newPass);
        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoginValid(newName.getText().toString())){
                    if (isPasswordValid(newPass.getText().toString())){
                        try {
                            FileOutputStream name = openFileOutput(user_name.concat(newName.getText().toString()), Context.MODE_PRIVATE);
                            FileOutputStream password = openFileOutput(newPass.getText().toString().concat(newName.getText().toString()), Context.MODE_PRIVATE);
                            name.write(newName.getText().toString().getBytes());
                            password.write(newPass.getText().toString().getBytes());
                            name.close();
                            password.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
    public boolean isLoginValid(String login){
        boolean log = false;
        if (login.replace(" "  , "").replace("."  , "").replace("!"  , "").replace(","  , "").length() > 2){
            log = true;
        }
        return log;
    }
    public boolean isPasswordValid(String password){
        boolean pass = false;
        if (password.length() > 4){
            pass = true;
        }
        return pass;
    }
}
