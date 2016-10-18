package online.klok.kot.shopping_cart;

import android.app.ProgressDialog;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import online.klok.kot.models.CategoriesModel;


public class CategoriesFragment extends Fragment implements CategoryAdapter.OnCategoriesItemClicked {

    private static final String LOG_TAG = CategoriesFragment.class.getSimpleName();

    private ProgressDialog dialog;
    private RecyclerView  rvCategories;
    private List<CategoriesModel> categoriesModelList = new ArrayList<>();
    private CategoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dialog = new ProgressDialog(getActivity());
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading, please wait.....");

        String url = "http://146.185.178.83/resttest/categories/";

        new JSONTask().execute(url);

        rvCategories = (RecyclerView) view.findViewById(R.id.rv_categories);
        rvCategories.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvCategories.setAdapter(new CategoryAdapter(this, getCategories()));

        return view;
    }

    private ArrayList<ShoppingCartPOJO> getCategories() {
        ArrayList<ShoppingCartPOJO> categoryList = new ArrayList<>();

        for (int i = 0; i < categoriesModelList.size(); i++) {
            ShoppingCartPOJO shoppingCartPOJO = new ShoppingCartPOJO();
            shoppingCartPOJO.setCategoryName("" + categoriesModelList.get(i).getName());
            shoppingCartPOJO.setCategoryId(Integer.parseInt("" + categoriesModelList.get(i).getId()));
            categoryList.add(shoppingCartPOJO);
        }

        Log.e(LOG_TAG, "Total items size :" + categoryList.size());

        return categoryList;
    }

    @Override
    public void onCategoriesItemClicked(int id) {


//        Intent intent = new Intent(getActivity(), ItemFragment.class);
//        intent.putExtra("CategoryId", id);
//        startActivity(intent);

//        for (int i = 0; i < categoriesModelList.size(); i++) {
//            ShoppingCartPOJO shoppingCartPOJO = new ShoppingCartPOJO();
//            shoppingCartPOJO.setSelectedCategoryId(id);
//            selectedCategoryList.add(shoppingCartPOJO);
//        }


        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabs);
        for (Fragment f:getFragmentManager().getFragments()) {

            if( f instanceof ItemFragment){
                ItemFragment itf = (ItemFragment)f;
                itf.updateItems(String.valueOf(id));
            }

        }
        tabLayout.getTabAt(1).select();


    }

    public class JSONTask extends AsyncTask<String, String, List<CategoriesModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<CategoriesModel> doInBackground(String... params) {
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
//                JSONObject parentObject = new JSONObject(finalJson);
//                JSONArray parentArray = parentObject.getJSONArray("movies");
                JSONArray parentArray = new JSONArray(finalJson);

                // finalBufferData stores all the data as string
//                StringBuffer finalBufferData = new StringBuffer();
                // for loop so it fetch all the json_object in the json_array


                Gson gson = new Gson();
                categoriesModelList.clear();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    CategoriesModel categoriesModel = gson.fromJson(finalObject.toString(), CategoriesModel.class);
                    categoriesModelList.add(categoriesModel);
                }
                return categoriesModelList;

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
        protected void onPostExecute(List<CategoriesModel> result) {
            super.onPostExecute(result);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            adapter = new CategoryAdapter(CategoriesFragment.this, getCategories());
            rvCategories.setAdapter(adapter);
        }
    }
}

