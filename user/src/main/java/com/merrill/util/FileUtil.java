package com.merrill.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class FileUtil {
    //允许接受的图片类型
    private static final String ALLOWED_IMAGE_TYPE = "png;jpg;jpeg";

    private static Properties p = new Properties();

    static {
        try {
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("filePath.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传图片
     * @param req
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public static String upload(HttpServletRequest req) throws ServletException, IOException {
        //解析和检查请求，请求方法是POST，请求编码是mulitipart/form-data
        String fileName = "";
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            return "";
        }
        try {
            //1.创建FileItemFactory对象
            //FileItemFactory是用来创建FileItem对象
            //FileItem：form表单中的菜单控制的封装
            DiskFileItemFactory factory = new DiskFileItemFactory();


            factory.setSizeThreshold(2 * 1024); //设置缓存大小
            //2.创建文件上传处理器
            //本身没有创建FileItem的关系 是一个包含关系
            ServletFileUpload upload = new ServletFileUpload(factory);


            //设置单个上传文件的大小限制
            upload.setFileSizeMax(1024 * 1024 * 10);

            //设置该次请求总数据大小限制
            upload.setSizeMax(1024 * 1024 * 20);

            //3.解析请求
            List<FileItem> items = upload.parseRequest(req);
            //4.迭代出每一个FileItem
            HashMap<String, Integer> map = new HashMap<>();
            for (FileItem item : items) {
                //获取表单属性的name属性
                String fieldName = item.getFieldName();
                //getFieldName 属性名
                //getName 上传的文件名
                if (item.isFormField()) {
                    //普通表单控件 获取普通表单控件的参数值
                    String value = item.getString("utf-8");
                    System.out.println(fieldName + "-" + value);
                    double num = Double.parseDouble(value);
                    map.put(fieldName, (int)num);
                } else {
                    //当前上传文件的mime类型
                    String mimeType = req.getServletContext().getMimeType(item.getName());
                    System.out.println(mimeType);

                    //文件拓展名
                    String ext = FilenameUtils.getExtension(item.getName());
                    String[] allowed = ALLOWED_IMAGE_TYPE.split(";");
                    //当前上传的文件格式不在允许的范围内
                    if (!Arrays.asList(allowed).contains(ext)) {
                        throw new LogicException("请上传正确的文件格式");
                    }

                    //表单上传挂件   把二进制数据写到哪一个文件中去
//                    item.write(new File("D:", FilenameUtils.getName(item.getName())));


                    //保存原始图片
                    String tempName1 = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(item.getName());
                    String dir = p.getProperty("path");
                    item.write(new File(dir, tempName1));

                    //裁剪图片
                    int x = map.get("x");
                    int y = map.get("y");
                    int height = map.get("height");
                    int width = map.get("width");
                    String tempName2 = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(item.getName());
                    ImageUtil.imgCut(dir + tempName1, x, y, width, height, dir + tempName2);
                    File img = new File(dir + tempName1);
                    img.delete();


                    //压缩图片
                    img = new File(dir + tempName2);
                    fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(item.getName());
                    FileOutputStream fileOutputStream = new FileOutputStream(dir + fileName);
                    ImageUtil.thumbnail_w_h(img, 200, 280, fileOutputStream);
                    fileOutputStream.close();
                    img.delete();
                }
            }
        } catch (FileUploadBase.SizeLimitExceededException e) {
            throw new LogicException("该次请求总数据大小不能超过20m", e);
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            throw new LogicException("单个文件大小不能超过10m", e);
        } catch (LogicException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 根据传入的图片获取输入流
     * @param image 图片信息
     * @return 输入流
     */
    public static InputStream changeImage(BufferedImage image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpeg", os);
            byte[] bs = os.toByteArray();
            InputStream ins = new ByteArrayInputStream(bs);
            return ins;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取磁盘绝对路径的前缀
     * @return 绝对路径信息
     */
    public static String getPath(){
        return p.getProperty("path");
    }

    public static void deleteFile(String filePath) {
        String fileName = p.getProperty("path") + filePath;
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("删除单个文件" + fileName + "成功！");
        } else {
            System.out.println("删除单个文件" + fileName + "失败！");
        }
    }
}

//业务逻辑异常
class LogicException extends RuntimeException {

    //错误原因自己定义
    public LogicException(String message) {
        super(message);
    }

    /**
     * @param message 异常信息
     * @param cause   异常的根本原因
     */
    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
