package online.klok.kot.customAdapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import online.klok.kot.R;


public class DisplayInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_display_info);

        TextView tvItemName = (TextView) findViewById(R.id.tvItemName);
        Intent intent = getIntent();

        String itemName = intent.getStringExtra("parameter_name");
                    tvItemName.setText("Item Name "+itemName);
//        Bundle bundle = intent.getExtras();
//
//        if(bundle!=null)
//        {
//            String itemName =(String) bundle.get("name");
//            tvItemName.setText(itemName);
//        }

    }
}
