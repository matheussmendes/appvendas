package com.appvendas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appvendas.model.MetaDeVendaMensal;

@Repository
public interface MetaMensalDaoInterface extends CrudRepository<MetaDeVendaMensal, Long>{

}
