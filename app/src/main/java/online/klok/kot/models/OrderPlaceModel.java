package online.klok.kot.models;

/**
 * Created by klok on 20/8/16.
 */
public class OrderPlaceModel {

    /**
     * id : 1
     * userId : 1
     * cardId : 1
     * created_at : 2016-08-18 05:42:12
     * updated_at : 2016-08-18 05:42:12
     */

    private int id;
    private int userId;
    private int cardId;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
