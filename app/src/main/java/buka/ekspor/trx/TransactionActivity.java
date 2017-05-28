package buka.ekspor.trx;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import buka.cus.view.CropCircleTransformation;
import buka.ekspor.main.LoginActivity;
import buka.ekspor.main.MainActivity;
import buka.ekspor.main.R;
import buka.ekspor.trx.fragment.DoneFragment;
import buka.ekspor.trx.fragment.ProceedFragment;
import buka.ekspor.trx.fragment.RecentFragment;
import buka.sharedpref.SharedPrefBE;
import buka.utils.UtilBuEks;

public class TransactionActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setupViewPager();
        setupNavigationView();
        setupToolbar();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fragment_recent, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // This method being called when invalidateOptionsMenu() executed
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        int pageNum = getCurrentPage();
//        if (pageNum == 0)
//            menu.findItem(R.id.action_qa).setVisible(true);
//        else
//            menu.findItem(R.id.action_qa).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout != null)
                    drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_qa:
                UtilBuEks.showMaterialDialog(TransactionActivity.this, "BANTUAN BukaEkspor",
                        "1. TERBARU\n" +
                                "Daftar kebutuhan ekspor yang sudah anda ambil dan harus dipenuhi " +
                                "lalu dikirim ke BukaEkspor pada tanggal yang telah ditentukan.\n" +
                                "2. DIPROSES\n" +
                                "Daftar hasil barang ekspor anda yang sudah dikirim. Pada bagian ini " +
                                "anda dapat memantau status barang anda mulai dari dikirim hingga pembayaran.\n" +
                                "3. SELESAI\n" +
                                "Daftar hasil transaksi anda yang sudah selesai.\n\n" +
                                "Jika ada yang ingin ditanyakan silahkan hubungi call center (021-111-222-333)");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
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
                        // Home
                        intent.setClass(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.user_transaction:
                        // Current active
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

        navigationView.getMenu().getItem(1).setChecked(true);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);// Show menu icon

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return RecentFragment.newInstance(position + 1);
                case 1:
                    return ProceedFragment.newInstance(position + 1);
                case 2:
                    return DoneFragment.newInstance(position + 1);
            }
            return RecentFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "TERBARU";
                case 1:
                    return "DIPROSES";
                case 2:
                    return "SELESAI";
            }
            return null;
        }
    }

}
