package buka.ekspor.main;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import buka.cus.view.CropCircleTransformation;
import buka.ekspor.main.dialog.EksporDetailDialog;
import buka.ekspor.trx.TransactionActivity;
import buka.recycleview.model.Ekspor;
import buka.recycleview.GridSpacingItemDecoration;
import buka.recycleview.adapter.EksporAdapter;
import buka.sharedpref.SharedPrefBE;
import buka.utils.UtilBuEks;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EksporAdapter adapter;
    DrawerLayout drawerLayout;

    private Ekspor ekspor;
    private List<Ekspor> eksporList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigationView();
        setupToolbar();
        initCollapsingToolbar();
        setupRecyclerView();

        try {
            Glide.with(this)
                    .load(R.drawable.cover1)
                    .centerCrop()
                    .into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout != null)
                    drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Drawer items action here
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Intent intent = new Intent();

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        // Current ACTIVE
                        break;
                    case R.id.user_transaction:
                        // User Transaction
                        intent.setClass(getApplicationContext(), TransactionActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.logout:
                        // Clear current user from preferences
                        SharedPrefBE.clearCurrentUser(getApplicationContext());

                        intent.setClass(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;
                }
                return true;
            }

        });

        // Navigation header
        View header = navigationView.inflateHeaderView(R.layout.nav_header);

        // Use to open user profile
        final View headerImage = header.findViewById(R.id.profile_image_layout);
        headerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                if (drawerLayout != null)
                    drawerLayout.closeDrawers();
            }
        });

        final ImageView userPic = (ImageView) header.findViewById(R.id.profile_image);
        final ImageView userPicBg = (ImageView) header.findViewById(R.id.profile_image_bg);
        TextView userName = (TextView) header.findViewById(R.id.username);
        TextView userEmail = (TextView) header.findViewById(R.id.email);

        userName.setText(SharedPrefBE.getCurrentUser(getApplicationContext()).getName());
        userEmail.setText(SharedPrefBE.getCurrentUser(getApplicationContext()).getEmail());

        String imgURL = "";

        Glide.with(getApplicationContext())
                .load(imgURL)
                .placeholder(R.drawable.ic_img_default_black_48dp) // Default image
                .error(R.drawable.ic_broken_image_black_24dp) // image for error load
                .bitmapTransform(new CropCircleTransformation(getApplicationContext()))
                .into(userPic);

        Glide.with(getApplicationContext())
                .load(R.drawable.bg_white)
                .bitmapTransform(new CropCircleTransformation(getApplicationContext()))
                .into(userPicBg);

        navigationView.getMenu().getItem(0).setChecked(true);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);// Show menu icon

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        eksporList = new ArrayList<>();
        adapter = new EksporAdapter(this, eksporList, new EksporAdapter.EksporClickListener() {
            @Override
            public void onCardClicked(Ekspor ekspor) {
                setEkspor(ekspor);
                BottomSheetDialogFragment scanResult = new EksporDetailDialog();
                scanResult.show(getSupportFragmentManager(), scanResult.getTag());
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, UtilBuEks.dpToPx(getApplicationContext(), 10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        eksporList.addAll(UtilBuEks.getPreparedData());
        adapter.notifyDataSetChanged();
    }

    public Ekspor getEkspor() {
        return ekspor;
    }

    public void setEkspor(Ekspor ekspor) {
        this.ekspor = ekspor;
    }
}
