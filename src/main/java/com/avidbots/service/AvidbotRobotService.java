package com.avidbots.service;

import com.avidbots.domain.avidbots.Statistics;
import org.springframework.stereotype.Service;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

@Service
public class AvidbotRobotService {
    private char[][] map;
    private char emptySpaceMapping = ' ';
    private char wallMapping = '#';
    private char newLineMapping = '\n';
    private int totalEmptySpace = 0;
    private int totalWalls = 0;
    private int cleanedSpace = 0;
    private double coveragePercentage = 0;
    private boolean cleaningDone = false;
    private long storedTime = System.currentTimeMillis();
    private int newCleanedSpacePerSecond = 0;
    private String currentPosition = "0,0";
    private String doneMessage = "I am done with cleaning, its time to go back to home";

    public void loadMap(String inputMap) throws InterruptedException {
        resetStats();
        String mapArray[] = inputMap.split("\n");
        int nr = mapArray.length;
        int nc = mapArray[0].length();
        map = new char[nr][nc];
        for (int i=0; i < nr; i++) {
            char[] chars = mapArray[i].toCharArray();
            for (int j=0; j < nc; j++) {
                map[i][j] = chars[j];
                if (chars[j] == emptySpaceMapping) {
                    totalEmptySpace++;
                } else if(chars[j] == wallMapping) {
                    totalWalls++;
                }
            }
        }
        startCleaning();
        cleaningDone = true;
    }
    public Statistics getStats() {
        Statistics statistics = new Statistics(currentPosition, totalEmptySpace, totalWalls, cleanedSpace,
                coveragePercentage, cleaningDone, newCleanedSpacePerSecond, doneMessage);
        return  statistics;
    }

    public void resetStats() {
        currentPosition = "0,0";
        totalEmptySpace = 0;
        totalWalls = 0;
        cleanedSpace = 0;
        coveragePercentage = 0;
        cleaningDone = false;
        storedTime = System.currentTimeMillis();
        newCleanedSpacePerSecond = 0;
    }

    private void startCleaning() throws InterruptedException {
        int nr = map.length;
        int nc = map[0].length;
        boolean[][] visited = new boolean[nr][nc];
        for(int i=0; i<nr; i++) {
            for(int j=0; j<nc; j++) {
                if (totalEmptySpace != cleanedSpace && !visited[i][j]) {
                    updatePerSecondStats();
                    dfs(map, nr, nc, visited, i+","+j);
                }
            }
        }
    }
    private void updatePerSecondStats() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - storedTime >= 1000) {
            newCleanedSpacePerSecond = 0;
            storedTime = currentTime;
        }
    }
    private void dfs(char[][] input, int nr, int nc, boolean[][] visited, String start) throws InterruptedException {
        Stack<String> stack = new Stack<>();
        stack.push(start);
        while(stack.empty() == false) {
            updatePerSecondStats();
            TimeUnit.MILLISECONDS.sleep(200);
            String x = stack.pop();
            int row = Integer.parseInt(x.split(",")[0]);
            int col = Integer.parseInt(x.split(",")[1]);
            if(row<0 || col<0 || row>= nr || col>= nc || visited[row][col] || input[row][col] != '#')
                continue;
            visited[row][col]=true;
            cleanedSpace++;
            newCleanedSpacePerSecond++;
            coveragePercentage = (cleanedSpace*100)/totalEmptySpace;
            currentPosition = row+","+col;
//            System.out.println("Robot is cleaning cell: " + row+","+col);
//            System.out.println("Total cleaned Space: " + cleanedSpace);
//            System.out.println("coveragePercentage: " + coveragePercentage);
            stack.push(row + "," + (col-1)); //left
            stack.push(row + "," + (col+1)); //right
            stack.push((row-1) + "," + col); //up
            stack.push((row+1) + "," + col); //down
        }
    }
}
