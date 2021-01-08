package com.example.inventory.data.model;

import java.util.Comparator;

public class NameSort implements Comparator<Dependency> {
    @Override
    public int compare(Dependency o1, Dependency o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
