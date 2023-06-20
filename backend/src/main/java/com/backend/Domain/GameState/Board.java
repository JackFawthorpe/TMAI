package com.backend.Domain.GameState;

import java.util.ArrayList;
import java.util.List;

/**
 * Data class for the state of the board
 */
public class Board {
    List<List<Cell>> cells;

    public Board() {
        cells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ArrayList<Cell> temp = new ArrayList<>();
            for (int j = 0; j < 9 - Math.abs(4 - i); j++) {
                temp.add(new Cell());
            }
            cells.add(temp);
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

}
