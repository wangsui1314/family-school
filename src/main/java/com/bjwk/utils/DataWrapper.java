package com.bjwk.utils;

import lombok.Data;


/**
 * @author lihui_vendor
 */
@Data
public class DataWrapper<T> {
    private CallStatusEnum callStatus;
    private ErrorCodeEnum errorCode;
    private T data;
    private String token;
    private String msg;

    public DataWrapper() {
        callStatus = CallStatusEnum.SUCCEED;
        errorCode = ErrorCodeEnum.No_Error;
    }

    public DataWrapper(CallStatusEnum callStatus, T data, String msg) {
        this.callStatus = callStatus;
        this.data = data;
        this.msg = msg;
    }

    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
        if (!errorCode.equals(ErrorCodeEnum.No_Error)) {
            this.callStatus = CallStatusEnum.FAILED;
        }
        this.msg = errorCode.getLabel();

    }
    public DataWrapper(ErrorCodeEnum errorCodeEnum, T data, String msg) {
        this.setErrorCode(errorCodeEnum);
        this.data = data;
        this.msg = msg;
    }

    public static <R>  DataWrapper<R> ok(R data, String msg) {

        return new DataWrapper<R>(CallStatusEnum.SUCCEED, data, msg);
    }

    public static <R> DataWrapper<R> error(R data, String msg) {
        return new DataWrapper<R>(CallStatusEnum.FAILED, data, msg);
    }

}
