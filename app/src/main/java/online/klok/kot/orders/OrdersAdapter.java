package online.klok.kot.orders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import online.klok.kot.AppKOT;
import online.klok.kot.R;

/**
 * Created by sooraj on 12/9/16.
 */
public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {
    public static final String LOG_TAG = "OrdersAdapter";

    private final OnOrdersItemClicked callBack;

    private ArrayList<OrdersPOJO> itemList = new ArrayList<>();
//
//    private ArrayList<OrdersPOJO> onStartKotList = new ArrayList<>();

//    private ArrayList<ArrayList<OrdersPOJO>> result = new ArrayList<>();

    public OrdersAdapter(OnOrdersItemClicked callBack, ArrayList<OrdersPOJO> itemList) {
        this.itemList = itemList;
        this.callBack = callBack;
    }

    @Override
    public OrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders, parent, false);
        return new OrdersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final OrdersViewHolder holder, int position) {

//        final int kotId;
//        if (AppKOT.onStartKot.size() > 0) {
//            kotId = result.get(1).get(position).getKotId();
//        } else {
//            kotId = result.get(0).get(position).getKotId();
//        }

//        holder.tvTable.setText(itemList.get(position).getTableName());
        holder.tvOrderNo.setText("Order #" + itemList.get(position).getOrderNo());
//        holder.tvCovers.setText("" + itemList.get(position).getCovers());
//        holder.tvKot.setText("" + kotId);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callBack.onOrdersEditItemClicked();
            }
        });

        holder.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onOrdersCheckoutItemClicked();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size(); // Same as itemList.size();
    }


    public interface OnOrdersItemClicked {
        void onOrdersEditItemClicked();
        void onOrdersCheckoutItemClicked();

    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {


        TextView tvTable, tvOrderNo, tvCovers, tvKot;
        Button btnNext, btnEdit;

        public OrdersViewHolder(View itemView) {
            super(itemView);
            tvTable = (TextView) itemView.findViewById(R.id.tv_table);
            tvOrderNo = (TextView) itemView.findViewById(R.id.tv_order_no);
            btnEdit = (Button) itemView.findViewById(R.id.btn_edit);
            btnNext = (Button) itemView.findViewById(R.id.btn_next);
            tvCovers = (TextView) itemView.findViewById(R.id.tv_covers);
            tvKot = (TextView) itemView.findViewById(R.id.tv_kot);


        }
    }

}
