package model.comparators;

import model.*;

import java.util.*;

public class ComparatorMemberPotentialB implements Comparator<Member> {
    @Override
    public int compare(Member m1, Member m2) {
        return (int) (m1.getPiggyBank().getPotentialB()*100-m2.getPiggyBank().getPotentialB()*100);
    }
}
