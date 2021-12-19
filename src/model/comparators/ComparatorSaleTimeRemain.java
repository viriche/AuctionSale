package model.comparators;

import model.*;

import java.time.*;
import java.util.*;

public class ComparatorSaleTimeRemain implements Comparator<Sale> {
    private final Instant i;

    public ComparatorSaleTimeRemain(Instant i) {
        this.i = i;
    }

    @Override
    public int compare(Sale s1, Sale s2) {
        return Server.remainingTime(s1, this.i)-Server.remainingTime(s2, this.i);
    }
}
