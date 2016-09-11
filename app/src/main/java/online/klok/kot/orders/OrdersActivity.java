package online.klok.kot.orders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import online.klok.kot.R;


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


}
