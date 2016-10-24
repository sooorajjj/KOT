package online.klok.kot.orders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import online.klok.kot.AppKOT;
import online.klok.kot.R;
import online.klok.kot.floors_tables.FloorsActivity;
import online.klok.kot.models.OrderModel;
import online.klok.kot.shopping_cart.CartActivity;


public class Orders extends AppCompatActivity implements OrdersAdapter.OnOrdersItemClicked {

    private static final String LOG_TAG = Orders.class.getSimpleName();

    RecyclerView rvOrders;
    Toolbar toolbar;
    TextView tvToolbarTitle;
    Button btnAddOrder;
    String url;
    private ProgressDialog dialog;
    private List<OrderModel> orderModelList = new ArrayList<>();
    OrdersAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        initViews();
    }

    private void initViews() {

        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading, please wait.....");

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tvToolbarTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        btnAddOrder = (Button) toolbar.findViewById(R.id.btn_add_order);

        btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newOrder();
            }
        });

        rvOrders = (RecyclerView) findViewById(R.id.rv_orders);
        rvOrders.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvOrders.setAdapter(new OrdersAdapter(this, getOrdersItems()));

        url = "http://146.185.178.83/resttest/order";
        new JSONTask().execute(url);
    }


    private ArrayList<OrdersPOJO> getOrdersItems() {
        ArrayList<OrdersPOJO> itemList = new ArrayList<>();

        for (int i = 0; i < orderModelList.size(); i++) {
            OrdersPOJO ordersPOJO = new OrdersPOJO();
            ordersPOJO.setOrderNo("" + orderModelList.get(i).getOrderId());
            itemList.add(ordersPOJO);
        }
        Log.e(LOG_TAG, "Total items size :" + itemList.size());

        return itemList;
    }

    public class JSONTask extends AsyncTask<String, String, List<OrderModel> > {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<OrderModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line ="";
                while ((line=reader.readLine()) !=null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
//                JSONObject parentObject = new JSONObject(finalJson);
//                JSONArray parentArray = parentObject.getJSONArray("movies");
                JSONArray parentArray = new JSONArray(finalJson);

                // finalBufferData stores all the data as string
//                StringBuffer finalBufferData = new StringBuffer();
                // for loop so it fetch all the json_object in the json_array

                Gson gson = new Gson();
                orderModelList.clear();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    OrderModel orderModel = gson.fromJson(finalObject.toString(), OrderModel.class);
                    orderModelList.add(orderModel);
                }
                return orderModelList;

            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection !=null) {
                    connection.disconnect();
                }
                try {
                    if (reader !=null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(List<OrderModel> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            adapter = new OrdersAdapter(Orders.this, getOrdersItems());
            rvOrders.setAdapter(adapter);        }
    }

    private void newOrder() {
        Toast.makeText(this, "Perform  newOrder", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FloorsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onOrdersEditItemClicked() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onOrdersCheckoutItemClicked() {
        Toast.makeText(this, "CheckoutItemClicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, OrderCheckoutActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Do nothing onBackPressed
    }

}
