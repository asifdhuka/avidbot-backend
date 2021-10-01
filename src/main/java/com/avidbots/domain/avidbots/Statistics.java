package com.avidbots.domain.avidbots;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statistics {
    private String currentPosition;
    private int totalEmptySpaces;
    private int totalWalls;
    private int cleanedSpace;
    private double coveragePercentage;
    private boolean cleaningDone;
    private int newCleanedSpacePerSecond;
    private String doneMessage;
}
