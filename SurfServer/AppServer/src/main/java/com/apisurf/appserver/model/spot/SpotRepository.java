package com.apisurf.appserver.model.spot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository extends CrudRepository<Spot, Integer> {
}
