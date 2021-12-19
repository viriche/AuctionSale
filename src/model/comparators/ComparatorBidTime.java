package model.comparators;

import model.*;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class ComparatorBidTime implements Comparator<Bid> {
    @Override
    public int compare(Bid b1, Bid b2) {
        return (int) Duration.between(b1.getTi(), b2.getTi()).get(ChronoUnit.SECONDS);
    }
}
