package model.comparators;

import model.*;

import java.util.*;

public class ComparatorSaleDuration implements Comparator<Sale> {
    @Override
    public int compare(Sale s1, Sale s2) {
        return s1.getDuration()- s2.getDuration();
    }
}
