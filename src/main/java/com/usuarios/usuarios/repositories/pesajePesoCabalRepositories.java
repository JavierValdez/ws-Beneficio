
package com.usuarios.usuarios.repositories;

import com.usuarios.usuarios.models.pesajePesoCabal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface pesajePesoCabalRepositories extends CrudRepository<pesajePesoCabal,Integer> {
    @Override
    public List<pesajePesoCabal> findAll();
}
