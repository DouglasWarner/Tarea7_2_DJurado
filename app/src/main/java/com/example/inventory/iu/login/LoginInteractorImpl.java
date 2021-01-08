package com.example.inventory.iu.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.inventory.data.repository.UserRepository;
import com.example.inventory.iu.utils.CommonUtils;

public class LoginInteractorImpl {

    private LoginInteractor listener;

    /*
     * Definición de las interfaces que debe implementar cualquier presentador
     * que haga uso del Interactor.
     */
    public interface LoginInteractor {

        void onUserEmptyError(); //RN-U1 y Alternativa 1.1

        void onPasswordEmptyError(); //RN-U1 y Alternativa 1.1

        void onPasswordFormatError(); //RN-U2 y Alternativa 1.2

        void onAuthenticationError(); //Alternativa 1.3

        void onSuccess(); //Secuencia normal del caso de uso.
    }

    public LoginInteractorImpl(LoginInteractor listener) {
        this.listener = listener;
    }

    /**
     * Este método contiene la lógica empresarial del caso de uso UC1-Login
     * Se crea un hilo para simular el retardo de dos segundos y ver cómo en la
     * vista se visualiza una barra de progreso.
     *
     * @param user
     * @param password
     */
    public void validateCredentials(final String user, final String password) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //Reglas de negocio y  Alternativas del caso de uso UC1-Login
                //RN-U1 y Alternativa 1.1.: el usuario no puede ser nulo
                if (TextUtils.isEmpty(user)) {
                    listener.onUserEmptyError();
                    return;
                }

                //RN-U1 y Alternativa 1.1.: el passowrd no puede ser nulo
                if (TextUtils.isEmpty(password)) {
                   listener.onPasswordEmptyError();
                   return;
                }

                //RN-U2 y Alternativa 1.2: La contraseña debe tener al menos ocho caracteres, una mayúscula y un número.
                if (!CommonUtils.isPasswordValid(password)) {
                    listener.onPasswordFormatError();
                    return;
                }

                //Alternativa 1.3 del caso de uso que indica que o bien el password no es correcto o el usuario
                //No se da información al usuario de qué campo es el erróneo
                if (!UserRepository.getInstance().validateCredentials(user, password)) {
                    listener.onAuthenticationError();
                    return;
                }
                //Secuencia normal
                listener.onSuccess();

            }

        }, 2000);
    }
}
