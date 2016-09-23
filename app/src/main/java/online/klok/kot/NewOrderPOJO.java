package online.klok.kot;

/**
 * Created by klok on 23/9/16.
 */
public class NewOrderPOJO {

    public static final String LOG_TAG = "NewOrderPOJO";

    String tableName, floorName;
    int orderNo, covers;

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getCovers() {
        return covers;
    }

    public void setCovers(int covers) {
        this.covers = covers;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
