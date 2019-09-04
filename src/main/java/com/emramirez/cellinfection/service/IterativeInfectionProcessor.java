package com.emramirez.cellinfection.service;

import com.emramirez.cellinfection.domain.Cell;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
            Cell current = cellStack.pop();
            if (visitedCells.contains(current)) {
                continue;
            }
            visitedCells.add(current);

            log.info("Processing cell in row {} and column {}", current.getRowPosition(), current.getColumnPosition());
            Set<Cell> infectedNeighbors = current.getNeighbors().stream()
                    .filter(neighbor -> isInfected(current, neighbor))
                    .filter(neighbor -> !infectedCells.contains(neighbor))
                    .collect(Collectors.toSet());

            infectedCells.addAll(infectedNeighbors);
            cellStack.addAll(infectedNeighbors);
        }

        return infectedCells;
    }

    private boolean isInfected(Cell infectedCell, Cell evaluatedCell) {
        return evaluatedCell.getResistance() <= infectedCell.getResistance();
    }
}
