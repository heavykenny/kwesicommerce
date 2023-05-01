package com.example.kwesicommerce.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Category;
import com.example.kwesicommerce.data.model.Product;
import com.example.kwesicommerce.data.repository.CategoryRepository;
import com.example.kwesicommerce.data.repository.ProductRepository;
import com.example.kwesicommerce.utils.NavigationUtil;
import com.example.kwesicommerce.utils.NotificationUtil;

import java.util.List;

public class AdminCreateProductActivity extends AppCompatActivity {

    private Button btnUploadImage;

    private Button btnCreateCategory;
    private ImageView imageViewProductImage;
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
        List<Category> categories = categoryRepository.getAllCategories();

        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoryID.setAdapter(adapter);


        imageViewProductImage = findViewById(R.id.imageViewProductImage);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        // Handle image upload button click
        // Create image picker launcher
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                result -> {
                    if (result != null) {
                        Uri imageUri = result;
                        imageViewProductImage.setImageURI(imageUri);
                        imageViewProductImage.setVisibility(View.VISIBLE);
                    }
                });

        // Handle image upload button click
        btnUploadImage.setOnClickListener(v -> {
            // Open image selection dialog or gallery
            imagePickerLauncher.launch("image/*");
        });


        btnCreateCategory = findViewById(R.id.btnCreateCategory);
        btnCreateCategory.setOnClickListener(v -> {
            // Get the selected category
            Category selectedCategory = (Category) spinnerCategoryID.getSelectedItem();
            // Get the category ID
            int categoryID = selectedCategory.getId();
            // Get the product image
            String productImage = imageViewProductImage.toString();

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

            Product product = new Product(
                    0, productName, productDescription,
                    productImage,
                    Integer.parseInt(productQuantity),
                    Double.parseDouble(productPrice), Double.parseDouble(listPrice),
                    Double.parseDouble(retailPrice), categoryID);

            ProductRepository productRepository = new ProductRepository(getBaseContext());

            productRepository.createProduct(product);

            notificationUtil.showToast("Product has been created successfully");
        });

    }
}