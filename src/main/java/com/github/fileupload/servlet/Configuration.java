package com.github.fileupload.servlet;

public class Configuration {

    private int maxSizeOfFile; //单个文件限制 -1 表示无限制

    private String uploadPath = "target"; // 上传文件路径

    private String tempPath = "target\\buffer\\";; // 零时文件路径

    private int SizeThreshold = 4096; // 缓冲区大小

    public int getSizeThreshold() {
        return SizeThreshold;
    }

    public void setSizeThreshold(int sizeThreshold) {
        SizeThreshold = sizeThreshold;
    }

    public int getMaxSizeOfFile() {
        return maxSizeOfFile;
    }

    public void setMaxSizeOfFile(int maxSizeOfFile) {
        this.maxSizeOfFile = maxSizeOfFile;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }
}
