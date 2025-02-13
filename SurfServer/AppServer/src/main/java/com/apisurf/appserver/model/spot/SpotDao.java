package com.apisurf.appserver.model.spot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//DAO "Data Access Object"
@Service
public class SpotDao {

    //activer l'injection automatique des dépendances
    @Autowired
    private SpotRepository repository;

    public Spot save(Spot spot) {
        return repository.save(spot);
    }

    public List<Spot> getAllSpots() {
        List<Spot> spots = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(spots::add);
        return spots;
    }

    public void delete(Spot spot){
        repository.delete(spot);
    }
}
