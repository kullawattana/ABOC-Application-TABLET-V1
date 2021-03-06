package com.example.suttipongk.testaboc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ViewHolder> {

    private List<ChatMessage> mChatMessages;
    private int[] mUsernameColors;

    public ChatMessageAdapter(Context context, List<ChatMessage> chatMessages) {
        mChatMessages = chatMessages;
        mUsernameColors = context.getResources().getIntArray(R.array.username_colors);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = -1;
        switch (viewType) {
        case ChatMessage.TYPE_MESSAGE:
            layout = R.layout.chat_item_message;
            break;
        case ChatMessage.TYPE_LOG:
            layout = R.layout.chat_item_log;
            break;
        case ChatMessage.TYPE_ACTION:
            layout = R.layout.chat_item_action;
            break;
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ChatMessage chatMessage = mChatMessages.get(position);
        viewHolder.setMessage(chatMessage.getMessage());
        viewHolder.setUsername(chatMessage.getUsername());
    }

    @Override
    public int getItemCount() {
        return mChatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mChatMessages.get(position).getType();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mUsernameView;
        private TextView mMessageView;

        public ViewHolder(View itemView) {
            super(itemView);

            mUsernameView = (TextView) itemView.findViewById(R.id.username);
            mMessageView = (TextView) itemView.findViewById(R.id.chatMessages);
        }

        public void setUsername(String username) {
            if (null == mUsernameView) return;
            mUsernameView.setText(username);
            mUsernameView.setTextColor(getUsernameColor(username));
        }

        public void setMessage(String message) {
            if (null == mMessageView) return;
            mMessageView.setText(message);
        }

        private int getUsernameColor(String username) {
            int hash = 7;
            for (int i = 0, len = username.length(); i < len; i++) {
                hash = username.codePointAt(i) + (hash << 5) - hash;
            }
            int index = Math.abs(hash % mUsernameColors.length);
            return mUsernameColors[index];
        }
    }
}
