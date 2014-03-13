package com.github.fileupload.servlet.action;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

public interface UploadAction {
    public boolean doAction(HttpServletRequest req) throws FileUploadException;
}
