package model.comparators;

import model.*;

import java.util.*;

public class ComparatorMemberBalance implements Comparator<Member> {
    @Override
    public int compare(Member m1, Member m2) {
        return (int) (m1.getPiggyBank().getBalance()*100-m2.getPiggyBank().getBalance()*100);
    }
}
