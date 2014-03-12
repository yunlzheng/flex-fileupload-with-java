package com.github.fileupload.servlet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public interface UploadAction {
    public boolean paser(HttpServletRequest req, PrintWriter resp) throws ActionException;
}
