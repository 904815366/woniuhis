package com.woniu.web.controller.alipay;

import com.alipay.api.AlipayApiException;
import com.google.common.eventbus.EventBus;
import com.woniu.config.ResponseResult;
import com.woniu.domain.AlipayBean;
import com.woniu.model.alipay.IPayModel;
import com.woniu.web.fo.InsertMoneyRecordComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.ConstantCallSite;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class AliPayController
{

    @Resource
    private EventBus bus;

    @Autowired
    private IPayModel model;


    private Integer registerid;
    private Double prepaymoney;
    private Integer userid;
    private String type;


    @PostMapping(value = "/order/alipay")
    public String alipay(String outTradeNo, String subject, String totalAmount, String body ,
                         Integer registerid,Double prepaymoney,Integer userid,String type)
            throws AlipayApiException
    {
        this.registerid = registerid;
        this.prepaymoney = prepaymoney;
        this.userid = userid;
        this.type = type;

        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(body);
        String s = model.aliPay(alipayBean);
        System.out.println(s);
        return s;
    }

    @RequestMapping(value = "/order/no")
    public String alipay(HttpServletRequest request) throws AlipayApiException
    {
        System.out.println(request);
        System.out.println(this.registerid);
        InsertMoneyRecordComment build = InsertMoneyRecordComment.builder().registerid(this.registerid).prepaymoney(this.prepaymoney)
                .type(this.type).userid(this.userid).build();

        bus.post(build);
//        return new ResponseResult<>(2000,"ok");

        return "ok";
    }


}
