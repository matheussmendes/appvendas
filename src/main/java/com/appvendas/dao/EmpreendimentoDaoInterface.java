package com.appvendas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appvendas.model.Empreendimento;
/*
 * Desenvolvedor: Matheus Mendes
 * 
 * suportetecnologia@outlook.com.br
*/
@Repository
public interface EmpreendimentoDaoInterface extends CrudRepository<Empreendimento, Long>{

}
