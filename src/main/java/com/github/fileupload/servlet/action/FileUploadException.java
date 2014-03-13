package com.github.fileupload.servlet.action;

public class FileUploadException extends Exception{

    private int errorCode;

    public FileUploadException(int errorCode) {
        super(ErrorCode.getErrorMessage(errorCode));
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
