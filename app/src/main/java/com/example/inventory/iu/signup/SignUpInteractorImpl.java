package com.example.inventory.iu.signup;

import android.os.Handler;
import android.text.TextUtils;

import com.example.inventory.data.repository.UserRepository;
import com.example.inventory.iu.utils.CommonUtils;

public class SignUpInteractorImpl {

    interface SignupInteractor{
        void onPasswordNotEqualError();
        void onUserExistsError();
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onPasswordFormatError();
        void onEmailFormatError();
        void onEmailEmptyError();
        void onSuccess();
    }

    private SignupInteractor callback;

    public SignUpInteractorImpl(SignupInteractor callback) {
        this.callback = callback;
    }

    public void addUser(final String user, final String password, final String confirmPassword, final String email){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(user)) {
                    callback.onUserEmptyError();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    callback.onPasswordEmptyError();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    callback.onEmailEmptyError();
                    return;
                }

                if (TextUtils.isEmpty(confirmPassword)){
                    callback.onPasswordEmptyError();
                    return;
                }

                if (!CommonUtils.isEmailValid(email)){
                    callback.onEmailFormatError();
                    return;
                }

                if (!password.equals(confirmPassword)){
                    callback.onPasswordNotEqualError();
                    return;
                }

                if (!CommonUtils.isPasswordValid(password)) {
                    callback.onPasswordFormatError();
                    return;
                }
                UserRepository repository = UserRepository.getInstance();

                if (repository.userExists(user)){
                    callback.onUserExistsError();
                    return;
                }

                repository.add( user, password, email);

                callback.onSuccess();


            }
        },2000);
    }
}
