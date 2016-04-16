package com.hzih.sslvpn.web.action.sslvpn.client;

import com.hzih.sslvpn.dao.UserDao;
import com.hzih.sslvpn.domain.User;
import com.hzih.sslvpn.utils.StringContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 15-5-26.
 */
public class ClientStatusAction extends ActionSupport {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String check() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        String json = null;
        String msg = null;
        String serial = request.getParameter("serial");
        if (null != serial) {
            User user = userDao.findBySerialNumber(serial);
            if (null != user) {
                int enable = user.getEnabled();
                if (enable == 0) {
                    msg = "用户已被禁用,不能进行拨号连接";
                    json = "{success:false,msg:'" + msg + "'}";
                } else {
                    boolean flag = checkCRL(serial);
                    if (flag == true) {
                        msg = "用户已被吊销证书,不能进行拨号连接";
                        json = "{success:false,msg:'" + msg + "'}";
                    } else {
                        msg = "校验用户正常";
                        json = "{success:true,msg:'" + msg + "'}";
                    }
                }
            } else {
                msg = "检测用户状态失败,未找到对应用户";
                json = "{success:false,msg:'" + msg + "'}";
            }
        }
        writer.write(json);
        writer.close();
        return null;
    }

    private boolean checkCRL(String serial) {
        boolean flag = false;
        File file = new File(StringContext.crl_path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    try {
                        FileInputStream fis = new FileInputStream(f);
                        CertificateFactory cf = CertificateFactory.getInstance("X.509");
                        X509CRL aCrl = (X509CRL) cf.generateCRL(fis);

                        Set tSet = aCrl.getRevokedCertificates();
                        Iterator tIterator = tSet.iterator();
                        while (tIterator.hasNext()) {
                            X509CRLEntry tEntry = (X509CRLEntry) tIterator.next();
                            String sn = tEntry.getSerialNumber().toString(16).toUpperCase();
                            if (sn.equalsIgnoreCase(serial)) {
                                return true;
                            }
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }
        return flag;
    }
}
