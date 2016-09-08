package online.klok.kot.shopping_cart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import online.klok.kot.R;

/**
 * Created by NJ on 01/09/16.
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder> {
    public static final String LOG_TAG = "ShoppingCartAdapter";
    private final OnShoppingCartItemClicked callBack;

    private ArrayList<ShoppingCartPOJO> itemList = new ArrayList<>();

    public ShoppingCartAdapter(OnShoppingCartItemClicked callBack, ArrayList<ShoppingCartPOJO> itemList) {
        this.itemList = itemList;
        this.callBack = callBack;
    }

    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart, parent, false);
        return new ShoppingCartViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public ArrayList<ShoppingCartPOJO> getSelectedItems() {

        ArrayList<ShoppingCartPOJO> selectedItems = new ArrayList<>();

        for (ShoppingCartPOJO shoppingCartPOJO : itemList) {
            if (shoppingCartPOJO.getQty() > 0) {
                selectedItems.add(shoppingCartPOJO);
            }
        }
        return selectedItems;
    }

    @Override
    public void onBindViewHolder(final ShoppingCartViewHolder holder, int position) {

        holder.tvItemName.setText(itemList.get(position).getName());
        holder.tvItemPrice.setText("Price :" + itemList.get(position).getPrice());

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                itemList.get(holder.getAdapterPosition())
                        .setQty(itemList.get(holder.getAdapterPosition()).getQty() + 1);

                callBack.onShoppingCartAddClicked(itemList.
                        get(holder.getAdapterPosition()).getPrice());

                int qty = Integer.parseInt(holder.tvItemQty.getText().toString().trim());
                    qty++;
                    holder.tvItemQty.setText("" + qty);
            }
        });

        holder.tvItemQty.setText("" + itemList.get(position).getQty());

        holder.btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onShoppingCartLessClicked(itemList.
                        get(holder.getAdapterPosition()).getPrice());

                int qty = Integer.parseInt(holder.tvItemQty.getText().toString().trim());
                if(qty != 0) {
                    qty--;
                    holder.tvItemQty.setText("" + qty);
                }

            }
        });

    }



    public class ShoppingCartViewHolder extends RecyclerView.ViewHolder {


        TextView tvItemName, tvItemPrice, tvItemQty;
        Button btnAdd, btnLess;

        public ShoppingCartViewHolder(View itemView) {
            super(itemView);
            tvItemName = (TextView) itemView.findViewById(R.id.tv_name_of_item);
            tvItemPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
            btnAdd = (Button) itemView.findViewById(R.id.btn_add);
            // TODO set qty details to this TextView
            tvItemQty = (TextView) itemView.findViewById(R.id.tv_item_qty);
            btnLess = (Button) itemView.findViewById(R.id.btn_Less);

        }
    }

    // interface for call back to count and add total

    public interface OnShoppingCartItemClicked {
        void onShoppingCartAddClicked(double price);
        void onShoppingCartLessClicked(double price);
    }
}
