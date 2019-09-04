package com.emramirez.cellinfection.service;

import com.emramirez.cellinfection.domain.Cell;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IterativeInfectionProcessorTest {

	public static final int ROWS = 6;
	public static final int COLUMNS = 10;
    public static final int EXAMPLE_INFECTED_CELLS_QUANTITY = 12;

    @Autowired
    IterativeInfectionProcessor service;

	@Test
	public void getInfection_exampleMatrixGiven_12infectedCellsExpected() {
		// arrange
        int[][] resistances = buildResistancesMatrix();
        Cell[][] cellMatrix = buildCellsMatrix(resistances);
        service.initialize(cellMatrix);

		// act
        Set<Cell> result = service.getInfection(cellMatrix[2][3]);

		// assert
        result.stream().forEach(cell -> log.info("Infected cell found in row {} and column {} with resistance {}",
                cell.getRowPosition() + 1, cell.getColumnPosition() + 1, cell.getResistance()));
        assertEquals("Wrong infected cells number!", EXAMPLE_INFECTED_CELLS_QUANTITY, result.size());
	}

    private int[][] buildResistancesMatrix() {
        return new int[][] {
                    {8, 7, 4, 1, 2, 8, 3, 6, 9, 8},
                    {9, 3, 4, 2, 7, 6, 8, 3, 4, 4},
                    {6, 7, 2, 9, 6, 4, 9, 0, 4, 6},
                    {7, 9, 7, 5, 2, 9, 8, 7, 7, 0},
                    {2, 6, 2, 4, 3, 5, 4, 0, 9, 7},
                    {1, 2, 5, 0, 6, 2, 8, 5, 7, 3},
            };
    }

    private Cell[][] buildCellsMatrix(int[][] resistances) {
        Cell[][] cellMatrix = new Cell[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                Cell cell = buildCell(resistances[row][column], row, column);
                cellMatrix[row][column] = cell;
            }
        }
        return cellMatrix;
    }

    private Cell buildCell(int resistance, int row, int column) {
        Cell cell = new Cell();
        cell.setRowPosition(row);
        cell.setColumnPosition(column);
        cell.setResistance(resistance);
        return cell;
    }
}