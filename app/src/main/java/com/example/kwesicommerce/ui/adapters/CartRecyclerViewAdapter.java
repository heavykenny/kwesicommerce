package com.example.kwesicommerce.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CartItem;
import com.example.kwesicommerce.data.repository.CartRepository;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.ui.activities.CartActivity;
import com.example.kwesicommerce.utils.NotificationUtil;

import java.util.List;

// REFERENCE: https://developer.android.com/develop/ui/views/layout/recyclerview
public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {
    private static CartRepository cartRepository = null;
    private static UserRepository userRepository = null;

    @SuppressLint("StaticFieldLeak")
    private static NotificationUtil notificationUtil = null;
    private static List<CartItem> cartItemList = null;

    private CartActivity cartActivity;

    public CartRecyclerViewAdapter(List<CartItem> cartItemList, Context context, CartActivity cartActivity) {
        CartRecyclerViewAdapter.cartItemList = cartItemList;
        cartRepository = new CartRepository(context);
        userRepository = new UserRepository(context);
        notificationUtil = new NotificationUtil(context);
        this.cartActivity = cartActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.bind(cartItem);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtViewProductListTitle;
        private final TextView txtViewProductPrice;
        private final Button btnQuantityCounter;
        private final Button btnRemoveProductCart;
        Button btnIncrementCounter;
        Button btnDecrementCounter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewProductListTitle = itemView.findViewById(R.id.txtViewProductListTitle);
            txtViewProductPrice = itemView.findViewById(R.id.txtViewProductPrice);
            btnQuantityCounter = itemView.findViewById(R.id.btnQuantityCounter);
            btnIncrementCounter = itemView.findViewById(R.id.btnIncrementCounter);
            btnDecrementCounter = itemView.findViewById(R.id.btnDecrementCounter);
            btnRemoveProductCart = itemView.findViewById(R.id.btnRemoveProductCart);
        }

        @SuppressLint("DefaultLocale")
        public void bind(CartItem cartItem) {
            txtViewProductListTitle.setText(cartItem.getProduct().getName());
            txtViewProductPrice.setText(String.valueOf(cartItem.getProduct().getPrice()));
            txtViewProductPrice.setText(String.format("£%.2f", cartItem.getProduct().getPrice()));
            btnQuantityCounter.setText(String.valueOf(cartItem.getQuantity()));

            btnIncrementCounter.setOnClickListener(v -> {
                int counter = Integer.parseInt(btnQuantityCounter.getText().toString());
                if (counter == cartItem.getProduct().getQuantity()) {
                    notificationUtil.showToast("This product only has " + cartItem.getProduct().getQuantity() + " items in stock");
                    return;
                }
                counter++;
                btnQuantityCounter.setText(String.valueOf(counter));
                cartRepository.updateCartItemQuantity(cartItem.getId(), counter);
                updateTotalPrice();
            });

            btnDecrementCounter.setOnClickListener(v -> {
                int counter = Integer.parseInt(btnQuantityCounter.getText().toString());
                if (counter > 1) {
                    counter--;
                    btnQuantityCounter.setText(String.valueOf(counter));
                    cartRepository.updateCartItemQuantity(cartItem.getId(), counter);
                    updateTotalPrice();
                }
            });

            btnRemoveProductCart.setOnClickListener(v -> {
                cartRepository.removeItemFromCart(cartItem.getId());
                updateTotalPrice();
                // remove from recycler view
                cartItemList.remove(cartItem);
                notifyItemRemoved(getAdapterPosition());

                notificationUtil.showToast("Product removed from cart");
            });
        }

        private void updateTotalPrice() {
            cartActivity.updateTotalPrice();
        }
    }
}