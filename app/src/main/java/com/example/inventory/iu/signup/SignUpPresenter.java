package com.example.inventory.iu.signup;

public class SignUpPresenter implements SignUpContract.Presenter, SignUpInteractorImpl.SignupInteractor {

    private SignUpContract.View view;
    private SignUpInteractorImpl interactor;

    public SignUpPresenter(SignUpContract.View view){
        this.view = view;
        this.interactor = new SignUpInteractorImpl(this);
    }

    /**
     * Este método deriva del contrato SignUpContract.Presenter
     * @param user
     * @param password
     * @param confirmPassword
     * @param email
     */

    @Override
    public void addUser(String user, String password, String confirmPassword, String email) {
        view.showProgressDialog();
        interactor.addUser(user, password, confirmPassword, email);
    }

    @Override
    public void onPasswordNotEqualError() {
        view.hideProgressDialog();
        view.setPasswordNotEqualError();
    }

    @Override
    public void onUserExistsError() {
        view.hideProgressDialog();
        view.setUserExistsError();
    }

    @Override
    public void onUserEmptyError() {
        view.hideProgressDialog();
        view.setUserEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgressDialog();
        view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordFormatError() {
        view.hideProgressDialog();
        view.setPasswordFormatError();
    }

    @Override
    public void onEmailFormatError() {
        view.hideProgressDialog();
        view.setEmailFormatError();
    }

    @Override
    public void onEmailEmptyError() {
        view.hideProgressDialog();
        view.setEmailEmptyError();
    }

    @Override
    public void onSuccess() {
        view.hideProgressDialog();
        view.onSuccess();
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }
}
