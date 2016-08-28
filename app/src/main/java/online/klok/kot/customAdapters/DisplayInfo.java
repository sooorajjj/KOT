package online.klok.kot.customAdapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import online.klok.kot.R;


public class DisplayInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_display_info);

        TextView tvItemName = (TextView) findViewById(R.id.tvItemName);
        TextView tvSalesRate = (TextView) findViewById(R.id.tvSalesRate);
        Intent intent = getIntent();

        String itemName = intent.getStringExtra("itemName");
        tvItemName.setText(itemName);
        String salesRate = intent.getStringExtra("salesRate");
        tvSalesRate.setText(salesRate);
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
