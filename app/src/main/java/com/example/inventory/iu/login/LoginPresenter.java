package com.example.inventory.iu.login;

import android.telephony.ClosedSubscriberGroupInfo;

import com.example.inventory.iu.base.BaseView;

/**
 * Clase que controla las reglas de negocio de la clase User
 */
public class LoginPresenter implements LoginContract.Presenter, LoginInteractorImpl.LoginInteractor {

    /* De esto a ...
    private ValidateUserContract.View validateUserView;
    private AddUserContract.View addUserView;
    */
    // ... esto
    private LoginContract.View view;

    private LoginInteractorImpl interactor;

    /**
     * Metodo
     *
     * @param view
     */
    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.interactor = new LoginInteractorImpl(this);
    }

    /**
     * Metodo que añade uin usuaro a la base de datos.
     * Se tiene que gestiona la RN-U5
     *
     * @param user
     * @param password
     * @param email
     */
    public void addUser(String user, String password, String email) {
        // 1. Se llama al método addUser del repositorio.
        // 1.1 Si es correcto
        view.onSuccess();
        // 1.2 Si el usuario es duplicado
//        ((AddUserContract.View)view).userEmailDuplicated();
    }

    public void validateUser(String user, String password) {
        //1. Se llama al método validateUser del repositorio
        // 1.1 Si es correcto
        view.onSuccess();
        // 1.2 Si el email no existe
//        ((ValidateUserContract.View)view).emailNoExist();
        // 1.3 Error en el password
//        ((ValidateUserContract.View)view).passwordError();
    }

    @Override
    public void validateCredentials(String user, String password) {
//        view.onSuccess();
        view.showProgress();
        interactor.validateCredentials(user, password);
    }

    /**
     * Metodo que elimina la instancia a la vista y al interactor
     */
    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    //region Metodos del interacto
    @Override
    public void onUserEmptyError() {
        view.hideProgress();
        view.setUserEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgress();
        view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordFormatError() {
        view.hideProgress();
        view.setPasswordFormatError();
    }

    @Override
    public void onAuthenticationError() {
        view.hideProgress();
        view.setAuthenticationError();
    }

    @Override
    public void onSuccess() {
        view.hideProgress();
        view.onSuccess();
    }
    //endregion
}
