package online.klok.kot.shopping_cart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import online.klok.kot.R;

public class ShoppingCartActivity extends AppCompatActivity implements ShoppingCartAdapter.OnShoppingCartItemClicked {

    private static final String LOG_TAG = ShoppingCartActivity.class.getSimpleName();

    private RecyclerView rvShoppingCart;
    private TextView tvTotalItems;
    private TextView tvTotalPrice;
    private int currentCartCount;
    private int currentTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        initViews();
    }

    private void initViews() {
        tvTotalItems = (TextView) findViewById(R.id.tv_total_items);
        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);


        tvTotalItems.setText("Total Item : " + currentCartCount);
        tvTotalPrice.setText("Total Price : " + currentTotalPrice);

        rvShoppingCart = (RecyclerView) findViewById(R.id.rv_shopping_cart);
        rvShoppingCart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvShoppingCart.setAdapter(new ShoppingCartAdapter(this, getShoppingCartItems()));

    }

    /**
     * Creates dummy data for now
     **/

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
