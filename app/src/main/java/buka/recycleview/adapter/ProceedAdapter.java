package buka.recycleview.adapter;

import android.app.Activity;
import android.graphics.Typeface;
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
import buka.cus.view.MarkView;
import buka.ekspor.main.R;
import buka.recycleview.model.Ekspor;
import buka.utils.UtilBuEks;

/**
 * Created by adikwidiasmono on 5/23/17.
 */

public class ProceedAdapter extends RecyclerView.Adapter<ProceedAdapter.MyViewHolder> {

    private Activity mActivity;
    private List<Ekspor> eksporList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView title, weightChoose, proceedDate;
        public TextView pgDiterimaBE, pgKirim, pgSampai, pgSiapBayar;
        public MarkView markView;
        public LinearLayout sideColor;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            title = (TextView) view.findViewById(R.id.tv_ekspor_title);
            weightChoose = (TextView) view.findViewById(R.id.tv_weight);
            proceedDate = (TextView) view.findViewById(R.id.tv_proceed_date);
            markView = (MarkView) view.findViewById(R.id.status_mark);

            pgDiterimaBE = (TextView) view.findViewById(R.id.pg_diterima_be);
            pgKirim = (TextView) view.findViewById(R.id.pg_kirim);
            pgSampai = (TextView) view.findViewById(R.id.pg_sampai);
            pgSiapBayar = (TextView) view.findViewById(R.id.pg_siap_bayar);
            sideColor = (LinearLayout) view.findViewById(R.id.status_side_color);
        }
    }

    private EksporClickListener clickListener;

    public ProceedAdapter(Activity mActivity, List<Ekspor> eksporList, EksporClickListener clickListener) {
        this.mActivity = mActivity;
        this.eksporList = eksporList;
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_proceed, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Ekspor ekspor = eksporList.get(position);
        holder.title.setText(ekspor.getTitle());
        holder.weightChoose.setText("Berat " + ekspor.getWeightChoose());
        holder.proceedDate.setText("Tgl proses " + UtilBuEks.formatDateddMMyyyy(ekspor.getProceedDate()));

        holder.markView.setMark(ekspor.getStatus());
        holder.markView.setMax(4);
        holder.sideColor.setBackgroundColor(holder.markView.getStrokeColor());

        switch (ekspor.getStatus()) {
            case 1: {
                holder.pgDiterimaBE.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
                holder.pgDiterimaBE.setTypeface(null, Typeface.BOLD);
                break;
            }
            case 2: {
                holder.pgKirim.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
                holder.pgKirim.setTypeface(null, Typeface.BOLD);
                break;
            }
            case 3: {
                holder.pgSampai.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
                holder.pgSampai.setTypeface(null, Typeface.BOLD);
                break;
            }
            case 4: {
                holder.pgSiapBayar.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
                holder.pgSiapBayar.setTypeface(null, Typeface.BOLD);
                break;
            }
            default:
                break;
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onCardClicked(ekspor);
            }
        });

    }

    @Override
    public int getItemCount() {
        return eksporList.size();
    }

    public interface EksporClickListener {
        public abstract void onCardClicked(Ekspor ekspor);
    }

}
