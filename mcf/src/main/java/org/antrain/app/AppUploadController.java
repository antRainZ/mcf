package org.antrain.app;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.antrain.app.config.OSSConfig;
import org.antrain.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/upload")
public class AppUploadController {
    @Resource
    private OSSConfig ossConfig;

    @PostMapping("/avatar")
    public AjaxResult uploadAvatar(MultipartFile file) {
        if (file == null ) {
            return AjaxResult.error("请上传头像");
        }
        try {
            OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(),
                    ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
            //System.out.println(file.getOriginalFilename());
            String [] fileAtt =  file.getOriginalFilename().split("\\.");
            String fileName = "avatar/" + IdUtil.simpleUUID() + "."+ fileAtt[fileAtt.length - 1];
            ossClient.putObject(ossConfig.getBucketName(), fileName, file.getInputStream());
            String data = "https://" + ossConfig.getBucketName() + "." +
                    ossConfig.getEndpoint()+ "/" + fileName;
            return  AjaxResult.success("上传成功",data);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("文件上传失败");
        }
    }

    @PostMapping("/cover")
    public AjaxResult uploadCover(MultipartFile file) {
        if (file == null ) {
            return AjaxResult.error("请上传封面图像");
        }
        try {
            OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(),
                    ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
            //System.out.println(file.getOriginalFilename());
            String [] fileAtt =  file.getOriginalFilename().split("\\.");
            String fileName = "cover/" + IdUtil.simpleUUID() + "."+ fileAtt[fileAtt.length - 1];
            ossClient.putObject(ossConfig.getBucketName(), fileName, file.getInputStream());
            String data = "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + fileName;
            return  AjaxResult.success("上传成功",data);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("封面上传失败");
        }
    }

    @PostMapping("/apply")
    public AjaxResult uploadApply(MultipartFile file) {
        if (file == null ) {
            return AjaxResult.error("请上传申请文件");
        }
        try {
            OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(),
                    ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
            //System.out.println(file.getOriginalFilename());
            String [] fileAtt =  file.getOriginalFilename().split("\\.");
            String fileName = "apply/" + IdUtil.simpleUUID() + "."+ fileAtt[fileAtt.length - 1];
            ossClient.putObject(ossConfig.getBucketName(), fileName, file.getInputStream());
            String data = "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + fileName;
            return  AjaxResult.success("上传成功",data);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("文件上传失败");
        }
    }
}
