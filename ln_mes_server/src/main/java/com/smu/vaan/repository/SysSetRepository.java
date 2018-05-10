package com.smu.vaan.repository;

import com.smu.vaan.model.SysSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vaan on 2017/4/8.
 */
@Repository
public interface SysSetRepository extends CrudRepository<SysSet, String> {

}
