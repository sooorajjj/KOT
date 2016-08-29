package online.klok.kot.customAdapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import online.klok.kot.R;


public class CartDisplay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_cart_display);

        TextView tvItemName = (TextView) findViewById(R.id.tvItemName);
        TextView tvSalesRate = (TextView) findViewById(R.id.tvSalesRate);
        TextView tvTotalAmount = (TextView) findViewById(R.id.tvTotalAmount);

        Intent intent = getIntent();

        String itemName = intent.getStringExtra("itemName");
        tvItemName.setText(itemName);
        String salesRate = intent.getStringExtra("salesRate");
        tvSalesRate.setText("RS"+salesRate);
        tvTotalAmount.setText("Total: RS"+salesRate);
//        Bundle bundle = intent.getExtras();
//
//        if(bundle!=null)
//        {
//            String itemName =(String) bundle.get("itemName");
//            tvItemName.setText(itemName);
//            String salesRate =(String) bundle.get("salesRate");
//            tvSalesRate.setText(salesRate);
//        }
//
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem show_cart = menu.findItem(R.id.action_show_cart);
        show_cart.setVisible(false);
        return true;
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

    //    private void aboutMenuItem(){
//        new AlertDialog.Builder(this)
//                .setTitle("About")
//                .setMessage("Klok Innovations"+" Copyright 2015 ©. All rights reserved")
//                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                }).show();
//    }
    private void addMenuItem() {
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
        finish();
    }
}
