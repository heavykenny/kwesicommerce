package com.example.kwesicommerce.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.UserModel;
import com.example.kwesicommerce.utils.NotificationUtil;

import java.util.List;

// REFERENCE: https://developer.android.com/develop/ui/views/layout/recyclerview
public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {
    @SuppressLint("StaticFieldLeak")
    private static NotificationUtil notificationUtil = null;
    private static List<UserModel> userModelList = null;

    public UserRecyclerViewAdapter(List<UserModel> userModelList, Context context) {
        UserRecyclerViewAdapter.userModelList = userModelList;
        notificationUtil = new NotificationUtil(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel userModel = userModelList.get(position);
        holder.bind(userModel);
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtViewUserName;
        private final TextView txtViewEmail;
        private final TextView txtViewDateRegistered;
        private final TextView txtViewUserType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewUserName = itemView.findViewById(R.id.txtViewUserName);
            txtViewEmail = itemView.findViewById(R.id.txtViewEmail);
            txtViewDateRegistered = itemView.findViewById(R.id.txtViewDateRegistered);
            txtViewUserType = itemView.findViewById(R.id.txtViewUserType);
        }

        @SuppressLint("DefaultLocale")
        public void bind(UserModel userModel) {
            txtViewUserName.setText(userModel.getFullName());
            txtViewEmail.setText(userModel.getEmail());
            txtViewDateRegistered.setText(userModel.getDateRegistered());
            txtViewUserType.setText(userModel.isAdmin() ? "Admin" : "UserModel");
        }
    }
}