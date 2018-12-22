package com.merrill.util;

import org.apache.commons.mail.HtmlEmail;

import java.util.Random;

public class EmailUtil {
    public static boolean sendEmail(String emailAddress, String code){
        try{
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.163.com");
            email.setCharset("UTF-8");
            email.addTo(emailAddress);
            email.setFrom("merrill1@163.com", "验证码邮箱");
            email.setAuthentication("merrill1@163.com", "meifengxin980511");
            email.setSubject("这是一封验证码邮件！");
            email.setMsg("您正在进行注册操作，您的验证码是：" + code + "。如非本人操作，请忽略此邮件。");
            email.send();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String randCode(int num){
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            buffer.append(random.nextInt(10) + "");
        }
        return buffer.toString();
    }
}
