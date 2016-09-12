package online.klok.kot.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import online.klok.kot.R;
import online.klok.kot.shopping_cart.CartActivity;


public class OrdersActivity extends AppCompatActivity implements OrdersAdapter.OnOrdersItemClicked {

    private static final String LOG_TAG = OrdersActivity.class.getSimpleName();

    private RecyclerView rvOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        initViews();
    }

    private void initViews() {

//        assert getSupportActionBar() != null;
//        getSupportActionBar().setTitle("My Orders");

        rvOrders = (RecyclerView) findViewById(R.id.rv_orders);
        rvOrders.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvOrders.setAdapter(new OrdersAdapter(this, getOrdersItems()));
    }

    private ArrayList<OrdersPOJO> getOrdersItems() {
        ArrayList<OrdersPOJO> itemList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            OrdersPOJO ordersPOJO = new OrdersPOJO();
            ordersPOJO.setName("Item " + (i + 1));
            ordersPOJO.setOrderNo(i + 1);
            itemList.add(ordersPOJO);
        }

        Log.e(LOG_TAG, "Total items size :" + itemList.size());

        return itemList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.testmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_add_item:
                addMenuItem();
                break;
        }
        return true;
    }

    private void addMenuItem() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
        finish();
    }


}
