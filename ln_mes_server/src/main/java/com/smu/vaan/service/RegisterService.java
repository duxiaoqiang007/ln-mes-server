package com.smu.vaan.service;

import com.smu.vaan.model.CRegister;
import com.smu.vaan.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;
    public Iterable<CRegister> getAllRegister(){return  registerRepository.findAll();}
}
