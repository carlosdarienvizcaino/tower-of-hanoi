package com.towerofhanoi.demo;

import java.util.List;
import java.util.ArrayList;

public class TowerMove {
    private int moveCount;
    private List<Integer> sourceTower;
    private List<Integer> auxiliaryTower;
    private List<Integer> destinationTower;

    public TowerMove() {
    }

    public TowerMove(TowerMove towerMove) {
        this.moveCount = towerMove.moveCount;
        this.sourceTower = new ArrayList<>(towerMove.sourceTower);
        this.auxiliaryTower = new ArrayList<>(towerMove.auxiliaryTower);
        this.destinationTower = new ArrayList<>(towerMove.destinationTower);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public List<Integer> getSourceTower() {
        return this.sourceTower;
    }

    public void setSourceTower(List<Integer> sourceTower) {
        this.sourceTower = sourceTower;
    }

    public List<Integer> getAuxiliaryTower() {
        return this.auxiliaryTower;
    }

    public void setAuxiliaryTower(List<Integer> auxiliaryTower) {
        this.auxiliaryTower = auxiliaryTower;
    }

    public List<Integer> getDestinationTower() {
        return this.destinationTower;
    }

    public void setDestinationTower(List<Integer> destinationTower) {
        this.destinationTower = destinationTower;
    }

    public TowerMove createCopy() {
        return new TowerMove(this);
    }
}
