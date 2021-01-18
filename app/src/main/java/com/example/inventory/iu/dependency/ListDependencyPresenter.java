package com.example.inventory.iu.dependency;

import com.example.inventory.data.model.Dependency;

import java.util.List;

public class ListDependencyPresenter implements ListDependencyContract.Presenter, ListDependencyInteractorImpl.ListDependencyInteractor {

    private ListDependencyInteractorImpl interactor;
    private ListDependencyContract.View view;

    public ListDependencyPresenter(ListDependencyContract.View view) {
        this.interactor = new ListDependencyInteractorImpl(this);
        this.view = view;
    }

    /**
     * Este metodo viene del contrato con la vista
     */
    @Override
    public void load() {
        view.showProgress();
        interactor.load();
    }

    @Override
    public void delete(Dependency deleted) {
        interactor.delete(deleted);
    }

    /**
     * Este metodo viene de la interfaz de BasePresenter
     */
    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }

    /**
     * Metodo que viene de la interfaz ListDependencyInteractor
     */
    @Override
    public void OnNoData() {
        view.hideProgress();
        view.setNoData();
    }

    /**
     * Metodo que viene de la interfaz ListDependencyInteractor
     */
    @Override
    public void OnSuccess(List<Dependency> list) {
        view.hideProgress();
        view.onSuccess(list);
    }

    @Override
    public void onSuccessDeleted() {
        view.onsuccessDeleted();
    }
}
