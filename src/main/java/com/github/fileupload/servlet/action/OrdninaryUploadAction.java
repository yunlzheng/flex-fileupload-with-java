package com.github.fileupload.servlet.action;

import com.github.fileupload.servlet.Configuration;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class OrdninaryUploadAction implements UploadAction {

    private File uploadPathFile;
    private File tempPathFile;
    private String uploadPath;
    private Configuration configuration;

    public OrdninaryUploadAction() {
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
        if (!uploadPathFile.exists()) {
            uploadPathFile.mkdir();
        }
        tempPathFile = new File(this.configuration.getTempPath());
        if (!tempPathFile.exists()) {
            tempPathFile.mkdir();
        }
    }

    private ServletFileUpload getServletFileUpload() {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // Set factory constraints
        factory.setSizeThreshold(this.configuration.getSizeThreshold()); // 设置缓冲区大小，这里是4kb
        factory.setRepository(tempPathFile);// 设置缓冲区目录
        ServletFileUpload upload = new ServletFileUpload(factory);
        return upload;
    }

    @Override
    public boolean doAction(HttpServletRequest req) throws FileUploadException {
        String fileName = req.getParameter("fileName");
        String fileSize = req.getParameter("fileSize");
        int fileOverwrite = req.getParameter("fileOverwrite") != null ? Integer.parseInt(req.getParameter("fileOverwrite")) : 0;
        boolean isMutipart = ServletFileUpload.isMultipartContent(req);
        if (!isMutipart) {
            throw new FileUploadException(ErrorCode.NOT_MULTIPART_CONTENT.getErrorCode());
        }

        ServletFileUpload upload = getServletFileUpload();
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(req);
        } catch (Exception e) {
            throw new FileUploadException(ErrorCode.PARSE_REQUEST_FALIS.getErrorCode());
        } finally {
            if (items != null) {
                saveFile(items, fileName, fileOverwrite);
            }
        }
        ;
        return true;
    }

    private void saveFile(List<FileItem> items, String fileName, int fileOverwrite) throws FileUploadException {

        Iterator<FileItem> itr = items.iterator();
        while (itr.hasNext()) {
            FileItem fi = (FileItem) itr.next();
            if (fi.isFormField()) {
                continue;
            }
            File fullFile = new File(fileName);
            File savedFile = new File(uploadPath, fullFile.getName());

            if (fileOverwrite == 1) {
                savedFile.delete();
            }
            try {
                fi.write(savedFile);
            } catch (Exception e) {
                throw new FileUploadException(ErrorCode.COULD_NOT_WRITE_FILE.getErrorCode());
            }

        }
    }

}