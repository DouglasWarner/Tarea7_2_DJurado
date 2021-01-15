package com.example.inventory.iu.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.inventory.R;
import com.example.inventory.iu.InventoryActivity;
import com.example.inventory.iu.preferences.InventoryPreferences;
import com.example.inventory.iu.signup.SignUpActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter;
    
    private String user;
    private String password;
    private TextInputLayout tilUser;
    private TextInputLayout tilPassword;
    private TextInputEditText tieUser;
    private TextInputEditText tiePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Se recoge el usuario
        tieUser = findViewById(R.id.tieUserName);
        // Se recoge el password
        tiePassword = findViewById(R.id.tiePassword);
        // Se recoge el componente TextInputLayout
        tilUser = findViewById(R.id.tilUserName);
        tilPassword = findViewById(R.id.tilPassword);
        
        // Añadimos LoginTextWatcher
        tieUser.addTextChangedListener(new LoginTextWatcher(tieUser));
        tiePassword.addTextChangedListener(new LoginTextWatcher(tiePassword));


        presenter = new LoginPresenter(this);
    }

    public void showSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    /**
     * Metodo que valida si el inicia de sesion es correcto
     *
     * @param view
     */
    public void validateUser(View view) {
        user = tieUser.getText().toString();
        password = tiePassword.getText().toString();

        hideSoftKeyboard(view);

        presenter.validateCredentials(user, password);
    }

    /**
     * Este metodo viene de la interfaz BaseView
     */
    @Override
    public void onSuccess() {
        // Solo cuando el Login es correcto se escribe el usuario en las preferencias
        InventoryPreferences.getInstance().putUser(tieUser.getText().toString(), tiePassword.getText().toString());

        Intent intent = new Intent(LoginActivity.this, InventoryActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Este metodo viene de la interfaz LoginContract.View
     */
    @Override
    public void showProgress() {
        findViewById(R.id.pbCarga).setVisibility(View.VISIBLE);
    }

    /**
     * Este metodo viene de la interfaz LoginContract.View
     */
    @Override
    public void hideProgress() {
        findViewById(R.id.pbCarga).setVisibility(View.GONE);
    }

    /**
     * Este metodo viene de la interfaz LoginContract.View
     */
    @Override
    public void setAuthenticationError() {
        // Version 1
//        Snackbar.make(findViewById(R.id.ltContainer), getResources().getString(R.string.err_authentication), Snackbar.LENGTH_LONG);
//        Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.err_authentication), Snackbar.LENGTH_SHORT).show();
        // Version 2
        showError(getResources().getString(R.string.err_authentication));
    }

    private void showError(String message)
    {
        // 1. Inflar la vista snackbar_view.xml
        View view = ((LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.snackbar_view, null);
        TextView tvMessageError = view.findViewById(R.id.tv_snackbar_error);
        tvMessageError.setText(message);

        // 2. Vamos a crear un objeto Snackbar
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"", Snackbar.LENGTH_SHORT);

        // 3. Vamos a personalizar el Layout Snackbar
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout)snackbar.getView();

        layout.setPadding(15,0,15,0);
        layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));

        // 4. Añadimo nuestro objeto View al layout
        layout.addView(view);

        // 5. Se muestra el snackba
        snackbar.show();
    }

    /**
     * Este metodo viene de la interfaz UserView
     */
    @Override
    public void setUserEmptyError() {
        tilUser.setError(getResources().getString(R.string.err_user_empty));
        showSoftKeyboard(tieUser);
    }

    /**
     * Este metodo viene de la interfaz UserView
     */
    @Override
    public void setPasswordFormatError() {
        tilPassword.setError(getResources().getString(R.string.err_password_format));
        showSoftKeyboard(tiePassword);
    }

    /**
     * Este metodo viene de la interfaz UserView
     */
    @Override
    public void setPasswordEmptyError() {
        tilPassword.setError(getResources().getString(R.string.err_password_empty));
        showSoftKeyboard(tiePassword);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    //region Clase que implementa la interfaz TextWatcher de login, de forma que controla si los campos TextInputEditText estan vacios
    class LoginTextWatcher implements TextWatcher {

        private View view;

        LoginTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.tieUserName:
                    validateUserName(tieUser.getText().toString());
                    break;
                case R.id.tiePassword:
                    validatePassword(tiePassword.getText().toString());
                    break;

            }
        }
    }

    private void validateUserName(String password) {
        if (tieUser.getText().toString().trim().isEmpty()) {
            tilUser.setError(getString(R.string.err_user_empty));
            showSoftKeyboard(tilUser);
        } else {
            tilUser.setErrorEnabled(false);
        }

    }

    private void validatePassword(String password) {
        if (tiePassword.getText().toString().trim().isEmpty()) {
            tilPassword.setError(getString(R.string.err_password_empty));
            showSoftKeyboard(tilPassword);

        } else if (tiePassword.getText().toString().length() < 3) {
            tilPassword.setError(getString(R.string.err_password_format));
            showSoftKeyboard(tilPassword);
        } else {
            tilPassword.setErrorEnabled(false);
        }

    }
    //endregion

    /**
     * Este metodo pone el foco en la vista y habilita el teclado virtual
     * @param view
     */
    public void showSoftKeyboard(View view)
    {
        view.requestFocus();
        InputMethodManager imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imn.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Este metodo oculta el teclado virtual
     * @param view
     */
    public void hideSoftKeyboard(View view)
    {
        view.requestFocus();
        InputMethodManager imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imn.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}