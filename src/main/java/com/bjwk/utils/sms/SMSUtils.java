package com.bjwk.utils.sms;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SMSUtils {
	
	public static void main(String[] args) {
		sendPhoneVerifyCode("17621302014","1234");
	}
	
	  public static boolean sendPhoneVerifyCode(String testPhone,String code) {
		  
	        String testUsername = "zhiyou6688"; //在短信宝注册的用户名
	        String testPassword = "zhiyou6688"; //在短信宝注册的密码
	        
	        String testContent = "【佳家校园】您的验证码是"+code+",五分钟内有效。若非本人操作请忽略此消息。"; // 注意测试时，也请带上公司简称或网站签名，发送正规内容短信。千万不要发送无意义的内容：例如 测一下、您好。否则可能会收不到
	 
	        String httpUrl = "http://api.smsbao.com/sms";
	 
	        StringBuffer httpArg = new StringBuffer();
	        httpArg.append("u=").append(testUsername).append("&");
	        httpArg.append("p=").append(md5(testPassword)).append("&");
	        httpArg.append("m=").append(testPhone).append("&");
	        httpArg.append("c=").append(encodeUrlString(testContent, "UTF-8"));
	        String sign=request(httpUrl, httpArg.toString());
	        if(sign.equals("0")){
	        	return true;
	        }
	        return false;
	    }
	 
	    public static String request(String httpUrl, String httpArg) {
	        BufferedReader reader = null;
	        String result = null;
	        StringBuffer sbf = new StringBuffer();
	        httpUrl = httpUrl + "?" + httpArg;
	 
	        try {
	            URL url = new URL(httpUrl);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	            connection.connect();
	            InputStream is = connection.getInputStream();
	            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	            String strRead = reader.readLine();
	            if (strRead != null) {
	                sbf.append(strRead);
	                while ((strRead = reader.readLine()) != null) {
	                    sbf.append("\n");
	                    sbf.append(strRead);
	                }
	            }
	            reader.close();
	            result = sbf.toString();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	 
	    public static String md5(String plainText) {
	        StringBuffer buf = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(plainText.getBytes());
	            byte b[] = md.digest();
	            int i;
	            buf = new StringBuffer("");
	            for (int offset = 0; offset < b.length; offset++) {
	                i = b[offset];
	                if (i < 0)
	                    i += 256;
	                if (i < 16)
	                    buf.append("0");
	                buf.append(Integer.toHexString(i));
	            }
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return buf.toString();
	    }
	 
	    public static String encodeUrlString(String str, String charset) {
	        String strret = null;
	        if (str == null)
	            return str;
	        try {
	            strret = java.net.URLEncoder.encode(str, charset);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	        return strret;
	    }
}
