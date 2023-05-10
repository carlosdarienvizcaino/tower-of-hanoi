package com.towerofhanoi.demo;

import java.util.List;
import com.towerofhanoi.demo.TowerMove;
import com.towerofhanoi.demo.TowerOfHanoi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void souldCreateAllMovesForHanoiTowersWithoutDisk() {

		// Given
		int numberOfDisks = 0;

		// When
		List<TowerMove> towerMoves = TowerOfHanoi.getTowerOfHanoiMoves(numberOfDisks);

		// Assert
		Assertions.assertEquals(towerMoves.size(), numberOfDisks);
	}

	@Test
	void souldCreateAllMovesForHanoiTowersWithSize1() {

		// Given
		int numberOfDisks = 1;

		// When
		List<TowerMove> towerMoves = TowerOfHanoi.getTowerOfHanoiMoves(numberOfDisks);
		TowerMove restTowers = towerMoves.get(0);
		TowerMove lastMove = towerMoves.get(TowerOfHanoi.getNumberOfMoves(numberOfDisks));

		// Assert
		Assertions.assertEquals(towerMoves.size(), TowerOfHanoi.getNumberOfMoves(numberOfDisks) + 1);
		Assertions.assertEquals(lastMove.getMoveCount(), TowerOfHanoi.getNumberOfMoves(numberOfDisks));
		Assertions.assertEquals(lastMove.getDestinationTower().size(), numberOfDisks);
		Assertions.assertArrayEquals(restTowers.getSourceTower().toArray(), lastMove.getDestinationTower().toArray());
	}

	@Test
	void souldCreateAllMovesForHanoiTowersWithSize2() {

		// Given
		int numberOfDisks = 2;

		// When
		List<TowerMove> towerMoves = TowerOfHanoi.getTowerOfHanoiMoves(numberOfDisks);
		TowerMove restTowers = towerMoves.get(0);
		TowerMove lastMove = towerMoves.get(TowerOfHanoi.getNumberOfMoves(numberOfDisks));
		TowerMove secondMove = towerMoves.get(2);

		// Assert
		Assertions.assertEquals(towerMoves.size(), TowerOfHanoi.getNumberOfMoves(numberOfDisks) + 1);
		Assertions.assertEquals(lastMove.getMoveCount(), TowerOfHanoi.getNumberOfMoves(numberOfDisks));
		Assertions.assertEquals(secondMove.getDestinationTower().get(0), 2);
		Assertions.assertEquals(lastMove.getDestinationTower().size(), numberOfDisks);
		Assertions.assertArrayEquals(restTowers.getSourceTower().toArray(), lastMove.getDestinationTower().toArray());
	}

	@Test
	void souldCreateAllMovesForHanoiTowersWithSize3() {

		// Given
		int numberOfDisks = 3;

		// When
		List<TowerMove> towerMoves = TowerOfHanoi.getTowerOfHanoiMoves(numberOfDisks);
		TowerMove restTowers = towerMoves.get(0);
		TowerMove lastMove = towerMoves.get(TowerOfHanoi.getNumberOfMoves(numberOfDisks));
		TowerMove secondMove = towerMoves.get(2);

		// Assert
		Assertions.assertEquals(towerMoves.size(), TowerOfHanoi.getNumberOfMoves(numberOfDisks) + 1);
		Assertions.assertEquals(lastMove.getMoveCount(), TowerOfHanoi.getNumberOfMoves(numberOfDisks));
		Assertions.assertEquals(lastMove.getDestinationTower().size(), numberOfDisks);

		Assertions.assertEquals(secondMove.getAuxiliaryTower().get(0), 2);
		Assertions.assertArrayEquals(restTowers.getSourceTower().toArray(), lastMove.getDestinationTower().toArray());
	}

	@Test
	void souldCreateAllMovesForHanoiTowersWithSize4() {

		// Given
		int numberOfDisks = 4;
		List<TowerMove> towerMoves = TowerOfHanoi.getTowerOfHanoiMoves(numberOfDisks);
		TowerMove restTowers = towerMoves.get(0);
		TowerMove lastMove = towerMoves.get(TowerOfHanoi.getNumberOfMoves(numberOfDisks));

		// Assert
		Assertions.assertEquals(towerMoves.size(), TowerOfHanoi.getNumberOfMoves(numberOfDisks) + 1);
		Assertions.assertEquals(lastMove.getMoveCount(), TowerOfHanoi.getNumberOfMoves(numberOfDisks));
		Assertions.assertEquals(lastMove.getDestinationTower().size(), numberOfDisks);
		Assertions.assertArrayEquals(restTowers.getSourceTower().toArray(), lastMove.getDestinationTower().toArray());

	}

	@Test
	void souldResetTowerMoves() {

		// Given
		TowerMove towerMove = TowerOfHanoi.resetTowers(3);

		// Assert
		Assertions.assertEquals(towerMove.getMoveCount(), 0);
		Assertions.assertEquals(towerMove.getSourceTower().size(), 3);

	}

	@Test
	void souldBe7TotalMovesFor3Disks() {

		// Given
		Integer totalNumberOfMoves = TowerOfHanoi.getNumberOfMoves(3);

		// Assert
		Assertions.assertEquals(totalNumberOfMoves, 7);
	}

}
