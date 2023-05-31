
package com.usuarios.usuarios.repositories;

import com.usuarios.usuarios.models.Transportista;
import com.usuarios.usuarios.models.pesajePesoCabal;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface pesajePesoCabalRepositories extends CrudRepository<pesajePesoCabal,Integer> {
    @Override
    public List<pesajePesoCabal> findAll();
}
