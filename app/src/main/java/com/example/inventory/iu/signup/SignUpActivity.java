package com.example.inventory.iu.signup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventory.R;
import com.example.inventory.iu.DashBoardFragment;
import com.example.inventory.iu.InventoryActivity;
import com.example.inventory.iu.utils.CommonUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    private TextInputLayout tilUser;
    private TextInputLayout tilPassword;
    private TextInputLayout tilRepeatPassword;
    private TextInputLayout tilEmail;

    private TextInputEditText tieUser;
    private TextInputEditText tiePassword;
    private TextInputEditText tieRepeatPassword;
    private TextInputEditText tieEmail;

    private ProgressDialog progressDialog;

    private SignUpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Se recoge el usuario
        tieUser = findViewById(R.id.tieUsername);
        // Se recoge el password
        tiePassword = findViewById(R.id.tiePassword);
        // Se recoge el email
        tieEmail = findViewById(R.id.tieEmail);
        // Se recoge el confirmPassword
        tieRepeatPassword = findViewById(R.id.tieRepeatPassword);

        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        tilRepeatPassword = findViewById(R.id.tilRepeatPassword);
        tilUser = findViewById(R.id.tilUsername);

        presenter = new SignUpPresenter(this);

        tieUser.addTextChangedListener(new SignUpTextWatcher(tieUser));
        tiePassword.addTextChangedListener(new SignUpTextWatcher(tiePassword));
        tieRepeatPassword.addTextChangedListener(new SignUpTextWatcher(tieRepeatPassword) );
        tieEmail.addTextChangedListener(new SignUpTextWatcher(tieEmail));
    }

    /* Comprobamos las reglas de negocio */
    public void signUp(View view) {
        presenter.addUser(tieUser.getText().toString(),
                tiePassword.getText().toString(),
                tieRepeatPassword.getText().toString(),
                tieEmail.getText().toString());
    }

    /**
     * Se ha dado de alta el usuario y pasamos a la pantalla Login
     */
    @Override
    public void onSuccess() {
        Intent intent = new Intent(SignUpActivity.this, InventoryActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPasswordNotEqualError() {
        tilPassword.setError("Las contraseñas no son iguales");
    }

    @Override
    public void setUserExistsError() {
        Toast.makeText(this,"El usuario existe", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setEmailFormatError() {
        tilEmail.setError("El formato del email no es correcto");
    }

    @Override
    public void showProgressDialog() {
        progressDialog = CommonUtils.showLoadingDialog(this);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void setEmailEmptyError() {
        tilEmail.setError("El email está vacio");
    }

    @Override
    public void setUserEmptyError() {
        tilUser.setError("El usuario está vacio");
    }

    @Override
    public void setPasswordFormatError() {
        tilPassword.setError("El formato de la contraseña no es correcto");
    }

    @Override
    public void setPasswordEmptyError() {
        tilPassword.setError("La contraseña no puede estar vacia");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    class SignUpTextWatcher implements TextWatcher {
        private View view;

        SignUpTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId())
            {
                case R.id.tieUserName:
                    validateUser();
                    break;
                case R.id.tiePassword:
                    validatePassword();
                    break;
                case R.id.tieRepeatPassword:
                    validateConfirmPassword();
                    break;
                case R.id.tieEmail:
                    validateEmail();
                    break;
            }
        }

        private void validateUser() {
            if(tieUser.getText().toString().trim().isEmpty())
            {
                tieUser.setError(getString(R.string.err_user_empty));
                showSoftKeyboard(tieUser);
            }
            else
            {
                tilUser.setErrorEnabled(false);
            }
        }

        private void validatePassword() {
            if(tiePassword.getText().toString().trim().isEmpty())
            {
                tilPassword.setError(getString(R.string.err_password_empty));
                showSoftKeyboard(tiePassword);
            }
            else if(tiePassword.getText().toString().length()< 3)
            {
                tilPassword.setError(getString(R.string.err_password));
            }
            else
            {
                tilPassword.setErrorEnabled(false);
            }
        }

        private void validateConfirmPassword() {
            if (!tiePassword.getText().toString().trim().equals(tieRepeatPassword.getText().toString().trim())){
                tilRepeatPassword.setError(getString(R.string.err_repeat_password));
                showSoftKeyboard(tieRepeatPassword);
            } else {
                tilRepeatPassword.setErrorEnabled(false);
            }
        }

        private void validateEmail() {
            if (tieEmail.getText().toString().isEmpty()){
                tilEmail.setError(getString(R.string.err_email_empty));
                showSoftKeyboard(tieEmail);
            } else {
                tilEmail.setErrorEnabled(false);
            }
        }
    }

    //TODO duda de este metodo
    private void showSoftKeyboard(View view) {
        view.requestFocus();
        InputMethodManager imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imn.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
    }
}