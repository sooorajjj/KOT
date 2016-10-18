package online.klok.kot.shopping_cart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import online.klok.kot.R;

/**
 * Created by klok on 15/10/16.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoriesItemViewHolder> {

    public static final String LOG_TAG = "ShoppingItemAdapter";
    private final OnCategoriesItemClicked callBack;

    private ArrayList<ShoppingCartPOJO> categoryList = new ArrayList<>();

    public CategoryAdapter(OnCategoriesItemClicked callBack, ArrayList<ShoppingCartPOJO> categoryList) {
        this.categoryList = categoryList;
        this.callBack = callBack;
    }
    @Override
    public CategoriesItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories, parent, false);
        return new CategoriesItemViewHolder(itemView);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final CategoriesItemViewHolder holder, int position) {

        holder.tvItemName.setText(categoryList.get(position).getCategoryName());
        holder.clickLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categoryList.get(holder.getAdapterPosition())
                        .setCategoryId(categoryList.get(holder.getAdapterPosition()).getCategoryId());

                callBack.onCategoriesItemClicked(categoryList.
                        get(holder.getAdapterPosition()).getCategoryId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public ArrayList<ShoppingCartPOJO> getSelectedItems() {

        ArrayList<ShoppingCartPOJO> selectedItems = new ArrayList<>();

        for (ShoppingCartPOJO shoppingCartPOJO : categoryList) {
            if (shoppingCartPOJO.getCategoryId() > 0) {
                selectedItems.add(shoppingCartPOJO);
            }
        }
        return selectedItems;
    }

    public class CategoriesItemViewHolder extends RecyclerView.ViewHolder {


        TextView tvItemName;
        LinearLayout clickLayout;

        public CategoriesItemViewHolder(View itemView) {
            super(itemView);
            tvItemName = (TextView) itemView.findViewById(R.id.tv_category_name);
            clickLayout = (LinearLayout) itemView.findViewById(R.id.clickLayout);
        }
    }

    // interface for call back to count and add total
    public interface OnCategoriesItemClicked {
        void onCategoriesItemClicked(int id);
    }
}
