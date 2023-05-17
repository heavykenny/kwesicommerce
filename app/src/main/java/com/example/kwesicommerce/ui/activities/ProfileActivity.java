package com.example.kwesicommerce.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.utils.NavigationUtil;

public class ProfileActivity extends AppCompatActivity {

    UserRepository userRepository;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        LinearLayout active = findViewById(R.id.linLayoutProfile);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        navigationUtil.backNavigation("User Profile");
        userRepository = new UserRepository(getBaseContext());

        TextView txtViewFirstName = findViewById(R.id.txtProfileName);
        TextView txtViewEmail = findViewById(R.id.txtProfileEmail);
        TextView txtProfileAddress = findViewById(R.id.txtProfileAddress);
        TextView txtProfilePostCode = findViewById(R.id.txtProfilePostCode);
        TextView txtViewHobbies = findViewById(R.id.txtViewHobbies);
        ImageView imgViewProfileImage = findViewById(R.id.imgViewProfileImage);

        if (!userRepository.getUser().getProfileImage().isEmpty()) {
            Glide.with(this).load(userRepository.getUser().getProfileImage())
                    .apply(new RequestOptions()
                            .override(300, 300)
                            .transform(new CenterCrop(), new RoundedCorners(1000)))
                    .into(imgViewProfileImage);
        }

        txtViewFirstName.setText("Name: "+ userRepository.getUserFullName());
        txtViewEmail.setText("Email: "+ userRepository.getUserEmail());

        if (!userRepository.getUserAddress().isEmpty()){
            txtProfileAddress.setText("Address: "+ userRepository.getUserAddress());
        }

        if (!userRepository.getUserHobbies().isEmpty()){
            txtViewHobbies.setText("Hobbies: "+ userRepository.getUserHobbies());
        }

        if (!userRepository.getUserPostcode().isEmpty()){
            txtProfilePostCode.setText("Postcode: "+ userRepository.getUserPostcode());
        }

        Button btnProfileEdit = findViewById(R.id.btnProfileEdit);
        btnProfileEdit.setOnClickListener(v -> navigationUtil.goToActivity(EditProfileActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this);
        navigationUtil.goToActivity(CategoryActivity.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}