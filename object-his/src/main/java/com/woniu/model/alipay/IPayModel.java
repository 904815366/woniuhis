package com.woniu.model.alipay;
import com.alipay.api.AlipayApiException;
import com.woniu.domain.AlipayBean;
import org.springframework.stereotype.Component;


public interface IPayModel
{
    /**
     * 支付宝支付接口
     * @param alipayBean
     * @return
     * @throws AlipayApiException
     */
    String aliPay(AlipayBean alipayBean) throws AlipayApiException;

}
