package com.mishraaditya.mycomm.ServerClients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mishraaditya.mycomm.ModelResponse.User;
import com.mishraaditya.mycomm.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.username.setText(userList.get(position).getUsername());
        holder.useremail.setText(userList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,useremail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            useremail=itemView.findViewById(R.id.txtUserEmail);
            username=itemView.findViewById(R.id.txtUserName);
        }
    }
}
