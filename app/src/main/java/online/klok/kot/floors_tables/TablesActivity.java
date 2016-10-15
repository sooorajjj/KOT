package online.klok.kot.floors_tables;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import online.klok.kot.NewOrderActivity;
import online.klok.kot.R;
import online.klok.kot.models.TableModel;
import online.klok.kot.settings.SettingsActivity;

public class TablesActivity extends AppCompatActivity implements TablesAdapter.OnTableItemClicked {

    private static final String LOG_TAG = FloorsActivity.class.getSimpleName();
    int floorId;
    private ProgressDialog dialog;
    private Toolbar toolbar;
    private TextView tvToolbarTitle;
    private RecyclerView rvTables;
    private List<TableModel> tableModelList = new ArrayList<>();
    private TablesAdapter adapter;
    private Button btnSettings;
    AlertDialog.Builder alert;
    EditText et_alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floors_tables);
        initViews();

        Intent intent = getIntent();
        floorId = intent.getIntExtra("FloorId", 1);

        String url = "http://146.185.178.83/resttest/floor/" + floorId + "/tables/";
        new JSONTask().execute(url);
    }

    private void initViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        tvToolbarTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Select Tables");

        alert = new AlertDialog.Builder(this);

        btnSettings = (Button) toolbar.findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_alert = new EditText(TablesActivity.this);
                et_alert.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                alert.setMessage("Admin Password");
                alert.setTitle("Password");

                alert.setView(et_alert);


                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value

                        String password = et_alert.getText().toString();

                        if (password.equals("1234"))
                        {
                            Intent intent = new Intent(TablesActivity.this, SettingsActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(TablesActivity.this, "Wrong Password" , Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });

                alert.show();

            }
        });


        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading, please wait.....");

        rvTables = (RecyclerView) findViewById(R.id.rv_floors_tables);
        rvTables.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        rvTables.setAdapter(new TablesAdapter(this, getTablesItems()));

    }

    private ArrayList<TablePOJO> getTablesItems() {
        ArrayList<TablePOJO> itemList = new ArrayList<>();

        for (int i = 0; i < tableModelList.size(); i++) {
            TablePOJO tablePOJO = new TablePOJO();
            tablePOJO.setName("" + tableModelList.get(i).getName());
            tablePOJO.setId(tableModelList.get(i).getId());
            itemList.add(tablePOJO);
        }

        Log.e(LOG_TAG, "Total items size :" + itemList.size());

        return itemList;
    }

    @Override
    public void onTableItemClicked(String name) {

        String floorName = "F" + floorId;

        Intent intent = new Intent(this, NewOrderActivity.class);
        intent.putExtra("FloorId", floorId);
        intent.putExtra("TableName", name);
        intent.putExtra("FloorName", floorName);
        startActivity(intent);
        finish();

        Toast.makeText(this, "Table Name : " + String.valueOf(name), Toast.LENGTH_SHORT).show();

    }


    public class JSONTask extends AsyncTask<String, String, List<TableModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<TableModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                JSONArray parentArray = new JSONArray(finalJson);

                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    TableModel tableModel = gson.fromJson(finalObject.toString(), TableModel.class);
                    tableModelList.add(tableModel);
                }
                return tableModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<TableModel> result) {
            super.onPostExecute(result);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            adapter = new TablesAdapter(TablesActivity.this, getTablesItems());
            rvTables.setAdapter(adapter);
        }
    }
}
