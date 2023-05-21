package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDatabaseHelper;
import com.example.kwesicommerce.data.model.CartItemModel;
import com.example.kwesicommerce.data.model.OrderModel;
import com.example.kwesicommerce.data.model.ProductModel;
import com.example.kwesicommerce.utils.FunctionUtil;

import java.util.List;

public class CartRepository {
    private final SQLiteDatabaseHelper dbHelper;
    private final Context context;

    public CartRepository(Context context) {
        dbHelper = new SQLiteDatabaseHelper(context);
        this.context = context;
    }

    public void addItemToCart(int userId, int productId, int quantity) {
        dbHelper.addToCart(userId, productId, quantity);
    }

    public void removeItemFromCart(int cartId) {
        dbHelper.removeFromCart(cartId);
    }

    public List<CartItemModel> getCartItems(int userId) {
        return dbHelper.getCartItems(userId);
    }

    public void updateCartItemQuantity(int userId, int quantity) {
        dbHelper.updateCartItemQuantity(userId, quantity);
    }

    public double getCartTotalPrice(int userId) {
        List<CartItemModel> cartItemModels = getCartItems(userId);
        double totalPrice = 0;
        for (CartItemModel item : cartItemModels) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }
        return totalPrice;
    }


    /**
     * This method is used to make an order from the cart items
     *
     * @param userId
     * @return orderId
     */
    public long makeOrder(int userId) {
        List<CartItemModel> cartItemModels = getCartItems(userId);
        OrderRepository orderRepository = new OrderRepository(context);
        // create an order for the user
        OrderModel orderModel = new OrderModel(userId, "Paid",
                FunctionUtil.generateOrderTracking(),
                this.getCartTotalPrice(userId),
                FunctionUtil.getCurrentDateTime(),
                FunctionUtil.getCurrentDateTime());

        long orderId = orderRepository.createOrder(orderModel);

        for (CartItemModel item : cartItemModels) {
            ProductModel productModel = item.getProduct();
            productModel.setQuantity(productModel.getQuantity() - item.getQuantity());
            dbHelper.updateProduct(productModel);
            orderRepository.createOrderItem((int) orderId, item.getProduct().getId(), item.getQuantity());
            dbHelper.removeFromCart(item.getId());
        }

        return orderId;
    }
}
