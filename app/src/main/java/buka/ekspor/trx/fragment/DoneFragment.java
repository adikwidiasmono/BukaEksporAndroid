package buka.ekspor.trx.fragment;

/**
 * Created by adikwidiasmono on 5/26/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import buka.cus.view.CropCircleTransformation;
import buka.ekspor.main.R;
import buka.recycleview.adapter.DoneAdapter;
import buka.recycleview.adapter.ProceedAdapter;
import buka.recycleview.model.Ekspor;
import buka.utils.UtilBuEks;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class DoneFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public DoneFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static DoneFragment newInstance(int sectionNumber) {
        DoneFragment fragment = new DoneFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView recyclerView;
    private DoneAdapter adapter;

    private List<Ekspor> eksporList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_done_transaction, container, false);
        ButterKnife.bind(this, rootView);

        setupRecyclerView(rootView);
        return rootView;
    }

    private void setupRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        eksporList = new ArrayList<>();
        adapter = new DoneAdapter(getActivity(), eksporList, new DoneAdapter.EksporClickListener() {
            @Override
            public void onCardClicked(Ekspor ekspor) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
    }


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
//        int[] covers = new int[]{
//                R.drawable.album1,
//                R.drawable.album2,
//                R.drawable.album3,
//                R.drawable.album4,
//                R.drawable.album5,
//                R.drawable.album6,
//                R.drawable.album7,
//                R.drawable.album8,
//                R.drawable.album9,
//                R.drawable.album10,
//                R.drawable.album11};
//
//        Ekspor a;
//        Integer status = 0;
//        for (int i = 0; i < 11; i++) {
//            if (status > 3)
//                status = 0;
//            status++;
//
//            a = new Ekspor(covers[i], "Udang Super " + i, new Date(), (i + 1) + " Ton", new Date(), "0 Ton",
//                    "Rp 500.000.000,-", true);
//            eksporList.add(a);
//        }

        eksporList.add(UtilBuEks.getPreparedData().get(8));
        eksporList.add(UtilBuEks.getPreparedData().get(9));
        eksporList.add(UtilBuEks.getPreparedData().get(10));
        adapter.notifyDataSetChanged();
    }

}
