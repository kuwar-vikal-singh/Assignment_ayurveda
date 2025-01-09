package com.axel.assignment_ayurveda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.axel.assignment_ayurveda.ProductDetailActivity;
import com.axel.assignment_ayurveda.Model.Product;
import com.axel.assignment_ayurveda.R;
import com.axel.assignment_ayurveda.ProductScreenActivity;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList;
    private Context context;
    private final ProductScreenActivity productScreenActivity;

    public ProductAdapter(List<Product> productList, ProductScreenActivity productScreenActivity) {
        this.productList = productList;
        this.productScreenActivity = productScreenActivity;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        context = parent.getContext();
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productImageView.setImageResource(product.getImageResId());
        holder.productNameTextView.setText(product.getName());
        holder.productPriceTextView.setText("$" + product.getPrice());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("productId", product.getId());
            intent.putExtra("productName", product.getName());
            intent.putExtra("productPrice", product.getPrice());
            intent.putExtra("productDescription", product.getDescription());
            intent.putExtra("productImage", product.getImageResId());
            context.startActivity(intent);
        });
        holder.addToCartButton.setOnClickListener(v -> {
            productScreenActivity.addToCart(product);
            Toast.makeText(context, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productImageView;
        private final TextView productNameTextView;
        private final TextView productPriceTextView;
        private final Button addToCartButton;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.imageView3);
            productNameTextView = itemView.findViewById(R.id.productName);
            productPriceTextView = itemView.findViewById(R.id.productPrice);
            addToCartButton = itemView.findViewById(R.id.button);
        }
    }
}
