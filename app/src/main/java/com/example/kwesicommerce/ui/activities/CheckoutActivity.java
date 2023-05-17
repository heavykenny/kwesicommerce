package com.example.kwesicommerce.ui.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.UserModel;
import com.example.kwesicommerce.data.repository.CartRepository;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.utils.NavigationUtil;

import java.util.Calendar;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    private TextView edtTxtExpireDate;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        CartRepository cartRepository = new CartRepository(getBaseContext());

        UserRepository userRepository = new UserRepository(getBaseContext());

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this);
        navigationUtil.setTopNavigationItemClick();
        navigationUtil.backNavigation("Your Details");

        edtTxtExpireDate = findViewById(R.id.edtTxtExpireDate);
        edtTxtExpireDate.setOnClickListener(view -> new DatePickerDialog(
                this,
                (view1, year, month, dayOfMonth) -> edtTxtExpireDate.setText(String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, month + 1, year)),
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).show());

        TextView edtTxtName = findViewById(R.id.edtTxtName);
        TextView edtTxtEmail = findViewById(R.id.edtTxtEmail);

        EditText edtTxtAddress = findViewById(R.id.edtTxtAddress);
        EditText edtTxtPostCode = findViewById(R.id.edtTxtPostCode);

        edtTxtName.setText(userRepository.getUserFullName());
        edtTxtEmail.setText(userRepository.getUserEmail());
        edtTxtAddress.setText(userRepository.getUser().getAddress());
        edtTxtPostCode.setText(userRepository.getUser().getPostcode());

        Button btnViewOrderSummary = findViewById(R.id.btnViewOrderSummary);

        btnViewOrderSummary.setOnClickListener(view -> {
            if (edtTxtAddress.getText().toString().isEmpty()) {
                edtTxtAddress.setError("Address is required");
                edtTxtAddress.requestFocus();
                return;
            }

            if (edtTxtPostCode.getText().toString().isEmpty()) {
                edtTxtPostCode.setError("Post code is required");
                edtTxtPostCode.requestFocus();
                return;
            }

            UserModel userModel = userRepository.getUser();
            userModel.setAddress(edtTxtAddress.getText().toString());
            userModel.setPostcode(edtTxtPostCode.getText().toString());
            userRepository.updateUser(userModel);

            navigationUtil.goToActivity(OrderSummaryActivity.class);
        });

        TextView txtViewTotalPrice = findViewById(R.id.txtViewTotalPrice);
        txtViewTotalPrice.setText(String.format("TOTAL £%,.2f", cartRepository.getCartTotalPrice(userRepository.getUserId())));
    }
}