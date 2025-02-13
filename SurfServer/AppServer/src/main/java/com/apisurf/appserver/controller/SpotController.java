package com.apisurf.appserver.controller;

import com.apisurf.appserver.model.spot.Spot;
import com.apisurf.appserver.model.spot.SpotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpotController {

    @Autowired
    private SpotDao spotDao;

    @GetMapping("/spot/get-all")
    public List<Spot> getAllSpots() {
        return spotDao.getAllSpots();
    }

    @PostMapping("/spot/save")
    public Spot save(@RequestBody Spot spot) {
        return spotDao.save(spot);
    }
}
