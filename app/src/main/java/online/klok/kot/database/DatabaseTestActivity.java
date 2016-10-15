package online.klok.kot.database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import online.klok.kot.R;

public class DatabaseTestActivity extends AppCompatActivity {

    private static final String LOG_TAG = DatabaseTestActivity.class.getSimpleName();


    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);

        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        StringBuilder sb = new StringBuilder();


        Cursor testdata = mDbHelper.getTestData();
        tvData = (TextView) findViewById(R.id.tv_data);
        if (testdata.moveToFirst()) {
            do
            {
                int columnsQty = testdata.getColumnCount();
                for (int idx = 0; idx < columnsQty; ++idx) {
                    sb.append(testdata.getString(idx));
                    if (idx < columnsQty - 1)
                        sb.append("; ");
                    }
                Log.e(LOG_TAG, String.format("Row: %d, Values: %s", testdata.getPosition(), sb.toString()));
            }
            while (testdata.moveToNext());
            tvData.setText(sb.toString());

        }

        mDbHelper.close();
    }
}
