package com.github.fileupload.servlet.action;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

public class BigFileUploadAction implements UploadAction {

    @Override
    public boolean doAction(HttpServletRequest req) throws FileUploadException {
        return false;
    }
}