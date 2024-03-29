package com.immoc.server.exception;

import com.immoc.server.enums.ResultEnum;

public class SellException  extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
