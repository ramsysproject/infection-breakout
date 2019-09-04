package com.emramirez.cellinfection.service;

import com.emramirez.cellinfection.domain.Cell;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The purpose of this class is to calculate how a infection would spread starting in a given cell.
 */
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

    /**
     * This recursive method gets an infected cell as input, and applies the recursion to its neighbors in order
     * identify all infected cells. At the end we will end up with the cells which were infected in the breakdown.
     *
     * @param input the infected cell
     * @return a list of infected cells as a result of the infection propagation
     */
    public Set<Cell> getInfection(Cell input) {
        log.info("Processing cell in row {} and column {}", input.getRowPosition(), input.getColumnPosition());
        if (infectedCells.isEmpty()) {
            infectedCells.add(input);
        }

        Set<Cell> infectedNeighbors = input.getNeighbors().stream()
                .filter(neighbor -> isInfected(input, neighbor))
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
