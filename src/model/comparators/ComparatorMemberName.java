package model.comparators;

import model.*;

import java.util.*;

public class ComparatorMemberName implements Comparator<Member> {
    @Override
    public int compare(Member m1, Member m2) {
        return m1.getName().charAt(0)-m2.getName().charAt(0);
    }
}
