package buka.recycleview.adapter;

import android.content.Context;
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

import buka.ekspor.main.R;
import buka.recycleview.model.Ekspor;
import buka.utils.UtilBuEks;

/**
 * Created by adikwidiasmono on 5/23/17.
 */

public class EksporAdapter extends RecyclerView.Adapter<EksporAdapter.MyViewHolder> {

    private Context mContext;
    private List<Ekspor> eksporList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView title, detail, dueDate;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            title = (TextView) view.findViewById(R.id.tv_ekspor_title);
            detail = (TextView) view.findViewById(R.id.tv_ekspor_detail);
            dueDate = (TextView) view.findViewById(R.id.tv_due_date);
            thumbnail = (ImageView) view.findViewById(R.id.iv_ekspor_thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            overflow.setVisibility(View.GONE);
        }
    }

    private EksporClickListener clickListener;

    public EksporAdapter(Context mContext, List<Ekspor> eksporList, EksporClickListener clickListener) {
        this.mContext = mContext;
        this.eksporList = eksporList;
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_ekspor, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Ekspor ekspor = eksporList.get(position);
        holder.title.setText(ekspor.getTitle());
        holder.detail.setText(ekspor.getWeightNeed());
        holder.dueDate.setText(UtilBuEks.formatDateddMMyyyy(ekspor.getDueDate()));

        // loading ekspor cover using Glide library
        Glide.with(mContext).load(ekspor.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onCardClicked(ekspor);
            }
        });

    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_ekspor, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return eksporList.size();
    }

    public interface EksporClickListener {
        public abstract void onCardClicked(Ekspor ekspor);
    }

}
