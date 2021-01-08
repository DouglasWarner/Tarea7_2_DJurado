package com.example.inventory.iu.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;

import java.util.List;

public class ListDependencyInteractorImpl {

    private ListDependencyInteractor callback;

    /**
     * Interfaz que debe implementar el presentador que quiera utilizar el interactor
     */
    interface ListDependencyInteractor {
        void OnNoData();
        void OnSuccess(List<Dependency> list);
    }

    public ListDependencyInteractorImpl(ListDependencyInteractor callback) {
        this.callback= callback;
    }

    /**
     * Metodo que solicita el conjunto de dependencias al Repository/Origen de datos
     */
    public void load() {
        List<Dependency> list = DependencyRepository.getInstance().getList();
        // 1. No hay datos en el listado
        if (list.isEmpty())
            callback.OnNoData();
        else
            callback.OnSuccess(list);
    }
}
