package com.github.fileupload.servlet;

import com.github.fileupload.servlet.action.FileUploadException;
import com.github.fileupload.servlet.action.FileCheckAction;
import com.github.fileupload.servlet.action.OrdninaryUploadAction;
import com.github.fileupload.servlet.action.UploadAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UploaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.process(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.process(req,resp);
    }

    protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        UploadAction paser = loadUploadRequestPaser(req);
        try {
            paser.doAction(req);
        } catch (FileUploadException e) {
            out.print(MessageHandler.getErrorMessage(e));
            return;
        } catch(Exception e ){
            out.print(MessageHandler.getErrorMessage(e.getMessage()));
            return;
        }
        out.print(MessageHandler.getSuccessMessage());

    }

    private UploadAction loadUploadRequestPaser(HttpServletRequest req) {
        String fileAction = req.getParameter("fileAction");
        if (fileAction.equals("check")) {
            return new FileCheckAction();
        } else if(fileAction.equals("upload")) {
            return new OrdninaryUploadAction();
        }
        else {
            return new OrdninaryUploadAction();
        }
    }

}
