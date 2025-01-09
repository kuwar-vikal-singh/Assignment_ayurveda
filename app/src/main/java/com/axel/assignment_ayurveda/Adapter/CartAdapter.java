package com.axel.assignment_ayurveda.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.axel.assignment_ayurveda.Model.Product;
import com.axel.assignment_ayurveda.R;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> cartList;
    private Context context;

    // Constructor to pass the cart list and context
    public CartAdapter(List<Product> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_product, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartList.get(position);
        holder.productNameTextView.setText(product.getName());
        holder.productQuantityTextView.setText("Quantity: " + product.getQuantity());
        holder.productPriceTextView.setText("Price: $" + product.getPrice() * product.getQuantity()); // Show price based on quantity
        holder.productImageView.setImageResource(product.getImageResId()); // Set the image resource
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public static class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImageView; // ImageView for product image
        private TextView productNameTextView;
        private TextView productQuantityTextView;
        private TextView productPriceTextView;

        public CartViewHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.cart_product_image); // Bind ImageView
            productNameTextView = itemView.findViewById(R.id.cart_product_name);
            productQuantityTextView = itemView.findViewById(R.id.cart_product_quantity);
            productPriceTextView = itemView.findViewById(R.id.cart_product_price);
        }
    }
}
