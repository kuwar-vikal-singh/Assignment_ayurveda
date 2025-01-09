package com.axel.assignment_ayurveda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.axel.assignment_ayurveda.Adapter.ProductAdapter;
import com.axel.assignment_ayurveda.Model.CartSingleton;
import com.axel.assignment_ayurveda.Model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductScreenActivity extends AppCompatActivity {

    private TextView cartCountTextView;
    private List<Product> cartList;
    private ImageView  cartImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);


        cartCountTextView = findViewById(R.id.cart_count_text);
        cartImageView = findViewById(R.id.cart_icon);

        cartList = CartSingleton.getInstance().getCartList();
        cartImageView.setOnClickListener(v -> {
            Intent cartIntent = new Intent(ProductScreenActivity.this, CartActivity.class);
            startActivity(cartIntent);
        });
        // Sample product data
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "She Care Juice", R.drawable.item_image, 10.0, "Helps in hormonal balance in women | Helps in improve digestion & metabolism | Helps improve hair and skin health.", 1));
        productList.add(new Product(2, "Thyro balance juice", R.drawable.item_2_image, 15.0, "Natural thyroid Care Juice | Goodness of Durva, Kanchnar, Mandukparni, Brahmi & 20 other herbs.", 1));
        productList.add(new Product(3, "Naurcissus Juice for Fatigue", R.drawable.item_3_image, 20.0, "Blend of Shilajit, Ashwagandha, Safed Musli for Stamina, Vigor, Vitality, Fatigue & Energy Booster.", 1));
        productList.add(new Product(4, "Cholesterol Care Juice", R.drawable.item_1_image, 25.0, "Right mix of Apple Cider Vinegar, Ginger juice, Garlic juice, Lemon juice & Honey", 1));
        productList.add(new Product(5, "Himalayan Shilajit Resin Women", R.drawable.item_4_image, 30.0, "Natural & Pure Himalayan shilajit resin | Stamina, Vigour, Vitality & Energy Booster", 1));
        productList.add(new Product(6, "Kesharogyam Hair Oil", R.drawable.item_5_image, 30.0, "Promotes Strong And Healthy Hair", 1));
        productList.add(new Product(7, "Pure Neem Oil", R.drawable.item_6_image, 30.0, "100% Pure, Natural & Cold pressed Neem Oil | For Healthy Skin & Hair", 1));


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); // 2 columns
        recyclerView.setLayoutManager(gridLayoutManager);


        ProductAdapter adapter = new ProductAdapter(productList, this);
        recyclerView.setAdapter(adapter);


        updateCartCount();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateCartCount();
    }


    public void updateCartCount() {
        int cartItemCount = CartSingleton.getInstance().getCartItemCount();
        if (cartItemCount > 0) {
            cartCountTextView.setText(String.valueOf(cartItemCount));
            cartCountTextView.setVisibility(View.VISIBLE);
        } else {
            cartCountTextView.setVisibility(View.GONE);
        }
    }


    public void addToCart(Product product) {

        CartSingleton.getInstance().addToCart(product);


        updateCartCount();
    }
}
