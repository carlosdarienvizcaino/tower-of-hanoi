package com.towerofhanoi.demo;

import com.towerofhanoi.demo.TowerOfHanoi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;

@RestController
@RequestMapping("/api")
public class APIController {

    @GetMapping("/moves/all")
    public TowerMoveResponse getAllMoves(@RequestParam("diskNumber") int diskNumber) {
        List<TowerMove> towerMoves = TowerOfHanoi.getTowerOfHanoiMoves(diskNumber);
        TowerMoveResponse towerMoveResponse = new TowerMoveResponse();
        towerMoveResponse.setTowerMoves(towerMoves);
        return towerMoveResponse;
    }

    // TODO: Add pagination

}
