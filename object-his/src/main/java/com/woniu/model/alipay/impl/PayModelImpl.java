package com.woniu.model.alipay.impl;
import com.alipay.api.AlipayApiException;
import com.woniu.domain.AlipayBean;
import com.woniu.model.alipay.IPayModel;
import com.woniu.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class PayModelImpl implements IPayModel
{
    @Autowired
    private IPayService service;


    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException
    {
        return service.aliPay(alipayBean);
    }
}
