package com.emramirez.cellinfection.service;

import com.emramirez.cellinfection.domain.Cell;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecursiveInfectionProcessor implements InfectionSpreadProcessor {

    private Set<Cell> infectedCells;
    private final GridService gridService;

    public void initialize(Cell[][] cellMatrix) {
        infectedCells = new HashSet<>();
        gridService.setNeighbors(cellMatrix);
    }

    @Override
    public Set<Cell> getInfection(Cell startingCell) {
        log.info("Processing cell in row {} and column {}", startingCell.getRowPosition(), startingCell.getColumnPosition());
        if (infectedCells.isEmpty()) {
            infectedCells.add(startingCell);
        }

        Set<Cell> infectedNeighbors = startingCell.getNeighbors().stream()
                .filter(neighbor -> isInfected(startingCell, neighbor))
                .filter(neighbor -> !infectedCells.contains(neighbor))
                .collect(Collectors.toSet());

        infectedCells.addAll(infectedNeighbors);
        infectedNeighbors.forEach(this::getInfection);

        return infectedCells;
    }

    private boolean isInfected(Cell infectedCell, Cell evaluatedCell) {
        return evaluatedCell.getResistance() <= infectedCell.getResistance();
    }
}
