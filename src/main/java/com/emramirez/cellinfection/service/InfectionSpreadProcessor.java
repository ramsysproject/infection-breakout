package com.emramirez.cellinfection.service;

import com.emramirez.cellinfection.domain.Cell;

import java.util.Set;

public interface InfectionSpreadProcessor {

    Set<Cell> getInfection(Cell input);
}
