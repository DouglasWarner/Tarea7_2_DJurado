package com.example.inventory.iu.login;

import com.example.inventory.iu.base.BasePresenter;
import com.example.inventory.iu.user.UserView;

public interface LoginContract {

    interface View extends UserView {

        //Método que muestra una barra de progreso en la interfaz
        //mientras se realiza una acción en el interactor
        void showProgress();

        //Método que oculta la barra de progreso en la interfaz
        void hideProgress();

        //Método que muestra un mensaje de error la usuario indicando que la autenticación
        //no ha sido correcta
        void setAuthenticationError();

    }

    interface Presenter extends BasePresenter {

        //Valida el usuario de la pantalla Login
        void validateCredentials(String user, String password);

    }
}
