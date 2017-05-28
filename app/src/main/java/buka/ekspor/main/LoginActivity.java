package buka.ekspor.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import buka.cus.progressbar.ProgressBarCircularIndeterminate;
import buka.sharedpref.SharedPrefBE;
import buka.sharedpref.UserBE;
import buka.utils.UtilBuEks;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.til_username)
    TextInputLayout tilUsername;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.tiet_username)
    TextInputEditText tietUsername;
    @BindView(R.id.tiet_password)
    TextInputEditText tietPassword;
    @BindView(R.id.iv_visible_password)
    ImageView ivVisiblePassword;
    @BindView(R.id.pb_login)
    ProgressBarCircularIndeterminate pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        checkCameraPermission();
        initAction();
    }

    private void initAction() {
        tietPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0)
                    ivVisiblePassword.setVisibility(View.VISIBLE);
                else
                    ivVisiblePassword.setVisibility(View.GONE);
            }
        });

        ivVisiblePassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        tietPassword.setInputType(InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                        tietPassword.setSelection(tietPassword.getText().length());
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        tietPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD
                                | InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                        tietPassword.setSelection(tietPassword.getText().length());
                        return true;
                }
                return false;
            }
        });

        pbLogin.setVisibility(View.GONE);
    }

    @OnClick(R.id.bt_login)
    public void actionLogin() {
        UtilBuEks.hideSoftKeyboard(getApplicationContext(), getCurrentFocus());

        submitForm();
    }

    private void submitForm() {
        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        pbLogin.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pbLogin.setVisibility(View.GONE);

                String email = tietUsername.getText().toString();
                UserBE user = new UserBE();
                user.setEmail(email);
                user.setName(email.split("\\@")[0]);
                new SharedPrefBE().setCurrentUser(user, getApplicationContext());

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, 3000); // 3 seconds
    }


    private boolean validateEmail() {
        tilUsername.setErrorEnabled(false);

        String email = tietUsername.getText().toString().trim();

        if (email.isEmpty() || !UtilBuEks.isValidEmail(email)) {
            tilUsername.setError("Invalid email");
            UtilBuEks.showSoftKeyboard(getApplicationContext(), tietUsername);
            return false;
        }

        return true;
    }

    private boolean validatePassword() {
        tilPassword.setErrorEnabled(false);

        String pass = tietPassword.getText().toString().trim();
        if (pass.length() > 0)
            ivVisiblePassword.setVisibility(View.VISIBLE);
        else
            ivVisiblePassword.setVisibility(View.GONE);

        if (pass.isEmpty() || (pass.length() < 6)) {
            tilPassword.setError("Invalid passowrd");
            UtilBuEks.showSoftKeyboard(getApplicationContext(), tietPassword);
            return false;
        }

        return true;
    }

    private static final int CAMERA_REQUEST_CODE = 1;

    @TargetApi(Build.VERSION_CODES.M)
    private void checkCameraPermission() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    CAMERA_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera
            } else {
                checkCameraPermission();
            }
        }
    }

}
