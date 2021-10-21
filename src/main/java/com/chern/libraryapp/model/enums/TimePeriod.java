package com.chern.libraryapp.model.enums;

import java.util.ArrayList;
import java.util.List;

public final class TimePeriod {
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int SIX = 6;
    public static final int TWELVE = 12;

    public static List<Integer> getAll(){
        List<Integer> of = new ArrayList<>(List.of(ONE,
                TWO,
                THREE,
                SIX,
                TWELVE));
        return of;

    }
}
