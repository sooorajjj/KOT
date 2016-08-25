package online.klok.kot.models;

/**
 * Created by klok on 25/8/16.
 */
public class TableModel {

    /**
     * id : 1
     * tableId : 100
     * name : table1
     * floorId : 1
     * created_at : null
     * updated_at : null
     */

    private int id;
    private String tableId;
    private String name;
    private int floorId;
    private Object created_at;
    private Object updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public Object getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Object created_at) {
        this.created_at = created_at;
    }

    public Object getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Object updated_at) {
        this.updated_at = updated_at;
    }
}
