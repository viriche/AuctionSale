package model.comparators;

import model.*;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class ComparatorTradeTime implements Comparator<Trade> {
    @Override
    public int compare(Trade t1, Trade t2) {
        return (int) Duration.between(t1.getTi(), t2.getTi()).get(ChronoUnit.SECONDS);
    }
}
