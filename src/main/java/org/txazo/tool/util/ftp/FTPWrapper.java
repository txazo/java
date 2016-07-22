package org.txazo.tool.util.ftp;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FTP包装类
 */
public class FTPWrapper {

    private FTPClient ftpClient;

    public FTPWrapper() {
        ftpClient = new FTPClient();
    }

    /**
     * 连接
     *
     * @param hostName 主机
     * @param port     端口
     * @param userName 用户名
     * @param passWord 密码
     * @return
     * @throws IOException
     */
    public boolean connect(String hostName, int port, String userName, String passWord) throws IOException {
        ftpClient.connect(hostName, port);
        ftpClient.login(userName, passWord);
        int replyCode = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            ftpClient.disconnect();
            return false;
        }
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        return true;
    }

    /**
     * 上传文件
     *
     * @param data   二进制流
     * @param path   目录
     * @param output 文件名
     * @return
     * @throws Exception
     */
    public boolean uploadFile(byte[] data, String path, String output) throws IOException {
        InputStream input = null;
        try {
            if (ftpClient.changeWorkingDirectory(path)) {
                input = new ByteArrayInputStream(data);
                return ftpClient.storeFile(output, input);
            }
        } finally {
            IOUtils.closeQuietly(input);
        }
        return false;
    }

    /**
     * 复制远程文件
     *
     * @param path 目录
     * @param from 源文件
     * @param to   目标文件
     * @return
     * @throws Exception
     */
    public boolean copyRemoteFile(String path, String from, String to) throws IOException {
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = null;
        try {
            if (ftpClient.changeWorkingDirectory(path)) {
                baos = new ByteArrayOutputStream();
                if (ftpClient.retrieveFile(from, baos)) {
                    bais = new ByteArrayInputStream(baos.toByteArray());
                    return ftpClient.storeFile(to, bais);
                }
            }
        } finally {
            IOUtils.closeQuietly(bais);
            IOUtils.closeQuietly(baos);
        }
        return false;
    }

    /**
     * 重命名文件
     *
     * @param path    目录
     * @param oldName 老文件名
     * @param newName 新文件名
     * @return
     * @throws IOException
     */
    public boolean rename(String path, String oldName, String newName) throws IOException {
        return ftpClient.changeWorkingDirectory(path) && ftpClient.rename(oldName, newName);
    }

    /**
     * 重命名文件
     *
     * @param oldName 老文件名
     * @param newName 新文件名
     * @return
     * @throws IOException
     */
    public boolean rename(String oldName, String newName) throws IOException {
        return ftpClient.rename(oldName, newName);
    }

    /**
     * 关闭连接
     */
    public void close() throws IOException {
        ftpClient.logout();
        if (ftpClient.isConnected()) {
            ftpClient.disconnect();
        }
    }

}
