package com.example.kwesicommerce.ui.activities;

import static com.example.kwesicommerce.utils.FileUtil.saveImageToFile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.UserModel;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.utils.NavigationUtil;

public class EditProfileActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 1;
    private String profileImageUri = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        LinearLayout active = findViewById(R.id.linLayoutProfile);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        navigationUtil.backNavigation("Edit Profile");

        UserRepository userRepository = new UserRepository(getBaseContext());

        EditText edtTxtFullName = findViewById(R.id.edtTxtFullName);
        EditText edtTxtAddress = findViewById(R.id.edtTxtAddress);
        EditText edtTxtPostCode = findViewById(R.id.edtTxtPostCode);
        EditText edtTxtHobbiesAndInterests = findViewById(R.id.edtTxtHobbiesAndInterests);

        ImageView imgViewProductImage = findViewById(R.id.imgViewProductImage);

        if (!userRepository.getUser().getProfileImage().isEmpty()) {
            Glide.with(this).load(userRepository.getUser().getProfileImage())
                    .apply(new RequestOptions()
                            .override(300, 300)
                            .transform(new CenterCrop(), new RoundedCorners(1000)))
                    .into(imgViewProductImage);
        }

        edtTxtFullName.setText(userRepository.getUserFullName());
        if (!userRepository.getUserAddress().isEmpty())
            edtTxtAddress.setText(userRepository.getUserAddress());
        if (!userRepository.getUserPostcode().isEmpty())
            edtTxtPostCode.setText(userRepository.getUserPostcode());
        if (!userRepository.getUserHobbies().isEmpty())
            edtTxtHobbiesAndInterests.setText(userRepository.getUserHobbies());


        imgViewProductImage.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
        });


        Button btnUpdateProfile = findViewById(R.id.btnUpdateProfile);

        btnUpdateProfile.setOnClickListener(v -> {
            String fullName = edtTxtFullName.getText().toString();
            String address = edtTxtAddress.getText().toString();
            String postCode = edtTxtPostCode.getText().toString();
            String hobbies = edtTxtHobbiesAndInterests.getText().toString();

            if (fullName.isEmpty()) {
                edtTxtFullName.setError("Please enter your full name");
                edtTxtFullName.requestFocus();
            }

            // if address is filled, postcode must be filled and vice versa
            else if (!address.isEmpty() && postCode.isEmpty()) {
                edtTxtPostCode.setError("Please enter your postcode");
                edtTxtPostCode.requestFocus();
            } else if (address.isEmpty() && !postCode.isEmpty()) {
                edtTxtAddress.setError("Please enter your address");
                edtTxtAddress.requestFocus();
            } else {
                UserModel userModel = userRepository.getUser();
                userModel.setFullName(fullName);
                userModel.setAddress(address);
                userModel.setPostcode(postCode);
                userModel.setHobbies(hobbies);
                userModel.setProfileImage(profileImageUri);
                userRepository.updateUser(userModel);

//                Toast.makeText(this, userRepository.getUserProfileImage(), Toast.LENGTH_SHORT).show();

                Toast.makeText(this, userModel.getProfileImage(), Toast.LENGTH_SHORT).show();
                navigationUtil.goToActivity(ProfileActivity.class);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            profileImageUri = saveImageToFile(this, imageUri);
        }
    }
}