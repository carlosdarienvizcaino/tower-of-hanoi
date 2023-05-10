package com.towerofhanoi.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TowerMoveResponse {

    private List<TowerMove> towerMoves;

    public TowerMoveResponse() {
        towerMoves = new ArrayList<>();
    }

    public List<TowerMove> getTowerMoves() {
        return towerMoves;
    }

    public void setTowerMoves(List<TowerMove> towerMoves) {
        this.towerMoves = towerMoves;
    }

}
