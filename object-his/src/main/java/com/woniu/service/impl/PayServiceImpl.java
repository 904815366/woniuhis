package com.woniu.service.impl;

import com.woniu.service.IPayService;

import com.alipay.api.AlipayApiException;
import com.woniu.domain.AlipayBean;
import com.woniu.service.IPayService;
import com.woniu.util.AlipayUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PayServiceImpl implements IPayService
{

    @Autowired
    private AlipayUtile alipayUtile;

    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return alipayUtile.pay(alipayBean);
    }

}
