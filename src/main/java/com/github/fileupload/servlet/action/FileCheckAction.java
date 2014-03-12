package com.github.fileupload.servlet.action;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

public class FileCheckAction implements UploadAction {

    @Override
    public boolean paser(HttpServletRequest req, PrintWriter resp) throws ActionException {
        resp.write("<success/>");
        return false;
    }
}