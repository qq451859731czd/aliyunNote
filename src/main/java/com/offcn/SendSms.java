package com.offcn;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.offcn.utils.PhoneFormatCheckUtils;

import java.util.HashMap;
import java.util.Map;

public class SendSms {
    //账号ID
    private String accessKeyId="LTAI4G5YtsiYyZwkQ8jbVjGp";
    //账号密码
    private String accessSecret="xonkzSBY4PqQLyZXRCDzJhXi9mdUTN";
    //阿里云模板ID
    private String TemplateCode ="SMS_198665672";
    //短信签名
    private String SignName ="ADC商城";


    public void Demo(String num,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        //短信收件人
        request.putQueryParameter("PhoneNumbers", num);
        //短信签名
        request.putQueryParameter("SignName", SignName);
        //阿里云平台模板ID
        request.putQueryParameter("TemplateCode", TemplateCode);
        //收到的验证码信息
        request.putQueryParameter("TemplateParam", code);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String num = "18760434321";
        if (!PhoneFormatCheckUtils.isChinaPhoneLegal(num)){
            System.out.println("手机号码错误!");
        }else {
            //生成随机6位验证码
            String code = ((long) (Math.random() * 1000000)) + "";
            System.out.println("验证码:" + code);
            Map map = new HashMap();
            map.put("code",code);
            String o = JSON.toJSON(map)+"";
            System.out.println(o);
            new SendSms().Demo(num,o);
        }
    }
}