package online.klok.kot;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;

import online.klok.kot.models.CategoriesModel;
import online.klok.kot.models.ItemModel;
import online.klok.kot.shopping_cart.ShoppingCartPOJO;

/**
 * Created by NJ on 06/09/16.
 */
public class AppKOT extends Application {
    public static final String LOG_TAG = "AppKOT";

    public static ArrayList<ShoppingCartPOJO> cartItemSelected = new ArrayList<>();
    public static ArrayList<NewOrderPOJO> newOrderList = new ArrayList<>();
    public static ArrayList<ShoppingCartPOJO> onStartKot = new ArrayList<>();
    public static ArrayList<CategoriesModel> selectedCategoryList = new ArrayList<>();
    public static HashMap<String, Double> slectedItemQuantities = new HashMap<>();
    public static HashMap<String, ShoppingCartPOJO> selectedItemDetails = new HashMap<>();


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
