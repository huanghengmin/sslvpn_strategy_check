package com.hzih.sslvpn.web.action.sslvpn.client;

import com.hzih.sslvpn.utils.FileUtil;
import com.hzih.sslvpn.utils.StringContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-9
 * Time: 下午12:35
 * To change this template use File | Settings | File Templates.
 */
public class ClientAction extends ActionSupport {
    private Logger logger = Logger.getLogger(ClientAction.class);

    /**
     *
     * @return
     * @throws Exception
     */
    public String downDh()throws Exception{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        File file = new File(StringContext.dh_file);
        if(file.exists()) {
            response = FileUtil.copy(file, response);
            logger.info("客户端:" + request.getRemoteAddr() + ",下载 dh_file 成功,时间:" + new Date());
        }
        else
            response.setStatus(404);
        return null;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String downStaticKey()throws Exception{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        File file = new File(StringContext.ta_key_file);
        if(file.exists()) {
            response = FileUtil.copy(file, response);
            logger.info("客户端:" + request.getRemoteAddr() + ",下载 ta_key_file 成功,时间:" + new Date());
        }
        else
            response.setStatus(404);
        return null;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String downCa()throws Exception{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        File file = new File(StringContext.ca_file);
        if(file.exists()) {
            response = FileUtil.copy(file, response);
            logger.info("客户端:" + request.getRemoteAddr() + ",下载 ca_file 成功,时间:" + new Date());
        }
        else
            response.setStatus(404);
        return null;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String downWindowsConfig()throws Exception{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        File file = new File(StringContext.windows_config_file);
        if(file.exists()) {
            response = FileUtil.copy(file, response);
            logger.info("客户端:" + request.getRemoteAddr() + ",下载 windows_config_file 成功,时间:" + new Date());
        }
        else
            response.setStatus(404);
        return null;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String downAndroidConfig()throws Exception{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        File file = new File(StringContext.android_config_file);
        if(file.exists()) {
            response = FileUtil.copy(file, response);
            logger.info("客户端:" + request.getRemoteAddr() + ",下载 android_config_file 成功,时间:" + new Date());
        }
        else
            response.setStatus(404);
        return null;
    }
}
