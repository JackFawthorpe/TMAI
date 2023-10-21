package com.backend.Domain.GameState.Board;

/**
 * Data class for a cell on a board
 */
public class Cell {

    CellType type;

    public Cell() {
        type = CellType.randomCellType();
    }

    public CellType getType() {
        return type;
    }
}
