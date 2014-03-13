package com.github.fileupload.servlet.action;

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

    private String tempPath = "target\\buffer\\";
    private String uploadPath="target";
    private File uploadPathFile;
    private  File tempPathFile;

    public OrdninaryUploadAction(){
        uploadPathFile = new File(uploadPath);

        if(!uploadPathFile.exists()) {
            uploadPathFile.mkdir();
        }

        tempPathFile = new File(uploadPath);
        if(!tempPathFile.exists()) {
            tempPathFile.mkdir();
        }
    }

    @Override
    public boolean paser(HttpServletRequest req, PrintWriter resp) throws ActionException {
        String fileName = req.getParameter("fileName");
        String fileSize = req.getParameter("fileSize");

        boolean isMutipart = ServletFileUpload.isMultipartContent(req);
        if(!isMutipart){
            resp.write("<error>Multipart错误</error>");
            return false;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // Set factory constraints
        factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
        factory.setRepository(tempPathFile);// 设置缓冲区目录

        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            Iterator<FileItem> itr = items.iterator();
            while (itr.hasNext()) {
                FileItem fi = (FileItem) itr.next();
                if(fi.isFormField()) {
                    continue;
                }

                System.out.println("####################################################################");
                System.out.println(fi.getSize());
                System.out.println();
                System.out.println(fi.getString());
                File fullFile = new File(fileName);
                File savedFile = new File(uploadPath, fullFile.getName());
                fi.write(savedFile);

            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.write("<error>"+e.getMessage()+"</error>");
            return false;
        }

        resp.write("<success/>");
        return true;
    }



}