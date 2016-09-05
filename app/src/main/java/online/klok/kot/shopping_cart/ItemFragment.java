package online.klok.kot.shopping_cart;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import online.klok.kot.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment implements ShoppingCartAdapter.OnShoppingCartItemClicked {

    private static final String LOG_TAG = ItemFragment.class.getSimpleName();

    private RecyclerView rvShoppingCart;
    private TextView tvTotalItems, tvTotalPrice;
    private int currentCartCount, currentTotalPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        tvTotalItems = (TextView) view.findViewById(R.id.tv_total_items);
        tvTotalPrice = (TextView) view.findViewById(R.id.tv_total_price);


        tvTotalItems.setText("Total Item : " + currentCartCount);
        tvTotalPrice.setText("Total Price : " + currentTotalPrice);

        rvShoppingCart = (RecyclerView) view.findViewById(R.id.rv_shopping_cart);
        rvShoppingCart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvShoppingCart.setAdapter(new ShoppingCartAdapter(this, getShoppingCartItems()));

        return view;
    }

    private ArrayList<ShoppingCartPOJO> getShoppingCartItems() {
        ArrayList<ShoppingCartPOJO> itemList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            ShoppingCartPOJO shoppingCartPOJO = new ShoppingCartPOJO();
            shoppingCartPOJO.setName("Item " + (i + 1));
            shoppingCartPOJO.setPrice(i + 1);
            itemList.add(shoppingCartPOJO);
        }

        Log.e(LOG_TAG, "Total items size :" + itemList.size());

        return itemList;
    }

    @Override
    public void onShoppingCartAddClicked(int price) {


        currentCartCount = currentCartCount + 1;
        currentTotalPrice = currentTotalPrice + price;


        tvTotalItems.setText("Total Item : " + currentCartCount);
        tvTotalPrice.setText("Total Price : " + currentTotalPrice);


    }

}
