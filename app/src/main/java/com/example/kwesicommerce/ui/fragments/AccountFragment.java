package com.example.kwesicommerce.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.UserModel;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.ui.activities.HomeActivity;
import com.example.kwesicommerce.ui.activities.ProfileActivity;
import com.example.kwesicommerce.utils.NotificationUtil;

import org.mindrot.jbcrypt.BCrypt;

public class AccountFragment extends Fragment {

    UserRepository userRepository;

    // Inflate the layout for the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth_form, container, false);

        NotificationUtil notificationUtil = new NotificationUtil(getContext());
        userRepository = new UserRepository(getContext());

        if (userRepository.isUserLoggedIn()) {
            Intent intent = new Intent(getContext(), ProfileActivity.class);
            startActivity(intent);
        }

        Button loginButton = view.findViewById(R.id.btnLogin);
        Button registerButton = view.findViewById(R.id.btnRegister);
        View loginForm = view.findViewById(R.id.linLayLoginForm);
        View registerForm = view.findViewById(R.id.linLayRegisterForm);

        // login form
        EditText edtTxtEmailLogin = view.findViewById(R.id.edtTxtEmailLogin);
        EditText edtTxtPasswordLogin = view.findViewById(R.id.edtTxtPasswordLogin);

        // register form
        EditText edtTxtFirstName = view.findViewById(R.id.edtTxtFirstName);
        EditText edtTxtLastName = view.findViewById(R.id.edtTxtLastName);
        EditText edtTxtEmail = view.findViewById(R.id.edtTxtEmail);
        EditText edtTxtPassword = view.findViewById(R.id.edtTxtPassword);
        EditText edtTxtConfirmPassword = view.findViewById(R.id.edtTxtConfirmPassword);

        Button btnAccountRegister = view.findViewById(R.id.btnAccountRegister);
        Button btnAccountLogin = view.findViewById(R.id.btnAccountLogin);

        loginButton.setOnClickListener(v -> {
            loginForm.setVisibility(View.VISIBLE);
            registerForm.setVisibility(View.GONE);
        });

        registerButton.setOnClickListener(v -> {
            loginForm.setVisibility(View.GONE);
            registerForm.setVisibility(View.VISIBLE);
        });

        loginForm.setVisibility(View.VISIBLE);
        registerForm.setVisibility(View.GONE);

        btnAccountRegister.setOnClickListener(v -> {
            String firstName = edtTxtFirstName.getText().toString().trim();
            String lastName = edtTxtLastName.getText().toString().trim();
            String email = edtTxtEmail.getText().toString().trim();
            String password = edtTxtPassword.getText().toString().trim();
            String confirmPassword = edtTxtConfirmPassword.getText().toString().trim();

            // validate user input
            if (firstName.isEmpty()) {
                edtTxtFirstName.setError("First name is required");
                edtTxtFirstName.requestFocus();
                return;
            }

            if (lastName.isEmpty()) {
                edtTxtLastName.setError("Last name is required");
                edtTxtLastName.requestFocus();
                return;
            }

            if (email.isEmpty()) {
                edtTxtEmail.setError("Email is required");
                edtTxtEmail.requestFocus();
                return;
            }

            if (!userRepository.isEmailUnique(email)) {
                edtTxtEmail.setError("Email already exists");
                edtTxtEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                edtTxtPassword.setError("Password is required");
                edtTxtPassword.requestFocus();
                return;
            }

            if (confirmPassword.isEmpty()) {
                edtTxtConfirmPassword.setError("Confirm password is required");
                edtTxtConfirmPassword.requestFocus();
                return;
            }

            if (!password.equals(confirmPassword)) {
                edtTxtConfirmPassword.setError("Passwords do not match");
                edtTxtConfirmPassword.requestFocus();
                return;
            }

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            UserModel newUserModel = new UserModel(firstName, lastName, email, hashedPassword);

            if (userRepository.createUser(newUserModel) > 0) {
                notificationUtil.showToast("Account created successfully", true);
            } else {
                notificationUtil.showToast("Account creation failed", false);
            }
        });

        btnAccountLogin.setOnClickListener(v -> {
            String email = edtTxtEmailLogin.getText().toString().trim();
            String password = edtTxtPasswordLogin.getText().toString().trim();

            if (email.isEmpty()) {
                edtTxtEmail.setError("Email is required");
                edtTxtEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                edtTxtPassword.setError("Password is required");
                edtTxtPassword.requestFocus();
                return;
            }

            boolean isAuthenticated = userRepository.isUserCredentialsValid(email, password);

            if (isAuthenticated) {
                userRepository.setUserDetails(userRepository.getUserByEmail(email));
                notificationUtil.showToast("Login successful", true);
                // User authentication successful
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
            } else {
                notificationUtil.showToast("Invalid credentials", false);
            }
        });

        return view;
    }
}


