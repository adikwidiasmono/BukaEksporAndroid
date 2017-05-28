package buka.recycleview.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import buka.cus.view.CropCircleTransformation;
import buka.ekspor.main.R;
import buka.recycleview.model.Ekspor;
import buka.utils.UtilBuEks;

/**
 * Created by adikwidiasmono on 5/23/17.
 */

public class DoneAdapter extends RecyclerView.Adapter<DoneAdapter.MyViewHolder> {

    private Activity mActivity;
    private List<Ekspor> eksporList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView detail, title, weight, startDate, endDate, acceptWeight, rejectWeight, price, paymentStatus;
        public ImageView thumbnail;
        public LinearLayout llDetail;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            thumbnail = (ImageView) view.findViewById(R.id.iv_ekspor_thumbnail);
            detail = (TextView) view.findViewById(R.id.tv_detail);
            title = (TextView) view.findViewById(R.id.tv_ekspor_title);
            weight = (TextView) view.findViewById(R.id.tv_weight);
            startDate = (TextView) view.findViewById(R.id.tv_start);
            endDate = (TextView) view.findViewById(R.id.tv_end);
            acceptWeight = (TextView) view.findViewById(R.id.tv_accept_weigth);
            rejectWeight = (TextView) view.findViewById(R.id.tv_reject_weight);
            price = (TextView) view.findViewById(R.id.tv_price);
            paymentStatus = (TextView) view.findViewById(R.id.tv_payment_status);
            llDetail = (LinearLayout) view.findViewById(R.id.ll_detail);
            llDetail.setVisibility(View.GONE);
        }
    }

    private EksporClickListener clickListener;

    public DoneAdapter(Activity mActivity, List<Ekspor> eksporList, EksporClickListener clickListener) {
        this.mActivity = mActivity;
        this.eksporList = eksporList;
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_done, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Ekspor ekspor = eksporList.get(position);
        // loading ekspor cover using Glide library
        Glide.with(mActivity)
                .load(ekspor.getThumbnail())
                .bitmapTransform(new CropCircleTransformation(mActivity))
                .into(holder.thumbnail);

        holder.title.setText(ekspor.getTitle());
        holder.weight.setText("Berat " + ekspor.getWeightChoose());
        holder.startDate.setText("MULAI " + UtilBuEks.formatDateddMMyyyy(ekspor.getSendDate()));
        holder.startDate.setText("SELESAI " + UtilBuEks.formatDateddMMyyyy(ekspor.getProceedDate()));
        holder.acceptWeight.setText("Diterima " + ekspor.getWeightChoose());
        holder.rejectWeight.setText("Ditolak " + ekspor.getRejectWeight());
        holder.price.setText(ekspor.getPrice());
        if (ekspor.isPaymentStatus()) {
            holder.paymentStatus.setText("TERBAYAR");
            holder.paymentStatus.setTextColor(ContextCompat.getColor(mActivity, R.color.c_green));
        } else {
            holder.paymentStatus.setText("DIPROSES");
            holder.paymentStatus.setTextColor(ContextCompat.getColor(mActivity, R.color.c_yellow_dark));

        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onCardClicked(ekspor);
            }
        });

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.detail.getText().toString().equalsIgnoreCase("DETAIL")) {
                    holder.llDetail.setVisibility(View.VISIBLE);
                    holder.detail.setText("TUTUP");
                } else {
                    holder.llDetail.setVisibility(View.GONE);
                    holder.detail.setText("DETAIL");

                }
            }
        });

    }

    public void removeRecent(int position) {
        eksporList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return eksporList.size();
    }

    public interface EksporClickListener {
        public abstract void onCardClicked(Ekspor ekspor);
    }

}
