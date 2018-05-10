//package com.smu.vaan.service;
//
//import com.smu.vaan.exception.ApiException;
//import com.smu.vaan.model.SysSet;
//import com.smu.vaan.repository.SysSetRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * Created by vaan on 2017/4/8.
// */
//
//@Service
//public class SysSetServiceImpl {
//    @Autowired
//    private SysSetRepository sysSetRepository;
//
//    /**
//     * 获取网站查询的起始时间
//     * @return 网站查询的起始时间
//     */
//    public Date getWebStartDate() throws ApiException {
//        SysSet sysSet = sysSetRepository.findOne("web_start_date");
//        String dateStr = sysSet.getItemValue().trim();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date webStartDate;
//        try {
//            webStartDate = sdf.parse(dateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            throw new ApiException(88888, "解析时间失败");
//        }
//
//        return webStartDate;
//    }
//}
