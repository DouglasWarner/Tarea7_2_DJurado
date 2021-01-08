package com.example.inventory.iu.base;

public interface BasePresenter {
    // Metodo que elimina la referencia a la vista y del interactor en el presenter
    void onDestroy();
}
