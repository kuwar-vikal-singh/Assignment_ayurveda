package com.axel.assignment_ayurveda;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.axel.assignment_ayurveda.Adapter.CartAdapter;
import com.axel.assignment_ayurveda.Model.CartSingleton;
import com.axel.assignment_ayurveda.Model.Product;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private TextView cartCountTextView;
    private TextView totalPriceTextView;
    private TextView cartEmptyTextView;
    private CartAdapter cartAdapter;
    private List<Product> cartList;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cart_recycler_view);
        cartCountTextView = findViewById(R.id.cart_count_text);
        totalPriceTextView = findViewById(R.id.totalPrice);
        cartEmptyTextView = findViewById(R.id.cart_empty_text);
        linearLayout = findViewById(R.id.linearLayout);

        cartList = CartSingleton.getInstance().getCartList();

        if (cartList.isEmpty()) {
            cartRecyclerView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            cartEmptyTextView.setVisibility(View.VISIBLE);
        } else {
            cartRecyclerView.setVisibility(View.VISIBLE);
            cartEmptyTextView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(cartList, this);
        cartRecyclerView.setAdapter(cartAdapter);

        updateCartCount();
        updateTotalPrice();
    }

    private void updateCartCount() {
        int cartItemCount = CartSingleton.getInstance().getCartItemCount();
        if (cartItemCount > 0) {
            cartCountTextView.setText("(" + cartItemCount + ")");
            cartCountTextView.setVisibility(View.VISIBLE);
        } else {
            cartCountTextView.setVisibility(View.GONE);
        }
    }

    private void updateTotalPrice() {
        double totalPrice = 0;
        for (Product product : cartList) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        totalPriceTextView.setText("$" + String.format("%.2f", totalPrice));
    }
}
