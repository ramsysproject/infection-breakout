package com.emramirez.cellinfection.service;

import com.emramirez.cellinfection.domain.Cell;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Service
@Slf4j
@RequiredArgsConstructor
public class IterativeInfectionProcessor implements InfectionSpreadProcessor {

    private Set<Cell> infectedCells;
    private final GridService gridService;

    public void initialize(Cell[][] cellMatrix) {
        infectedCells = new HashSet<>();
        gridService.setNeighbors(cellMatrix);
    }

    @Override
    public Set<Cell> getInfection(Cell startingCell) {
        Stack<Cell> cellStack = new Stack();
        cellStack.add(startingCell);
        infectedCells.add(startingCell);
        List<Cell> visitedCells = new ArrayList<>();

        while (!cellStack.isEmpty()) {
            Cell currentCell = cellStack.pop();
            if (visitedCells.contains(currentCell)) {
                continue;
            }
            visitedCells.add(currentCell);

            log.info("Processing cell in row {} and column {}", currentCell.getRow(), currentCell.getColumn());
            Set<Cell> infectedNeighbors = currentCell.getNeighbors().stream()
                    .filter(neighbor -> isInfected(currentCell, neighbor) && !infectedCells.contains(neighbor))
                    .collect(toSet());
            infectedCells.addAll(infectedNeighbors);
            cellStack.addAll(infectedNeighbors);
        }

        return infectedCells;
    }
}
