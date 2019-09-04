package com.emramirez.cellinfection.domain;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Cell {

    private int rowPosition;
    private int columnPosition;
    private int resistance;
    private List<Cell> neighbors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return rowPosition == cell.rowPosition &&
                columnPosition == cell.columnPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowPosition, columnPosition);
    }
}
