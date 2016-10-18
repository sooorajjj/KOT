package online.klok.kot.shopping_cart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import online.klok.kot.AppKOT;
import online.klok.kot.R;

public class ShoppingItemAdapter extends RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder> {

    public static final String LOG_TAG = "ShoppingItemAdapter";
    private final OnShoppingCartItemClicked callBack;

    private ArrayList<ShoppingCartPOJO> itemList = new ArrayList<>();

    public ShoppingItemAdapter(OnShoppingCartItemClicked callBack, ArrayList<ShoppingCartPOJO> itemList) {
        this.itemList = itemList;
        this.callBack = callBack;
    }

    @Override
    public ShoppingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart, parent, false);
        return new ShoppingItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ShoppingItemViewHolder holder, int position) {

        holder.tvItemName.setText(itemList.get(position).getName());
        holder.tvItemPrice.setText("Price :" + itemList.get(position).getPrice());

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ShoppingCartPOJO p = itemList.get(holder.getAdapterPosition());
                AppKOT.selectedItemDetails.put(p.getItemId(),p);

                if(AppKOT.slectedItemQuantities.containsKey(p.getItemId()))
                    AppKOT.slectedItemQuantities.put(p.getItemId(),AppKOT.slectedItemQuantities.get(p.getItemId()) + 1.0);
                else
                    AppKOT.slectedItemQuantities.put(p.getItemId(),1.0);

//                AppKOT.selectedItemDetails.put(itemList.get(holder.getAdapterPosition()).)

                itemList.get(holder.getAdapterPosition())
                        .setQty(itemList.get(holder.getAdapterPosition()).getQty() + 1);

                callBack.onShoppingCartAddClicked(itemList.
                        get(holder.getAdapterPosition()).getPrice());
            }
        });

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

    public class ShoppingItemViewHolder extends RecyclerView.ViewHolder {


        TextView tvItemName;
        TextView tvItemPrice;
        Button btnAdd;

        public ShoppingItemViewHolder(View itemView) {
            super(itemView);
            tvItemName = (TextView) itemView.findViewById(R.id.tv_name_of_item);
            tvItemPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
            btnAdd = (Button) itemView.findViewById(R.id.btn_add);

        }
    }

    // interface for call back to count and add total
    public interface OnShoppingCartItemClicked {
        void onShoppingCartAddClicked(double price);
    }

}