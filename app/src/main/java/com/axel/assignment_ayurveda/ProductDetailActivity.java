package com.axel.assignment_ayurveda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.axel.assignment_ayurveda.Model.CartSingleton;
import com.axel.assignment_ayurveda.Model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImageView, cartImageView;
    private TextView nameTextView, priceTextView, descriptionTextView, quantityTextView, cartCountTextView;
    private Button addCartButton, buyNowButton;
    private TextView btnAdd, btnMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        productImageView = findViewById(R.id.productImageView);
        nameTextView = findViewById(R.id.nameTextView);
        priceTextView = findViewById(R.id.priceTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        quantityTextView = findViewById(R.id.quantity);
        btnAdd = findViewById(R.id.btnAdd);
        btnMinus = findViewById(R.id.btnMinus);
        addCartButton = findViewById(R.id.adCartBtn);
        buyNowButton = findViewById(R.id.buyNowButton);
        cartImageView = findViewById(R.id.cart_icon);
        cartCountTextView = findViewById(R.id.cart_count_text);


        updateCartCount();


        Intent intent = getIntent();
        int productId = intent.getIntExtra("productId", -1); // Fetch product ID
        String productName = intent.getStringExtra("productName");
        double productPrice = intent.getDoubleExtra("productPrice", 0.0);
        String productDescription = intent.getStringExtra("productDescription");
        int productImageResId = intent.getIntExtra("productImage", -1);


        nameTextView.setText(productName);
        priceTextView.setText("Price: $" + productPrice);
        descriptionTextView.setText(productDescription);
        productImageView.setImageResource(productImageResId);


        btnAdd.setOnClickListener(v -> {
            int quantity = Integer.parseInt(quantityTextView.getText().toString());
            quantity++;
            quantityTextView.setText(String.valueOf(quantity));
        });


        btnMinus.setOnClickListener(v -> {
            int quantity = Integer.parseInt(quantityTextView.getText().toString());
            if (quantity > 1) {
                quantity--;
                quantityTextView.setText(String.valueOf(quantity));
            }
        });

        addCartButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(quantityTextView.getText().toString());
            Product product = new Product(productId, productName, productImageResId, productPrice, productDescription, quantity);


            CartSingleton.getInstance().addToCart(product);

            updateCartCount();
        });


        buyNowButton.setOnClickListener(v -> {
            Toast.makeText(this, "Order will Deliver Soon!!", Toast.LENGTH_SHORT).show();
        });


        cartImageView.setOnClickListener(v -> {
            Intent cartIntent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(cartIntent);
        });
    }


    private void updateCartCount() {
        int cartItemCount = CartSingleton.getInstance().getCartItemCount();
        if (cartItemCount > 0) {
            cartCountTextView.setText(String.valueOf(cartItemCount));
            cartCountTextView.setVisibility(View.VISIBLE);
        } else {
            cartCountTextView.setVisibility(View.GONE);
        }
    }
}
