package com.example.inventory.data.repository;

import com.example.inventory.data.model.Dependency;

import java.util.ArrayList;
import java.util.List;

public class DependencyRepository {
    private List<Dependency> list;
    private static DependencyRepository repository;

    static {
        repository = new DependencyRepository();
    }

    private DependencyRepository() {
        this.list = new ArrayList<>();
        initialice();
    }

    private void initialice()
    {
        list.add(new Dependency("Producto 1","PD1","Descripcion de producto 1", "Imagen de producto 1"));
        list.add(new Dependency("Seccion 1","SC1","Decripcion de seccion 1", "Imagen de seccion 1"));
        list.add(new Dependency("Producto 2","PD2","Decripcion de producto 2", "Imagen de producto 2"));
        list.add(new Dependency("Armario 1","AR1","Descripcion de armario 1", "Imagen de armario 1"));
        list.add(new Dependency("Monitor 1","MT1","Descripcion de monitor 1", "Imagen de monitor 1"));
        list.add(new Dependency("Teclado 1","TC1","Descripcion de teclado 1", "Imagen de teclado 1"));
        list.add(new Dependency("Teclado 2","TC2","Descripcion de teclado 2", "Imagen de teclado 2"));
    }

    public static DependencyRepository getInstance()
    {
        return repository;
    }

    public List<Dependency> getList()
    {
        return list;
    }
}
