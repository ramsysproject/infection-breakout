package com.emramirez.cellinfection.service;

import com.emramirez.cellinfection.TestUtils;
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
public class RecursiveInfectionProcessorTest {

    public static final int ROWS = 6;
    public static final int COLUMNS = 10;
    public static final int CHALLENGE_INFECTED_CELLS_QUANTITY = 12;

    @Autowired
    RecursiveInfectionProcessor service;

    @Test
    public void getInfection_challengeMatrixGiven_12infectedCellsExpected() {
        // arrange
        int[][] resistances = TestUtils.buildChallengeResistancesMatrix();
        Cell[][] cellMatrix = TestUtils.buildChallengeCellsMatrix(resistances, ROWS, COLUMNS);
        service.initialize(cellMatrix);

        // act
        Set<Cell> result = service.getInfection(cellMatrix[2][3]);

        // assert
        assertEquals("Wrong infected cells number!", CHALLENGE_INFECTED_CELLS_QUANTITY, result.size());
    }
}