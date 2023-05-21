package com.example.kwesicommerce.ui.activities;

import static com.example.kwesicommerce.utils.FileUtil.saveImageToFile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CategoryModel;
import com.example.kwesicommerce.data.model.ProductModel;
import com.example.kwesicommerce.data.repository.CategoryRepository;
import com.example.kwesicommerce.data.repository.ProductRepository;
import com.example.kwesicommerce.ui.adapters.CustomSpinnerAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;
import com.example.kwesicommerce.utils.NotificationUtil;

import java.util.List;

public class AdminCreateProductActivity extends AppCompatActivity {

    private Button btnUploadImage;

    private Button btnCreateCategory;
    private ImageView imageViewProductImage;

    private static final int GALLERY_REQUEST_CODE = 1;
    private String productImageUri = "";
    private ActivityResultLauncher<String> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_product);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, findViewById(R.id.linLayoutAdminProduct));
        navigationUtil.setAdminNavigationItemClick();

        navigationUtil.backNavigation("Create Product");

        NotificationUtil notificationUtil = new NotificationUtil(getBaseContext());

        CategoryRepository categoryRepository = new CategoryRepository(getApplicationContext());

        Spinner spinnerCategoryID = findViewById(R.id.spinnerCategoryID);
        List<CategoryModel> categories = categoryRepository.getAllCategories();

        // Create an ArrayAdapter using the string array and a default spinner layout
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, categories);
        spinnerCategoryID.setAdapter(adapter);


        // Get the product image
        imageViewProductImage = findViewById(R.id.imageViewProductImage);
        btnUploadImage = findViewById(R.id.btnUploadImage);

        btnUploadImage.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
        });

        btnCreateCategory = findViewById(R.id.btnCreateCategory);
        btnCreateCategory.setOnClickListener(v -> {
            // Get the selected category
            CategoryModel selectedCategoryModel = (CategoryModel) spinnerCategoryID.getSelectedItem();
            // Get the category ID
            int categoryID = selectedCategoryModel.getId();
            // Get the productModel image

            TextView edtTxtProductName = findViewById(R.id.edtTxtProductName);
            TextView edtTxtProductDescription = findViewById(R.id.edtTxtDescription);
            TextView edtTxtProductPrice = findViewById(R.id.edtTxtPrice);
            TextView edtTxtProductQuantity = findViewById(R.id.edtTxtQuantity);
            TextView edtTxtListPrice = findViewById(R.id.edtTxtListPrice);
            TextView edtTxtRetailPrice = findViewById(R.id.edtTxtRetailPrice);


            String productName = edtTxtProductName.getText().toString();
            String productDescription = edtTxtProductDescription.getText().toString();
            String productPrice = edtTxtProductPrice.getText().toString();
            String productQuantity = edtTxtProductQuantity.getText().toString();
            String listPrice = edtTxtListPrice.getText().toString();
            String retailPrice = edtTxtRetailPrice.getText().toString();

            if (productName.isEmpty()) {
                edtTxtProductName.setError("Product name is required");
                edtTxtProductName.requestFocus();
                return;
            }

            if (productDescription.isEmpty()) {
                edtTxtProductDescription.setError("Product description is required");
                edtTxtProductDescription.requestFocus();
                return;
            }

            if (productPrice.isEmpty()) {
                edtTxtProductPrice.setError("Product price is required");
                edtTxtProductPrice.requestFocus();
                return;
            }

            if (productQuantity.isEmpty()) {
                edtTxtProductQuantity.setError("Product quantity is required");
                edtTxtProductQuantity.requestFocus();
                return;
            }

            if (listPrice.isEmpty()) {
                edtTxtListPrice.setError("List price is required");
                edtTxtListPrice.requestFocus();
                return;
            }

            if (retailPrice.isEmpty()) {
                edtTxtRetailPrice.setError("Retail price is required");
                edtTxtRetailPrice.requestFocus();
            }

            ProductModel productModel = new ProductModel(
                    0, productName, productDescription,
                    productImageUri,
                    Integer.parseInt(productQuantity),
                    Double.parseDouble(productPrice), Double.parseDouble(listPrice),
                    Double.parseDouble(retailPrice), categoryID);

            ProductRepository productRepository = new ProductRepository(getBaseContext());

            if (productRepository.createProduct(productModel) > 0) {
                notificationUtil.showToast("Product has been created successfully", true);
                navigationUtil.goToActivity(AdminCreateProductActivity.class);
            } else {
                notificationUtil.showToast("Product creation failed", false);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // get image from gallery
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            productImageUri = saveImageToFile(this, imageUri);

            Glide.with(this)
                    .load(productImageUri)
                    .override(150, 150)
                    .centerCrop()
                    .into(imageViewProductImage);
        }
    }
}