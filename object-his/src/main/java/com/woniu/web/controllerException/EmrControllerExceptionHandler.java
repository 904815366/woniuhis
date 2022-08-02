package com.woniu.web.controllerException;

import com.woniu.web.controller.EmrController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = EmrController.class)
public class EmrControllerExceptionHandler {
    /**
     *  RegisterController 中的方法但凡抛出了 RuntimeException 异常，都会"导致"下面这个方法的执行。
     *  Controller 中"抛"的异常会作为这个方法的参数传到方法里面来。
     */
    @ExceptionHandler(RuntimeException.class)
    public String xxx(RuntimeException e) {
        if (e instanceof NullPointerException) {
            return " AppontmentController空指针异常:"+e.getMessage();
        } else if (e instanceof IndexOutOfBoundsException) {
            return " AppontmentController下标越界异常:"+e.getMessage();
        } else {
            return "AppontmentController其他异常"+e.getMessage();
        }
    }
}
