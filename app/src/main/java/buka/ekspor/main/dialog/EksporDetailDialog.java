package buka.ekspor.main.dialog;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import buka.ekspor.main.MainActivity;
import buka.ekspor.main.R;
import buka.recycleview.model.Ekspor;
import buka.utils.UtilBuEks;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by adikwidiasmono on 5/24/17.
 */

public class EksporDetailDialog extends BottomSheetDialogFragment {
    private static final String TAG = "ScanResultDialog";

    @BindView(R.id.tv_dialog_title)
    TextView tvDialogTitle;
    @BindView(R.id.iv_tumbnail)
    ImageView ivTumbnail;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_need)
    TextView tvNeed;
    @BindView(R.id.tv_due_date)
    TextView tvDueDate;
    @BindView(R.id.tv_description)
    TextView tvDescription;

    @BindView(R.id.til_berat)
    TextInputLayout tilBerat;
    @BindView(R.id.tiet_berat)
    TextInputEditText tietBerat;

    @BindView(R.id.til_tgl_kirim)
    TextInputLayout tilTglKirim;
    @BindView(R.id.tiet_tgl_kirim)
    TextInputEditText tietTglKirim;

    BottomSheetBehavior bottomBehavior;

    private Ekspor ekspor;

    @Override
    public void setupDialog(Dialog dialog, int style) {
        ekspor = ((MainActivity) getActivity()).getEkspor();
        if (ekspor == null)
            return;

        Log.e(TAG, ekspor.toString());

        View contentView = View.inflate(getContext(), R.layout.dialog_ekspor_detail, null);
        ButterKnife.bind(this, contentView);

        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(false);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            bottomBehavior = ((BottomSheetBehavior) behavior);
            bottomBehavior.setPeekHeight(160);
            bottomBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        dismiss();
                    }
                    // Disable bottom sheet scroll behavior
//                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
//                        bottomBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });
        }

        setValue();
    }

    private void setValue() {
        // loading ekspor cover using Glide library
        Glide.with(getActivity())
                .load(ekspor.getThumbnail())
                .centerCrop()
                .into(ivTumbnail);
        tvDialogTitle.setText(ekspor.getTitle());
        tvTitle.setText(ekspor.getTitle());
        tvNeed.setText("Kebutuhan belum terpenuhi " + ekspor.getWeightNeed());
        tvDueDate.setText("Tanggal pengiriman " + UtilBuEks.formatDateddMMyyyy(ekspor.getDueDate()));
        tvDescription.setText(ekspor.getDescription());
    }

    @OnClick(R.id.tv_dialog_title)
    public void onTitleClicked() {
        if (bottomBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            bottomBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    @OnClick(R.id.iv_dialog_dismiss)
    public void dismissDialog() {
        dismiss();
    }

    @OnClick(R.id.tv_agree)
    public void onAgreeToJoin() {
        tilBerat.setErrorEnabled(false);
        tilTglKirim.setErrorEnabled(false);

        // Validate
        if (tietBerat.getText() == null || tietBerat.getText().length() < 1) {
            tilBerat.setError("Tidak valid");
            UtilBuEks.showSoftKeyboard(getActivity(), tietBerat);
            return;
        }
        if (tietTglKirim.getText() == null || tietTglKirim.getText().length() < 1) {
            tilTglKirim.setError("Tidak valid");
            UtilBuEks.showSoftKeyboard(getActivity(), tietTglKirim);
            return;
        }

        UtilBuEks.showToast(getActivity(),
                "Terima kasih. Silahkan lihat di transaksi anda untuk info lebih lanjut");
        dismiss();
    }

}
