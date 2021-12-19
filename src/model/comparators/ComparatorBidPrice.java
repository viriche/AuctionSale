package model.comparators;

import model.*;

import java.util.*;

public class ComparatorBidPrice implements Comparator<Bid> {
    @Override
    public int compare(Bid b1, Bid b2) {
        return (int)(b1.getPrice()*100-b2.getPrice()*100);
    }

}
