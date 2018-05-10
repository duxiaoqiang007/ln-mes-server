//package com.smu.vaan.controller;
//
//import com.smu.vaan.exception.ApiException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.web.AbstractErrorController;
//import org.springframework.boot.autoconfigure.web.ErrorAttributes;
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by vaan on 2017/2/23.
// */
//
////@RestController
//public class MyErrorController implements ErrorController {
////public class MyErrorController extends AbstractErrorController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(MyErrorController.class);
////    public MyErrorController(ErrorAttributes errorAttributes) {
////        super(errorAttributes);
////    }
//
//    public MyErrorController() {
//    }
//
////
//    @RequestMapping(path = "/error")
//    @ResponseBody
//    public Object error(Exception e, HttpServletRequest request) throws ApiException {
//        throw new ApiException(111, e.getMessage());
//    }
////
//
//
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
//}
