package com.appvendas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appvendas.model.Empreendimento;

@Repository
public interface EmpreendimentoDaoInterface extends CrudRepository<Empreendimento, Long>{

}
