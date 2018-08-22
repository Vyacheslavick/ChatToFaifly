package com.example.slavick.chatforwork;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.slavick.chatforwork.ChatActivity.SP_NAME;
import static com.example.slavick.chatforwork.ChatActivity.SP_SAVE;

public class ChattingFragment extends Fragment implements MessageRecyclerAdapter.OnRecyclerItemClick{

    EditText createMessage;
    RecyclerView messageList;
    MessageRecyclerAdapter adapter;
    Context context;
    List<Message> dialogMessages;
    Button send;
    String username;

    public static ChattingFragment instance;
    public static ChattingFragment getInstance(){
        if (instance == null){
            instance = new ChattingFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState ) {
        View root = inflater.inflate(R.layout.chatting_fragment, container, false);
        createMessage = root.findViewById(R.id.createMessage);
        messageList = root.findViewById(R.id.message_list);
        send = root.findViewById(R.id.send);

        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        messageList.setLayoutManager(layoutManager);
        dialogMessages.add(new Message("admin", "We are glad to see you in our messenger)"));
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        final String dialog = sharedPreferences.getString(SP_SAVE, "kek");
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        final String username = sp.getString("user", "user");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit.getMessage(dialog, new Callback<List<Message>>() {
                    @Override
                    public void success(List<Message> messages, Response response) {
                        dialogMessages.addAll(messages);
                        adapter = new MessageRecyclerAdapter(context,  dialogMessages , ChattingFragment.this );
                        messageList.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        dialogMessages.add(new Message("trouble", "Check out your internet, please"));
                    }
                });

            }
        });
        thread.start();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        return root;
    }

    @Override
    public void onClick(int position) {

    }
}
