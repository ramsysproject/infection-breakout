package com.emramirez.cellinfection;

import com.emramirez.cellinfection.domain.Cell;

public class TestUtils {

    public static int[][] buildChallengeResistancesMatrix() {
        return new int[][]{
                {8, 7, 4, 1, 2, 8, 3, 6, 9, 8},
                {9, 3, 4, 2, 7, 6, 8, 3, 4, 4},
                {6, 7, 2, 9, 6, 4, 9, 0, 4, 6},
                {7, 9, 7, 5, 2, 9, 8, 7, 7, 0},
                {2, 6, 2, 4, 3, 5, 4, 0, 9, 7},
                {1, 2, 5, 0, 6, 2, 8, 5, 7, 3},
        };
    }

    public static Cell[][] buildChallengeCellsMatrix(int[][] resistances, int rows, int columns) {
        Cell[][] cellMatrix = new Cell[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell cell = buildCell(resistances[row][column], row, column);
                cellMatrix[row][column] = cell;
            }
        }
        return cellMatrix;
    }

    private static Cell buildCell(int resistance, int row, int column) {
        return Cell.builder()
                .rowPosition(row)
                .columnPosition(column)
                .resistance(resistance).build();
    }
}
