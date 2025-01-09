package com.axel.assignment_ayurveda.Model;

import java.util.ArrayList;
import java.util.List;

public class CartSingleton {

    private static CartSingleton instance;
    private List<Product> cartList;

    private CartSingleton() {
        cartList = new ArrayList<>();
    }

    public static CartSingleton getInstance() {
        if (instance == null) {
            instance = new CartSingleton();
        }
        return instance;
    }

    public List<Product> getCartList() {
        return cartList;
    }

    public void addToCart(Product product) {

        for (Product p : cartList) {
            if (p.getId() == product.getId()) {
                p.setQuantity(p.getQuantity() + product.getQuantity());
                return;
            }
        }

        cartList.add(product);
    }

    public int getCartItemCount() {
        return cartList.size();
    }

    public void clearCart() {
        cartList.clear();
    }

    public void removeItem(Product product) {
        cartList.remove(product);
    }
}
