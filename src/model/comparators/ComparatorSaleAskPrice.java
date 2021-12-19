package model.comparators;

import model.*;

import java.util.*;

public class ComparatorSaleAskPrice implements Comparator<Sale> {
    @Override
    public int compare(Sale s1, Sale s2) {
        return (int) (s1.getAskPrice()*100-s2.getAskPrice()*100);
    }
}
