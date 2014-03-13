package com.github.fileupload.servlet.action;

import com.github.fileupload.servlet.Configuration;
import com.github.fileupload.servlet.MessageHandler;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class OrdninaryUploadAction implements UploadAction {

    private File uploadPathFile;
    private  File tempPathFile;
    private String uploadPath;
    private Configuration configuration;

    public OrdninaryUploadAction(){
        this.configuration = new Configuration();
        this.Initialization();
    }

    public OrdninaryUploadAction(Configuration config) {
        this.configuration = config;
        this.Initialization();
    }

    private void Initialization() {
        uploadPath = this.configuration.getUploadPath();
        uploadPathFile = new File(uploadPath);
        if(!uploadPathFile.exists()) {
            uploadPathFile.mkdir();
        }
        tempPathFile = new File(this.configuration.getTempPath());
        if(!tempPathFile.exists()) {
            tempPathFile.mkdir();
        }
    }

    private  ServletFileUpload getServletFileUpload() {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // Set factory constraints
        factory.setSizeThreshold(this.configuration.getSizeThreshold()); // 设置缓冲区大小，这里是4kb
        factory.setRepository(tempPathFile);// 设置缓冲区目录
        ServletFileUpload upload = new ServletFileUpload(factory);
        return upload;
    }

    @Override
    public boolean paser(HttpServletRequest req, PrintWriter resp) throws ActionException {
        String fileName = req.getParameter("fileName");
        String fileSize = req.getParameter("fileSize");

        boolean isMutipart = ServletFileUpload.isMultipartContent(req);
        if(!isMutipart){
            resp.write(MessageHandler.getErrorMessage("Multipart错误"));
            return false;
        }

        ServletFileUpload upload = getServletFileUpload();

        try {
            List<FileItem> items = upload.parseRequest(req);
            Iterator<FileItem> itr = items.iterator();
            while (itr.hasNext()) {
                FileItem fi = (FileItem) itr.next();
                if(fi.isFormField()) {
                    continue;
                }
                File fullFile = new File(fileName);
                File savedFile = new File(uploadPath, fullFile.getName());
                fi.write(savedFile);

            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.write(MessageHandler.getErrorMessage(e));
            return false;
        }

        resp.write(MessageHandler.getSuccessMessage());
        return true;
    }

}