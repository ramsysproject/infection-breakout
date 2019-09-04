package com.emramirez.cellinfection.service;

import com.emramirez.cellinfection.domain.Cell;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to set the neighboring cells in a given cell matrix
 */
@Service
@Slf4j
public class GridService {

    public void setNeighbors(Cell[][] cellMatrix) {
        for (int i = 0; i < cellMatrix.length; i++) {
            for (int j = 0; j < cellMatrix[0].length; j++) {
                List<Cell> neighbors = new ArrayList<>();
                Cell cell = cellMatrix[i][j];
                findNorthNeighbor(cell, cellMatrix, neighbors);
                findSouthNeighbor(cell, cellMatrix, neighbors);
                findWestNeighbor(cell, cellMatrix, neighbors);
                findEastNeighbor(cell, cellMatrix, neighbors);
                cell.setNeighbors(neighbors);
            }
        }
    }

    private void findEastNeighbor(Cell cell, Cell[][] cellMatrix, List<Cell> neighbors) {
        if (cell.getColumn() != (cellMatrix[cell.getRow()].length - 1)) {
            neighbors.add(cellMatrix[cell.getRow()][cell.getColumn() + 1]);
        }
    }

    private void findWestNeighbor(Cell cell, Cell[][] cellMatrix, List<Cell> neighbors) {
        if (cell.getColumn() != 0) {
            neighbors.add(cellMatrix[cell.getRow()][cell.getColumn() - 1]);
        }
    }

    private void findSouthNeighbor(Cell cell, Cell[][] cellMatrix, List<Cell> neighbors) {
        if (cell.getRow() != (cellMatrix.length - 1)) {
            neighbors.add(cellMatrix[cell.getRow() + 1][cell.getColumn()]);
        }
    }

    private void findNorthNeighbor(Cell cell, Cell[][] cellMatrix, List<Cell> neighbors) {
        if (cell.getRow() != 0) {
            neighbors.add(cellMatrix[cell.getRow() - 1][cell.getColumn()]);
        }
    }
}
