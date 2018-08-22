package com.example.slavick.chatforwork;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.slavick.chatforwork.ChatActivity.SP_NAME;
import static com.example.slavick.chatforwork.ChatActivity.SP_SAVE;

public class DialogActivity extends AppCompatActivity implements MessageRecyclerAdapter.OnRecyclerItemClick{
    EditText createMessage;
    RecyclerView messageList;
    MessageRecyclerAdapter adapter;
    List<Message> dialogMessages;
    Button send;
    String username;
    Thread thread;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        createMessage = findViewById(R.id.createMessage);
        messageList = findViewById(R.id.message_list);
        send = findViewById(R.id.send);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        messageList.setLayoutManager(layoutManager);
//        dialogMessages.add(new Message("admin", "We are glad to see you in our messenger)"));
        SharedPreferences sharedPreferences = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        final String dialog = sharedPreferences.getString(SP_SAVE, "kek");
        SharedPreferences sp = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        final String username = sp.getString("user", "user");
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (0 == 0) {
//                    Retrofit.getMessage(dialog, new Callback<List<Message>>() {
//                        @Override
//                        public void success(List<Message> messages, Response response) {
//                            while (messages.size() - dialogMessages.size() > 0) {
//                                int addon = messages.size() - (messages.size() - dialogMessages.size();
//                                dialogMessages.add(addon, messages.get(addon));
//                                adapter = new MessageRecyclerAdapter(DialogActivity.this, dialogMessages, DialogActivity.this);
//                                messageList.setAdapter(adapter);
//                                adapter.notifyDataSetChanged();
//                            }
//
//                            }
//
//                            @Override
//                            public void failure (RetrofitError error){
//                                dialogMessages.add(new Message("trouble", "Check out your internet, please"));
//                            }
//                        });
//
//                }
//            }
//        });
//        thread.start();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(new Message(username, createMessage.getText().toString()));
                Retrofit.sendMessage(new Message(username, createMessage.getText().toString()), new Callback<Message>() {
                    @Override
                    public void success(Message message, Response response) {
                        Log.d("Yeah", "message done");
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });
    }

    @Override
    public void onClick(int position) {

    }
}
