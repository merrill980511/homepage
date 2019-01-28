package com.merrill.util;

import org.apache.commons.mail.HtmlEmail;

import java.util.Properties;
import java.util.Random;

public class EmailUtil {

    private static Properties p = new Properties();

    static {
        try {
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("email.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送传入的验证码到传入的邮箱中
     *
     * @param emailAddress 待发送验证码党的邮箱
     * @param code         已经生成的验证码
     * @return true表示发送成功，false表示发送失败
     */
    public static boolean sendEmail(String emailAddress, String code) {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(p.getProperty("hostName"));
            email.setCharset("UTF-8");
            email.addTo(emailAddress);
            email.setFrom(p.getProperty("from"), "验证码邮箱");
            email.setAuthentication(p.getProperty("username"), p.getProperty("password"));
            email.setSubject("这是一封验证码邮件！");
            email.setMsg("您正在进行注册操作，您的验证码是：" + code + "。如非本人操作，请忽略此邮件。");
            System.out.println(code);
            System.out.println("send email to " + emailAddress);

            email.send();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 生成指定位数的验证码
     *
     * @param num 验证码的位数
     * @return 返回生成的验证码
     */
    public static String randCode(int num) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            buffer.append(random.nextInt(10) + "");
        }
        return buffer.toString();
    }
}
