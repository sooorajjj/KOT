package online.klok.kot.customAdapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

import online.klok.kot.R;
import online.klok.kot.models.ItemModel;
import online.klok.kot.models.ModifiersModel;
import online.klok.kot.models.OrderModel;

public class OrderSelected extends AppCompatActivity {

    private ListView listView1;
    private ListView listView2;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        Intent intent = getIntent();
        int orderId = intent.getIntExtra("parameter_name", 1);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int modifierId = bundle.getInt("modifierId");
            String url2 = "http://146.185.178.83/resttest/item/"+ modifierId + "/modifiers/";
            new JSONTask2().execute(url2);
        }


        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading, please wait.....");

        listView1 = (ListView) findViewById(R.id.listView1);
        listView2 = (ListView) findViewById(R.id.listView2);
        String url1 = "http://146.185.178.83/resttest/item/"+ orderId + "/";
        new JSONTask().execute(url1);

    }

    public class JSONTask extends AsyncTask<String, String, List<ItemModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<ItemModel> doInBackground(String... params) {
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
                JSONObject parentObject = new JSONObject(finalJson);
//                JSONArray parentArray = parentObject.getJSONArray("movies");
//                JSONArray parentArray = new JSONArray(finalJson);

                // finalBufferData stores all the data as string
//                StringBuffer finalBufferData = new StringBuffer();
                // for loop so it fetch all the json_object in the json_array

                List<ItemModel> itemModelList = new ArrayList<>();

                Gson gson = new Gson();
                ItemModel itemModel = gson.fromJson(finalJson, ItemModel.class);
                itemModelList.add(itemModel);
                return itemModelList;

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
        protected void onPostExecute(List<ItemModel> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            ItemAdapter adapter = new ItemAdapter(getApplicationContext(), R.layout.row_order_selected, result);
            listView1.setAdapter(adapter);
//            TODO need to set the data to the list
        }
    }

    public class ItemAdapter extends ArrayAdapter {

        public List<ItemModel> itemModelList;
        private int resource;
        private LayoutInflater inflater;

        public ItemAdapter(Context context, int resource, List<ItemModel> objects) {
            super(context, resource, objects);
            itemModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(resource, null);
                holder.tvItemName = (TextView) convertView.findViewById(R.id.tvItemName);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvItemName.setText("Item name                    " + itemModelList.get(position).getItemName());
            return convertView;
        }

        class ViewHolder {
            private TextView tvItemName;
        }
    }
    public class JSONTask2 extends AsyncTask<String, String, List<ModifiersModel> > {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected List<ModifiersModel> doInBackground(String... params) {
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

                List<ModifiersModel> modifiersModelList = new ArrayList<>();

                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    ModifiersModel modifiersModel = gson.fromJson(finalObject.toString(), ModifiersModel.class);
                    modifiersModelList.add(modifiersModel);
                }
                return modifiersModelList;

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
        protected void onPostExecute(List<ModifiersModel> result) {
            super.onPostExecute(result);
            ModifiersAdapter adapter = new ModifiersAdapter(getApplicationContext(), R.layout.row_order_selected, result);
            listView2.setAdapter(adapter);
//            TODO need to set the data to the list
        }
    }
    public class ModifiersAdapter extends ArrayAdapter {

        public List<ModifiersModel> modifiersModelList;
        private int resource;
        private LayoutInflater inflater;
        public ModifiersAdapter(Context context, int resource, List<ModifiersModel> objects) {
            super(context, resource, objects);
            modifiersModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if(convertView == null){
                holder = new ViewHolder();
                convertView=inflater.inflate(resource, null);
                holder.tvModifierName = (TextView)convertView.findViewById(R.id.tvModifierName);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvModifierName.setText("Modifier Name: " + modifiersModelList.get(position).getModifierName());
            return convertView;
        }

        class ViewHolder{
            private TextView tvModifierName;

        }
    }
}
