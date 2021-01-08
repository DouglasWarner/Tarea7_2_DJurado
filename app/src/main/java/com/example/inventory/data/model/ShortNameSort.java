package com.example.inventory.data.model;

import java.util.Comparator;

public class ShortNameSort implements Comparator<Dependency> {
    @Override
    public int compare(Dependency o1, Dependency o2) {
        return o1.getShortName().compareTo(o2.getShortName());
    }
}
