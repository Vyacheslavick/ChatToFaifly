package com.example.slavick.chatforwork;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.ViewHolder> {
    public Context context;
    public List<Message> messages;
    public OnRecyclerItemClick onRecyclerItemClick;

    public MessageRecyclerAdapter(Context context, List<Message> messages, OnRecyclerItemClick onRecyclerItemClick) {
        this.context = context;
        this.messages = messages;
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    public interface OnRecyclerItemClick{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.messageText.setText(message.messageText);
        holder.username.setText(message.username);
    }
    public void addItem(Message message) {
        messages.add(0, message);
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView messageText;
        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            messageText = itemView.findViewById(R.id.messageText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerItemClick.onClick(getAdapterPosition());
                }
            });
        }
    }
}
