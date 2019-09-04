package com.emramirez.cellinfection.service;

import com.emramirez.cellinfection.domain.Cell;

import java.util.Set;

/**
 * Sets the contract for any infection spread processor.
 */
public interface InfectionSpreadProcessor {

    /**
     * This method gets an infected cell as input and calculates how this infection will spread in a grid system.
     * To accomplish this, it analyzes cells neighbors to verify if the infection will spread or not.
     *
     * @param startingCell the initial infected cell
     * @return a list of infected cells as a result of the infection propagation
     */
    Set<Cell> getInfection(Cell startingCell);

    default boolean isInfected(Cell infectedCell, Cell evaluatedCell) {
        return evaluatedCell.getResistance() <= infectedCell.getResistance();
    }
}
