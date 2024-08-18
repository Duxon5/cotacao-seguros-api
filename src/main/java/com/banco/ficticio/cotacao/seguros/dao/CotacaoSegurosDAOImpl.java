package com.banco.ficticio.cotacao.seguros.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banco.ficticio.cotacao.seguros.model.AssistanceEntity;
import com.banco.ficticio.cotacao.seguros.model.CoverageEntity;
import com.banco.ficticio.cotacao.seguros.model.CustomerEntity;
import com.banco.ficticio.cotacao.seguros.model.InsurancePolicyEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CotacaoSegurosDAOImpl implements CotacaoSegurosDAO {

	@Autowired
	private InsurancePolicyRepository insurancePolicyRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CoverageRepository coverageRepository;
	
	@Autowired
	private AssistanceRepository assistanceRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Long generateId() {
        String sql = "SELECT NEXTVAL('insurance_policy_id') AS id";
        return (Long) entityManager.createNativeQuery(sql, Long.class).getSingleResult();
    }
	
	@Override
	public void incluirInsurancePolicy(InsurancePolicyEntity insurancePolicyEntity) {
		insurancePolicyRepository.save(insurancePolicyEntity);
		insurancePolicyRepository.flush();
	}
	
	@Override
	public Optional<InsurancePolicyEntity> obterPorId(Long insurancePolicyEntity) {
	    return insurancePolicyRepository.findById(insurancePolicyEntity);
	}

	@Override
	public void incluirCustomer(CustomerEntity customerEntity) {
		customerRepository.save(customerEntity);
	}

	@Override
	public void incluirCoverage(CoverageEntity coverageEntity) {
		coverageRepository.save(coverageEntity);
	}

	@Override
	public void incluirAssistance(AssistanceEntity assistanceEntity) {
		assistanceRepository.save(assistanceEntity);
	}

	@Override
	public InsurancePolicyEntity buscarPorInsurancePolicyId(Long insurancePolicyId) {

	    String hql =  "SELECT p FROM InsurancePolicyEntity p "
	    			+ "WHERE "
						+ "p.insurancePolicyId = :insurancePolicyId";

	    Query query = entityManager.createQuery(hql);
	    
	    query.setParameter("insurancePolicyId", insurancePolicyId);

	    @SuppressWarnings("unchecked")
		List<InsurancePolicyEntity> listPolicies = query.getResultList();
	    
	    if(listPolicies != null && !listPolicies.isEmpty()) 
	    	return listPolicies.get(0);

	    return null;
	}

}