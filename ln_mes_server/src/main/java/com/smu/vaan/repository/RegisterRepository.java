package com.smu.vaan.repository;

import com.smu.vaan.model.CRegister;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends CrudRepository<CRegister,String>{
}
