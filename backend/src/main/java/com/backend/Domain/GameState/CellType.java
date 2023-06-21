package com.backend.Domain.GameState;

import java.util.Random;

public enum CellType {
    EMPTY,
    CITY,
    SPECIAL,
    OCEAN,
    GREENERY;

    private static final Random PRNG = new Random();

    public static CellType randomCellType() {
        CellType[] directions = values();
        return directions[PRNG.nextInt(directions.length)];
    }
}
