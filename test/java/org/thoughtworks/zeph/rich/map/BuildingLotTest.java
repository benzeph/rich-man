package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BuildingLotTest {

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_level_1_building_lot_1_when_input_is_level_0_building_lot_1_and_level_up() {
		Land buildLotOneTwo = new BuildingLotOneTwo(1, '0');
		buildLotOneTwo.levelUp();
		Land buildLotOneTwoLevel1 = new BuildingLotOneTwo(1, '0');
		buildLotOneTwoLevel1.setLevel(1);
		assertThat(buildLotOneTwo, is(buildLotOneTwoLevel1));
	}

	@Test
	public void should_return_level_2_building_lot_3_when_input_is_level_1_building_lot_3_and_level_up() {
		Land buildingLotThree = new BuildingLotThree(2, '0');
		buildingLotThree.levelUp();
		buildingLotThree.levelUp();
		Land buildingLotThreeLevel2 = new BuildingLotThree(2, '0');
		buildingLotThreeLevel2.setLevel(2);
		assertThat(buildingLotThree, is(buildingLotThreeLevel2));
	}

	@Test
	public void should_return_level_3_building_lot_45_when_input_is_level_2_building_lot_45_and_level_up() {
		Land buildingLotFourFive = new BuildingLotFourFive(3, '0');
		buildingLotFourFive.levelUp();
		buildingLotFourFive.levelUp();
		buildingLotFourFive.levelUp();
		Land buildingLotFourFiveLevel3 = new BuildingLotFourFive(3, '0');
		buildingLotFourFiveLevel3.setLevel(3);
		assertThat(buildingLotFourFive, is(buildingLotFourFiveLevel3));
	}

	@Test
	public void should_return_100_when_input_is_level_0_building_lot_12() {
		Land buildingLotOneTwo = new BuildingLotOneTwo(7, '0');
		int cost = buildingLotOneTwo.getCost();
		assertThat(cost, is(100));
	}
}