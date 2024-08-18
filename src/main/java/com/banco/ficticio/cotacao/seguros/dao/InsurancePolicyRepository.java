package com.banco.ficticio.cotacao.seguros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.ficticio.cotacao.seguros.model.InsurancePolicyEntity;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicyEntity, Long>  {

}

