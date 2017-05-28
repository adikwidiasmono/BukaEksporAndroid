package buka.ekspor.trx.fragment;

/**
 * Created by adikwidiasmono on 5/26/17.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import buka.ekspor.main.R;
import buka.recycleview.adapter.EksporAdapter;
import buka.recycleview.adapter.RecentAdapter;
import buka.recycleview.model.Ekspor;
import buka.utils.UtilBuEks;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecentFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public RecentFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RecentFragment newInstance(int sectionNumber) {
        RecentFragment fragment = new RecentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView recyclerView;
    private RecentAdapter adapter;

    private List<Ekspor> eksporList;
    private Integer selectedCard = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recent_transaction, container, false);
        ButterKnife.bind(this, rootView);

        setupRecyclerView(rootView);
        return rootView;
    }

    private void setupRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        eksporList = new ArrayList<>();
        adapter = new RecentAdapter(getActivity(), eksporList, new RecentAdapter.EksporClickListener() {
            @Override
            public void onCardClicked(Ekspor ekspor) {

            }

            @Override
            public void onKirimClicked(Integer position) {
                selectedCard = position;
                dispatchTakePictureIntent();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            UtilBuEks.showMaterialDialog(getActivity(), "SIAP KIRIM",
                    "Silahkan kirim barang ekspor anda dan pantau status barang ekspor anda di bagian 'DIPROSES'",
                    new UtilBuEks.MaterialDialogClickListener() {
                        @Override
                        public void onSELESAIClicked(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if (selectedCard != null)
                                adapter.removeRecent(selectedCard);
                        }
                    });
        }
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        Ekspor a;
        for (int i = 0; i < 11; i++) {
            a = new Ekspor(covers[i], "Udang Super " + i, "Deskripsi udang " + i, new Date(), (i + 1) + " Ton");
            eksporList.add(a);
        }

        adapter.notifyDataSetChanged();
    }
}
