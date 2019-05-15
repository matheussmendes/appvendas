package com.appvendas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.appvendas.model.Vendas;

@Repository
public interface VendasDaoInterface extends CrudRepository<Vendas, Long>{

}
