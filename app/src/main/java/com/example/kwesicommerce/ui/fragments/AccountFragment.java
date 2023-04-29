package com.example.kwesicommerce.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.kwesicommerce.R;

public class AccountFragment extends Fragment {
    private boolean isLogin = true;

    // Inflate the layout for the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth_form, container, false);

        Button loginButton = view.findViewById(R.id.login_button);
        Button registerButton = view.findViewById(R.id.register_button);
        View loginForm = view.findViewById(R.id.login_form);
        View registerForm = view.findViewById(R.id.register_form);

        loginButton.setOnClickListener(v -> {
            isLogin = true;
            loginForm.setVisibility(View.VISIBLE);
            registerForm.setVisibility(View.GONE);
        });

        registerButton.setOnClickListener(v -> {
            isLogin = false;
            loginForm.setVisibility(View.GONE);
            registerForm.setVisibility(View.VISIBLE);
        });

        // Show the initial form (login by default)
        loginForm.setVisibility(View.VISIBLE);
        registerForm.setVisibility(View.GONE);

        return view;
    }
}


