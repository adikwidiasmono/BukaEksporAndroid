package buka.recycleview.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import buka.cus.view.CropCircleTransformation;
import buka.ekspor.main.R;
import buka.recycleview.model.Ekspor;
import buka.utils.UtilBuEks;

/**
 * Created by adikwidiasmono on 5/23/17.
 */

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.MyViewHolder> {

    private Activity mActivity;
    private List<Ekspor> eksporList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView title, sendDate, kirim;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            title = (TextView) view.findViewById(R.id.tv_ekspor_title);
            sendDate = (TextView) view.findViewById(R.id.tv_send_date);
            kirim = (TextView) view.findViewById(R.id.tv_kirim);
            thumbnail = (ImageView) view.findViewById(R.id.iv_ekspor_thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }

    private EksporClickListener clickListener;

    public RecentAdapter(Activity mActivity, List<Ekspor> eksporList, EksporClickListener clickListener) {
        this.mActivity = mActivity;
        this.eksporList = eksporList;
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_recent, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Ekspor ekspor = eksporList.get(position);
        holder.title.setText(ekspor.getTitle() + " [" + ekspor.getWeightChoose() + "]");
        holder.sendDate.setText("Target kirim " + UtilBuEks.formatDateddMMyyyy(ekspor.getSendDate()));

        // loading ekspor cover using Glide library
        Glide.with(mActivity)
                .load(ekspor.getThumbnail())
                .bitmapTransform(new CropCircleTransformation(mActivity))
                .into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilBuEks.showMaterialDialog(mActivity, ekspor.getTitle(), ekspor.getDescription());
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onCardClicked(ekspor);
            }
        });

        holder.kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onKirimClicked(position);
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

        public abstract void onKirimClicked(Integer position);
    }

}
