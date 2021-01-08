package com.example.inventory.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.inventory.R;
import com.example.inventory.iu.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long WAIT_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    /**
     * En este metodo se debe ejecutar todas las operaciones de inicio de la aplicacion
     * como conectarse a una base de datos, a un servicior... Simulamos el tiempo de espera
     * con un hilo que duerme 2 segundos y cuando despierta ejecuta el metodo initLogin
     */
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initLogin();
            }
        },WAIT_TIME);
    }

    private void initLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        // Vamos a llamar de forma explicita al metodo finish() que destruye la activity
        // y no se muestra cuando se pulse el boton back
        finish();
    }
}