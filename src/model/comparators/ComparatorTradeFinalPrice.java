package model.comparators;

import model.*;

import java.util.*;

public class ComparatorTradeFinalPrice implements Comparator<Trade> {
    @Override
    public int compare(Trade t1, Trade t2) {
        return (int) (t1.getFinalPrice()*100-t2.getFinalPrice()*100);
    }
}
