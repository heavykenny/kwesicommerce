package com.example.kwesicommerce.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Product;
import com.example.kwesicommerce.data.repository.CartRepository;
import com.example.kwesicommerce.data.repository.ProductRepository;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.utils.NavigationUtil;
import com.example.kwesicommerce.utils.NotificationUtil;

public class ProductDetailsActivity extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        UserRepository userRepository = new UserRepository(getBaseContext());


        LinearLayout active = findViewById(R.id.linLayoutShop);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        navigationUtil.backNavigation("Product Details");

        NotificationUtil notificationUtil = new NotificationUtil(getBaseContext());

        Intent intent = getIntent();
        int productId = intent.getIntExtra("productId", 0);

        ProductRepository productRepository = new ProductRepository(getBaseContext());
        Product product = productRepository.getProductById(productId);

        TextView txtViewProductListTitle = findViewById(R.id.txtViewProductListTitle);
        TextView txtViewProductPrice = findViewById(R.id.txtViewProductPrice);
        TextView txtViewProductListDescription = findViewById(R.id.txtViewProductListDescription);
        ImageView imgViewProductImage = findViewById(R.id.imgViewProductImage);

        txtViewProductListTitle.setText(product.getName());
        txtViewProductPrice.setText(String.format("£%.2f", product.getPrice()));
        txtViewProductListDescription.setText(product.getDescription());
        imgViewProductImage.setImageURI(Uri.parse(product.getImageUrl()));

        TextView txtViewCounter = findViewById(R.id.btnQuantityCounter);

        Button btnIncrementCounter = findViewById(R.id.btnIncrementCounter);
        btnIncrementCounter.setOnClickListener(v -> {
            int counter = Integer.parseInt(txtViewCounter.getText().toString());
            if (counter == product.getQuantity()) {
                notificationUtil.showToast("You can't add more than " + product.getQuantity() + " items to your cart");
                return;
            }
            counter++;
            txtViewCounter.setText(String.valueOf(counter));
        });

        Button btnDecrementCounter = findViewById(R.id.btnDecrementCounter);
        btnDecrementCounter.setOnClickListener(v -> {
            int counter = Integer.parseInt(txtViewCounter.getText().toString());
            if (counter > 1) {
                counter--;
                txtViewCounter.setText(String.valueOf(counter));
            }
        });


        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        CartRepository cartRepository = new CartRepository(getBaseContext());

        btnAddToCart.setOnClickListener(v -> {
            // validate user is logged in
            if (!userRepository.isUserLoggedIn()) {
                notificationUtil.showToast("You need to login to add items to your cart");
                return;
            }

            int counter = Integer.parseInt(txtViewCounter.getText().toString());
            if (counter < 1) {
                notificationUtil.showToast("You need to add at least one item to your cart");
                return;
            }
            cartRepository.addItemToCart(userRepository.getUserId(), productId, counter);

            notificationUtil.showToast("You have added " + counter + " " + product.getName() + " to your cart");
        });


        Button btnContinueShopping = findViewById(R.id.btnContinueShopping);
        Button btnProceedToCheckout = findViewById(R.id.btnProceedToCheckout);

        btnContinueShopping.setOnClickListener(v -> {
            Intent intent1 = new Intent(getBaseContext(), CategoryActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getBaseContext().startActivity(intent1);
            overridePendingTransition(0, 0);
        });

        btnProceedToCheckout.setOnClickListener(v -> {
            int counter = Integer.parseInt(txtViewCounter.getText().toString());
            if (counter < 1) {
                notificationUtil.showToast("You need to add at least one item to your cart");
                return;
            }
            cartRepository.addItemToCart(userRepository.getUserId(), productId, counter);

            Intent intent12 = new Intent(getBaseContext(), CartActivity.class);
            intent12.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getBaseContext().startActivity(intent12);
            overridePendingTransition(0, 0);
        });
    }
}