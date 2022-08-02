package com.woniu.web.controllerException;

import com.woniu.web.controller.ConsutantsController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ConsutantsController.class)
public class ConsutantsControllerExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public String xxx(RuntimeException e) {
        if (e instanceof NullPointerException) {
            return " AppontmentController空指针异常:" + e.getMessage();
        } else if (e instanceof IndexOutOfBoundsException) {
            return " AppontmentController下标越界异常:" + e.getMessage();
        } else {
            return "AppontmentController其他异常" + e.getMessage();
        }
    }
}
