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
import com.example.kwesicommerce.data.model.CartItemModel;
import com.example.kwesicommerce.data.repository.CartRepository;
import com.example.kwesicommerce.ui.activities.CartActivity;
import com.example.kwesicommerce.utils.NotificationUtil;

import java.util.List;

// REFERENCE: https://developer.android.com/develop/ui/views/layout/recyclerview
public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {
    private static CartRepository cartRepository = null;
    @SuppressLint("StaticFieldLeak")
    private static NotificationUtil notificationUtil = null;
    private static List<CartItemModel> cartItemModelList = null;

    private CartActivity cartActivity;

    public CartRecyclerViewAdapter(List<CartItemModel> cartItemModelList, Context context, CartActivity cartActivity) {
        CartRecyclerViewAdapter.cartItemModelList = cartItemModelList;
        cartRepository = new CartRepository(context);
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
        CartItemModel cartItemModel = cartItemModelList.get(position);
        holder.bind(cartItemModel);
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
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
        public void bind(CartItemModel cartItemModel) {
            txtViewProductListTitle.setText(cartItemModel.getProduct().getName());
            txtViewProductPrice.setText(String.valueOf(cartItemModel.getProduct().getPrice()));
            txtViewProductPrice.setText(String.format("£%.2f", cartItemModel.getProduct().getPrice()));
            btnQuantityCounter.setText(String.valueOf(cartItemModel.getQuantity()));

            btnIncrementCounter.setOnClickListener(v -> {
                int counter = Integer.parseInt(btnQuantityCounter.getText().toString());
                if (counter == cartItemModel.getProduct().getQuantity()) {
                    notificationUtil.showToast("This product only has " + cartItemModel.getProduct().getQuantity() + " items in stock");
                    return;
                }
                counter++;
                btnQuantityCounter.setText(String.valueOf(counter));
                cartRepository.updateCartItemQuantity(cartItemModel.getId(), counter);
                updateTotalPrice();
            });

            btnDecrementCounter.setOnClickListener(v -> {
                int counter = Integer.parseInt(btnQuantityCounter.getText().toString());
                if (counter > 1) {
                    counter--;
                    btnQuantityCounter.setText(String.valueOf(counter));
                    cartRepository.updateCartItemQuantity(cartItemModel.getId(), counter);
                    updateTotalPrice();
                }
            });

            btnRemoveProductCart.setOnClickListener(v -> {
                cartRepository.removeItemFromCart(cartItemModel.getId());
                updateTotalPrice();
                // remove from recycler view
                cartItemModelList.remove(cartItemModel);
                notifyItemRemoved(getAdapterPosition());

                notificationUtil.showToast("ProductModel removed from cart");
            });
        }

        private void updateTotalPrice() {
            cartActivity.updateTotalPrice();
        }
    }
}