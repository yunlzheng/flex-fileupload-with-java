package com.github.fileupload.servlet.action;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCode {

    PARSE_REQUEST_FALIS(300, "parse file content error"),
    NOT_MULTIPART_CONTENT(400, "not mutipart request"),
    FILE_ALREADY_EXIST(410, "File already exists"),
    FILE_SIZE_EXCEED_MAXIMUM(420, "File size exceeds maximum"),
    FILE_TYPE_NOT_ALLOWED(430, "File type not allowed"),
    CLOULD_NOT_OPEN_FILE(510, "Could not open file for writing"),
    FILE_SEEK_FAILED(515, "File seek operation failed"),
    COULD_NOT_WRITE_FILE(520, "Could not write to file"),
    TRUNCATE_FILE_FAILED(525, "Truncate file failed"),
    COUNLD_NOT_WIRTE_TMP_FILE_HEADER(530, "Could not write temporary file header"),
    FILE_CLOSE_ERROR(540, "File could not be closed"),
    TMPFILE_CLOULD_NOT_RENAME(550, "Temporary file could not be renamed to original"),
    FILE_DELETED_FAILED(555, "File could not be deleted"),
    COMPRESSION_NOT_SUPPORT(560, "Data compression not supported (ZLib)");
    private static Map<Integer, String> codeMap = new HashMap<Integer, String>();

    static {

        for (ErrorCode error : ErrorCode.values()) {
            codeMap.put(error.getErrorCode(), error.getMessage());
        }

    }

    private int errorCode;
    private String message;

    private ErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static String getErrorMessage(int code) {
        System.out.println(codeMap.get(code));
        return codeMap.get(code);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

}
