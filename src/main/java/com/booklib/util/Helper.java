package com.booklib.util;

import java.util.Random;
import java.io.File;
import javax.servlet.http.Part;

public class Helper {
    
    public static String getRandomString(int length){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
          int index = random.nextInt(alphabet.length());
          char randomChar = alphabet.charAt(index);
          sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString.toLowerCase();
    }
    
    //overload
    public static String getRandomString(){
        return getRandomString(10);
    }
    
    public static boolean saveFile(Part part, String path){
        boolean status = false;
        try{
            File uploadDir = new File(path);
            if(!uploadDir.exists()) uploadDir.mkdir();
            part.write(path);
            status = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    
    public static boolean deleteFile(String path){
        boolean status = false;
        try{
            File file = new File(path);
            status = file.delete(); // delete() method return boolean so.
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    
    public static String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return null;
    }
}
