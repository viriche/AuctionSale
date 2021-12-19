package model.comparators;

import model.*;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class ComparatorMemberTimeOut implements Comparator<Member> {
    @Override
    public int compare(Member m1, Member m2) {
        return (int) Duration.between(m1.getTimeOut(), m2.getTimeOut()).get(ChronoUnit.SECONDS);
    }
}
