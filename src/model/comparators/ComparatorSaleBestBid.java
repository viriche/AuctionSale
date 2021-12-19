package model.comparators;

import model.*;

import java.util.*;

public class ComparatorSaleBestBid implements Comparator<Sale> {
    @Override
    public int compare(Sale s1, Sale s2) {
        double p1 = s1.getAskPrice();
        double p2 = s2.getAskPrice();
        if(s1.getBestBid()!=null) p1 = s1.getBestBid().getPrice();
        if(s2.getBestBid()!=null) p2 = s2.getBestBid().getPrice();
        return (int) (p1*100-p2*100);
    }
}
