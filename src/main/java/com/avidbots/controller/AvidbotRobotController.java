package com.avidbots.controller;

import com.avidbots.domain.avidbots.InputData;
import com.avidbots.domain.avidbots.Statistics;
import com.avidbots.service.AvidbotRobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/avidbotsRobot")
public class AvidbotRobotController {
    private final AvidbotRobotService avidbotRobotService;
    @Autowired
    public AvidbotRobotController(AvidbotRobotService avidbotRobotService) {
        this.avidbotRobotService = avidbotRobotService;
    }
    @PostMapping(value = "/process")
    public ResponseEntity<String> loadMap(@RequestBody InputData inputData) throws InterruptedException {
        avidbotRobotService.loadMap(inputData.getInputMap());
        return ResponseEntity.ok("Done processing!");
    }

    @GetMapping(value = "/statistics")
    public ResponseEntity<Statistics> loadMap() {
        Statistics statistics = avidbotRobotService.getStats();
        return ResponseEntity.ok(statistics);
    }
}
