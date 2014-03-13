package com.github.fileupload.servlet.action;

import com.github.fileupload.servlet.Configuration;
import com.github.fileupload.servlet.MessageHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintWriter;

public class FileCheckAction implements UploadAction {

    private Configuration config;

    public FileCheckAction(){
        this.config = new Configuration();
    }

    @Override
    public boolean doAction(HttpServletRequest req) throws FileUploadException {
        String fileName = req.getParameter("fileName");
        if(isFileExist(fileName)){
            throw new FileUploadException(ErrorCode.FILE_ALREADY_EXIST.getErrorCode());
        }
        return false;
    }

    public boolean isFileExist(String fileName) {
        String uploadPath = this.config.getUploadPath();
        File fullFile = new File(fileName);
        File savedFile = new File(uploadPath, fullFile.getName());
        return savedFile.exists() ? true: false;
    }

}