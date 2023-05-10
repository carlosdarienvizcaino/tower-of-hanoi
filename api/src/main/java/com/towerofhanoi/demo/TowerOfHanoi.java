package com.towerofhanoi.demo;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class TowerOfHanoi {

    public static List<TowerMove> getTowerOfHanoiMoves(int numberOfDisk) {

        if (numberOfDisk <= 0) {
            return new ArrayList<>();
        }

        List<TowerMove> towerMoves = new ArrayList<>();
        TowerMove towerMove = resetTowers(numberOfDisk);
        towerMoves.add(towerMove);
        solveTowerOfHanoi(numberOfDisk, towerMoves, "A", "B", "C");
        return towerMoves;

    }

    private static void saveTowerOfHanoiMove(List<TowerMove> towerMoves, String sourceTower, String destinationTower) {

        if (towerMoves.isEmpty()) {
            return;
        }

        TowerMove towerMove = new TowerMove(towerMoves.get(towerMoves.size() - 1));
        towerMove.setMoveCount(towerMoves.size());

        if (sourceTower == "A" && destinationTower == "B") {
            towerMove.getAuxiliaryTower().add(0, towerMove.getSourceTower().remove(0));
        }

        if (sourceTower == "A" && destinationTower == "C") {
            towerMove.getDestinationTower().add(0, towerMove.getSourceTower().remove(0));
        }

        if (sourceTower == "B" && destinationTower == "A") {
            towerMove.getSourceTower().add(0, towerMove.getAuxiliaryTower().remove(0));
        }

        if (sourceTower == "B" && destinationTower == "C") {
            towerMove.getDestinationTower().add(0, towerMove.getAuxiliaryTower().remove(0));
        }

        if (sourceTower == "C" && destinationTower == "A") {
            towerMove.getSourceTower().add(0, towerMove.getDestinationTower().remove(0));
        }

        if (sourceTower == "C" && destinationTower == "B") {
            towerMove.getAuxiliaryTower().add(0, towerMove.getDestinationTower().remove(0));
        }

        towerMoves.add(towerMove);
    }

    private static void solveTowerOfHanoi(int numberOfDisk, List<TowerMove> towerMoves, String src, String aux,
            String dest) {

        if (numberOfDisk == 1) {
            saveTowerOfHanoiMove(towerMoves, src, dest);
            return;
        }

        solveTowerOfHanoi(numberOfDisk - 1, towerMoves, src, dest, aux);
        saveTowerOfHanoiMove(towerMoves, src, dest);
        solveTowerOfHanoi(numberOfDisk - 1, towerMoves, aux, src, dest);
    }

    public static TowerMove resetTowers(int numberOfDisk) {
        // Initialize TowerMove instance
        TowerMove towerMove = new TowerMove();

        // Set values for moveCount
        towerMove.setMoveCount(0);

        // Set values for sourceTower
        List<Integer> sourceTower = generateListOfSize(numberOfDisk);
        towerMove.setSourceTower(sourceTower);

        // Set values for auxiliaryTower
        List<Integer> auxiliaryTower = new ArrayList<>();
        towerMove.setAuxiliaryTower(auxiliaryTower);

        // Set values for destinationTower
        List<Integer> destinationTower = new ArrayList<>();
        towerMove.setDestinationTower(destinationTower);

        return towerMove;
    }

    public static List<Integer> generateListOfSize(int n) {
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        return list;
    }

    public static int getNumberOfMoves(int numberOfDisks) {
        return (int) Math.pow(2, numberOfDisks) - 1;
    }
}