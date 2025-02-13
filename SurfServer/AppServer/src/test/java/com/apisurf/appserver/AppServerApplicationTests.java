package com.apisurf.appserver;

import com.apisurf.appserver.model.spot.Spot;
import com.apisurf.appserver.model.spot.SpotDao;
import com.apisurf.appserver.model.spot.SurfBreak;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AppServerApplicationTests {

	@Autowired
	private SpotDao spotDao;

	@Test
	void addSpotTest() {
		Spot spot = new Spot();
		spot.setDestination("Honolulu");
		spot.setAddress("Hawai");
		spot.setDifficulty_level(3);
		spot.setPhoto_url("ada.com/honolulu");
		spot.setSurfBreak(SurfBreak.REEFBREAK);
		spotDao.save(spot);
	}

	//@Test
	void getAllSpots() {
		List<Spot> spots = spotDao.getAllSpots();
		System.out.println(spots);
	}

	//@Test
	void getAllSpotsAndDeleteThem() {
		List<Spot> spots = spotDao.getAllSpots();
		for (Spot spot : spots) {
			spotDao.delete(spot);
		}
	}

}
