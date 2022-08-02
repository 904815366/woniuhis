package com.woniu.web.controllerException;

import com.woniu.web.controller.MoneylistController;
import com.woniu.web.controller.RegisterController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice(assignableTypes = MoneylistController.class)
public class MoneyListControllerExceptionHandler {

    /**
     *  RegisterController 中的方法但凡抛出了 RuntimeException 异常，都会"导致"下面这个方法的执行。
     *  Controller 中"抛"的异常会作为这个方法的参数传到方法里面来。
     */
    @ExceptionHandler(RuntimeException.class)
    public String xxx(RuntimeException e) {
        if (e instanceof NullPointerException) {
            return "MoneylistController空指针异常:"+e.getMessage();
        } else if (e instanceof IndexOutOfBoundsException) {
            return "MoneylistController下标越界异常:"+e.getMessage();
        } else if (e instanceof NoSuchElementException) {
            return "MoneylistController从redis未找到数据:"+e.getMessage();
        }else {
            return "MoneylistController其他异常"+e.getMessage();
        }
    }

}
