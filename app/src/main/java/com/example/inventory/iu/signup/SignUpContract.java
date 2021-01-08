package com.example.inventory.iu.signup;

import com.example.inventory.iu.base.BasePresenter;
import com.example.inventory.iu.user.UserView;

public interface SignUpContract {

    interface View extends UserView {
        // Metodo que viene de la Alternativa 1.3. Contraseñas diferente
        void setPasswordNotEqualError();

        // Metodo que viene de la Alternativa 1.4. Usuario ya existe
        void setUserExistsError();

        // Metodo que viene de RN que el email tiene que tener un formato correcto
        void setEmailFormatError();

        void showProgressDialog();
        void hideProgressDialog();
        void setEmailEmptyError();
        void setUserEmptyError();
        void setPasswordFormatError();
        void setPasswordEmptyError();
    }

    interface Presenter extends BasePresenter {
        void addUser(String user , String password, String confirmPassword, String email);

    }
}
