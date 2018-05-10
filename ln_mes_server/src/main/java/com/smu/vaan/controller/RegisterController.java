package com.smu.vaan.controller;

import com.smu.vaan.model.CRegister;
import com.smu.vaan.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @RequestMapping(method = RequestMethod.GET,value = "/wms/register")
    public Iterable<CRegister> getAllRegister(){
        return  registerService.getAllRegister();
    }
}
