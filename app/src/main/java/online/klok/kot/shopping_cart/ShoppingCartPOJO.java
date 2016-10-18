package online.klok.kot.shopping_cart;

/**
 * Created by NJ on 31/08/16.
 */
public class ShoppingCartPOJO {
    public static final String LOG_TAG = "ShoppingCartPOJO";


    String name, kitchenNote, type, categoryName, itemId;
    double price;
    double qty;
            int categoryId, selectedCategoryId;

    public static int kotId;
    int count;

    ShoppingCartPOJO() {

        if (kotId != 0) {
            kotId++;
            count = kotId;
        } else {
            count = kotId;
        }
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(int selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }

    public void setKotId(int count) {
        kotId = count;
    }

    public int getKotId() {
        return kotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKitchenNote() {
        return kitchenNote;
    }

    public void setKitchenNote(String kitchenNote) {
        this.kitchenNote = kitchenNote;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

}
