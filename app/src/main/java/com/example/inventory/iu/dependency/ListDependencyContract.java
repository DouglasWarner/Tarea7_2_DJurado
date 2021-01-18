package com.example.inventory.iu.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.iu.base.BaseListView;
import com.example.inventory.iu.base.BasePresenter;
import com.example.inventory.iu.base.BaseView;

public interface ListDependencyContract {

    interface View extends BaseListView<Dependency> {
        // Activa la parte de la vista que indica que no hay datos
        void setNoData();

        //Método que muestra una barra de progreso en la interfaz
        //mientras se realiza una acción en el interactor
        void showProgress();

        //Método que oculta la barra de progreso en la interfaz
        void hideProgress();

        void onsuccessDeleted();
    }

    interface Presenter extends BasePresenter{
        void load();

        void delete(Dependency deleted);
    }
}
