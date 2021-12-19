package model.comparators;

import model.*;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class ComparatorSaleTimeClosed implements Comparator<Sale> {
    @Override
    public int compare(Sale s1, Sale s2) {
        return (int) Duration.between(s1.getTo(), s2.getTo()).get(ChronoUnit.SECONDS);
    }
}
