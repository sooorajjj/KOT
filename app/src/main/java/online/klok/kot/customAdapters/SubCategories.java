package online.klok.kot.customAdapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
import online.klok.kot.models.SubcategoryModel;

public class SubCategories extends AppCompatActivity {


    private ListView lvUsers;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        int categoryId = intent.getIntExtra("parameter_name", 1);

        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading, please wait.....");

        lvUsers = (ListView) findViewById(R.id.lvUsers);
        String url = "http://146.185.178.83/resttest/categories/" + categoryId  +"/subcategories/";
        new JSONTask().execute(url);
    }


    public class JSONTask extends AsyncTask<String, String, List<SubcategoryModel> > {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<SubcategoryModel> doInBackground(String... params) {
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

                List<SubcategoryModel> subcategoryModelList = new ArrayList<>();

                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    SubcategoryModel subcategoryModel = gson.fromJson(finalObject.toString(), SubcategoryModel.class);
                    subcategoryModelList.add(subcategoryModel);
                }
                return subcategoryModelList;

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
        protected void onPostExecute(List<SubcategoryModel> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            SubcategoryAdapter adapter = new SubcategoryAdapter(getApplicationContext(), R.layout.row_subcategory, result);
            lvUsers.setAdapter(adapter);
        }
    }

    public class SubcategoryAdapter extends ArrayAdapter {

        public List<SubcategoryModel> subcategoryModelList;
        private int resource;
        private LayoutInflater inflater;
        public SubcategoryAdapter(Context context, int resource, List<SubcategoryModel> objects) {
            super(context, resource, objects);
            subcategoryModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if(convertView == null){
                holder = new ViewHolder();
                convertView=inflater.inflate(resource, null);
                holder.bSubCategory = (Button) convertView.findViewById(R.id.bSubCategory);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.bSubCategory.setText(subcategoryModelList.get(position).getName());
            final int subcategoryId = subcategoryModelList.get(position).getId();
            holder.bSubCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(SubCategories.this, Items.class);
                    intent.putExtra("parameter_name", subcategoryId);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class ViewHolder{
            private Button bSubCategory;


        }
    }
}
