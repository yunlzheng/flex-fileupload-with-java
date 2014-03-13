package com.github.fileupload.servlet;

import com.github.fileupload.servlet.action.ErrorCode;
import com.github.fileupload.servlet.action.FileUploadException;

public class MessageHandler {

    public static String getErrorMessage(String error) {
        return buildErrorMessage(0, error);
    }

    public static String getErrorMessage(FileUploadException ex) {

        int code = ex.getErrorCode();
        String error = ErrorCode.getErrorMessage(code);
        return buildErrorMessage(code, error);

    }

    public static String getErrorMessage(int code, String error){
        return buildErrorMessage(code, error);
    }

    public static String getSuccessMessage() {
        String message = "";
        return buildSuccessMessage(message);
    }

    private static String buildErrorMessage(int code, String error) {
        StringBuilder sb = new StringBuilder("<error");
        if(code!=-1){
            sb.append(" id=\""+code+"\">");
        }else{
            sb.append(">");
        }
        sb.append(error);
        sb.append("</error>");
        return sb.toString();
    }

    private static String buildSuccessMessage(String success) {
        StringBuilder sb = new StringBuilder("<success>");
        sb.append(success);
        sb.append("</success>");
        return sb.toString();
    }

}
