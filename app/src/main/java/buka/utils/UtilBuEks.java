package buka.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import buka.ekspor.main.R;
import buka.recycleview.model.Ekspor;

/**
 * Created by adikwidiasmono on 5/25/17.
 */

public class UtilBuEks {
    public static List<Ekspor> getPreparedData() {
        List<Ekspor> eksporList = new LinkedList<>();

        int[] covers = new int[]{
                R.drawable.a1_karbon_aktif,
                R.drawable.a2_kacang_mete,
                R.drawable.a3_mukena,
                R.drawable.a4_dompet_batok,
                R.drawable.a5_kursi_bambu,
                R.drawable.a6_lele,
                R.drawable.a7_nanas_madu,
                R.drawable.a8_lobster_tawar,
                R.drawable.a9_durian_merah,
                R.drawable.a10_bekicot,
                R.drawable.a11_belut};

        Ekspor a = new Ekspor(covers[0], "Karbon Batok Kelapa",
                "8 TON", UtilBuEks.getNextDayDate(0),
                "Tujuan pengiriman ke negara Jepang.\n" +
                        "▪ Tersedia mesh 6-8 inchi, 8-30 inchi, 12-30 inchi\n" +
                        "▪ Total ash content max 4%" +
                        "▪ Moisture content max 5%\n" +
                        "▪ Apparent density (Berat Jenis): 450 – 550 kg/m3\n" +
                        "▪ Iodine number : 800/1000 mg/g, 1 sak +- 20kg\n" +
                        "▪ Harga Rp. 320.000,-", UtilBuEks.getNextDayDate(1),
                "4 TON", UtilBuEks.getNextDayDate(2),
                1, "0 TON",
                "Rp. 750.000.000, -", true
        );
        eksporList.add(a);

        a = new Ekspor(covers[1], "Kacang Mete",
                "12 TON", UtilBuEks.getNextDayDate(3)
                , "Tujuan pengiriman ke USA. Mete harus utuh.\n" +
                "▪ Warna Putih Bersih\n" +
                "▪ Keutuhan 90%\n" +
                "▪ Dikeringkan dengan panas matahari\n" +
                "▪ Kacang mete cukup kering, kadar air 5%-10%\n" +
                "▪ Berasal dari biji kacang mete tua kering\n" +
                "▪ harga Rp. 150.000,- / kg", UtilBuEks.getNextDayDate(4),
                "7 TON", UtilBuEks.getNextDayDate(5),
                2, "0 TON",
                "Rp. 560.000.000,-", true
        );
        eksporList.add(a);

        a = new Ekspor(covers[2], "Mukena",
                "500.000 Potong", UtilBuEks.getNextDayDate(6),
                "Tujuan pengiriman ke Venezuela.\n" +
                        "▪ Bahan rayon bali, adem dan nyaman digunakan\n" +
                        "▪ Aplikasi bordir flanel lembut aneka motif dan warna menarik\n" +
                        "▪ Dilengkapi dengan tas cantik, sehingga mudah dibawa kemana-mana\n" +
                        "▪ harga Rp. 170.000,-", UtilBuEks.getNextDayDate(7),
                "300.000 Potong", UtilBuEks.getNextDayDate(8),
                3, "0 Potong",
                "Rp 670.000.000,-", true
        );
        eksporList.add(a);

        a = new Ekspor(covers[3], "Dompet Batok Kelapa",
                "300.000 Biji", UtilBuEks.getNextDayDate(9),
                "Tujuan pengiriman ke Arab Saudi.\n" +
                        "▪ Produk dompet dengan bahan dasar batok kelapa\n" +
                        "▪ Tersedia berbagai motif beragam dan unik\n" +
                        "▪ Harga Rp. 100.000,-", UtilBuEks.getNextDayDate(10),
                "150.000 Biji", UtilBuEks.getNextDayDate(11),
                4, "0 Biji",
                "Rp. 540.000.000,-", true
        );
        eksporList.add(a);

        a = new Ekspor(covers[4], "Kursi Bambu",
                "5.000 Biji", UtilBuEks.getNextDayDate(12),
                "Tujuan pengiriman ke Eropa.\n" +
                        "▪ Terbuat dari Bambu wulung (Gigantochloa atroviolacea), rotan dan finishing vernis.\n" +
                        "▪ Model kursi bambu silang\n" +
                        "▪ Harga Rp. 750.000,-", UtilBuEks.getNextDayDate(13),
                "3.000 Biji", UtilBuEks.getNextDayDate(14)
                , 1, "0 Biji",
                "Rp. 45.000.000,-", true
        );
        eksporList.add(a);

        a = new Ekspor(covers[5], "Lele Ekspor",
                "15 TON", UtilBuEks.getNextDayDate(15),
                "Tujuan ekspor USA dan Swiss.\n" +
                        "▪ Ukuran 1 lele 900 gr - 1500 gr\n" +
                        "▪ Lele fresh\n" +
                        "▪ Packaging higienis dan bersih\n" +
                        "▪ Harga Rp. 75.000,- / Kg", UtilBuEks.getNextDayDate(16),
                "10 TON", UtilBuEks.getNextDayDate(17),
                2, "0 TON",
                "Rp. 890.000.000,-", true
        );
        eksporList.add(a);

        a = new Ekspor(covers[6], "Nanas Madu",
                "150 TON", UtilBuEks.getNextDayDate(18),
                "Tujuan ekspor negara Jepang.\n" +
                        "▪ Berat 1 buah 1500 gr - 2000 gr\n" +
                        "▪ Buah matang pohon\n" +
                        "▪ Packaging bersih dan higienis\n" +
                        "▪ Harga Rp 45.000,- / Kg", UtilBuEks.getNextDayDate(19),
                "60 TON", UtilBuEks.getNextDayDate(20),
                3, "0 TON",
                "Rp. 870.000.000,-", true
        );
        eksporList.add(a);

        a = new Ekspor(covers[7], "Lobster Air Tawar",
                "2.500 TON", UtilBuEks.getNextDayDate(21),
                "Tujuan ekspor China dan Jepang.\n" +
                        "▪ Berat 1 ekor 900 gr - 1200 gr\n" +
                        "▪ Sehat, cangkang bersih\n" +
                        "▪ Cangkang mengkilat\n" +
                        "▪ Packaging higienis dan bersih\n" +
                        "▪ Harga Rp 450.000,- /Kg", UtilBuEks.getNextDayDate(22),
                "1.700 TON", UtilBuEks.getNextDayDate(23),
                4, "0 TON",
                "Rp. 760.000.000,-", true
        );
        eksporList.add(a);

        a = new Ekspor(covers[8], "Durian Merah",
                "15 TON", UtilBuEks.getNextDayDate(24),
                "Tujuan ekspor Rusia.\n" +
                        "▪ Berat 1 buah 1500 gr - 2000 gr\n" +
                        "▪ Buah tidak pecah\n" +
                        "▪ Packaging bersih dan rapi" +
                        "▪ Harga Rp. 60.000,- / Kg", UtilBuEks.getNextDayDate(25),
                "5 TON", UtilBuEks.getNextDayDate(26),
                1, "0 TON",
                "Rp. 670.000.000,-", true
        );
        eksporList.add(a);

        a = new Ekspor(covers[9], "Bekicot",
                "150 TON", UtilBuEks.getNextDayDate(27),
                "Tujuan ekspor Inggis.\n" +
                        "▪ Berat 1 ekor 200 gr keatas\n" +
                        "▪ Bekicot sudah bersih\n" +
                        "▪ Cangkang tidak pecah\n" +
                        "▪ Harga Rp. 950.000 / Kg", UtilBuEks.getNextDayDate(28),
                "100 TON", UtilBuEks.getNextDayDate(29),
                2, "0 TON",
                "Rp. 876.000.000,-", true
        );
        eksporList.add(a);

        a = new Ekspor(covers[10], "Belut Air Tawar",
                "15 TON", UtilBuEks.getNextDayDate(30),
                "Tujuan ekspor Belanda dan Perancis.\n" +
                        "▪ Berat 1 ekor 500 gr keatas\n" +
                        "▪ Belut sehat\n" +
                        "▪ Tidak ada bintik-bintik\n" +
                        "▪ Packaging rapi dan higienis\n" +
                        "▪ Harga Rp. 3500.000,- / Kg", UtilBuEks.getNextDayDate(31),
                "4 TON", UtilBuEks.getNextDayDate(32),
                3, "0 TON",
                "Rp. 89.000.000,-", true
        );
        eksporList.add(a);

        return eksporList;
    }

    public static Date getNextDayDate(Integer nextDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static float dpToPx(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float spToPx(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static void showMaterialDialog(Activity activity, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialogTheme);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("SELESAI",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public static void showMaterialDialog(Activity activity, String title, String message, final MaterialDialogClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialogTheme);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("SELESAI",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (clickListener != null)
                            clickListener.onSELESAIClicked(dialog, which);
                        else
                            dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        // display dialog
        dialog.show();
    }

    public interface MaterialDialogClickListener {
        public abstract void onSELESAIClicked(DialogInterface dialog, int which);
    }


    public static String formatDateddMMyyyy(Date date) {
        return formatDate("dd-MM-yyyy", date);
    }

    public static String formatDate(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showSoftKeyboard(Context context, View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
