package com.lenovohit.lartemis_api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("rawtypes")
public class DeepCopyUtil {
	
     //深拷贝1：递归方法
    
	@SuppressWarnings("unchecked")
	public void copy(List src,List dest){
         for (int i = 0 ;i < src.size() ; i++) {
             Object obj = src.get(i);            
             if (obj instanceof List){
                 dest.add(new ArrayList());
                     copy((List)obj,(List)((List)dest).get(i));
             }else{
                 dest.add(obj);
             }
         }
        
     }
    
     //深拷贝2:序列化|反序列化方法
     public List copyBySerialize(List src) throws IOException, ClassNotFoundException{
         ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
         ObjectOutputStream out = new ObjectOutputStream(byteOut);
         out.writeObject(src);
    
         ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
         ObjectInputStream in =new ObjectInputStream(byteIn);
         List dest = (List)in.readObject();
         return dest;
     }
     
     //深拷贝2:序列化|反序列化方法
     public static Object copyBySerialize(Object src) throws IOException, ClassNotFoundException{
         ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
         ObjectOutputStream out = new ObjectOutputStream(byteOut);
         out.writeObject(src);
    
         ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
         ObjectInputStream in =new ObjectInputStream(byteIn);
         Object dest = (Object)in.readObject();
         return dest;
     }
    
     //浅拷贝
     @SuppressWarnings("unchecked")
	public void copyByAdd(List src,List dest){     
         for (int i = 0 ; i< src.size() ;i++) {//jdk 1.4
             Object obj = src.get(i);
             dest.add(obj);
         }
        
     }    
     //添加引用
     @SuppressWarnings("unchecked")
	public void evalByAdd(List src,List dest){
         dest.add(src);
     }
     //直接引用
     public List evalByRef(List src){
         return src;
     }    
     
     /** *//**
      * @param args
      */
//     public static void main(String[] args) {

//         DeepCopy dc = new DeepCopy();
//         /** *//***********test#1*******************/
//         //dc.copy(srcList,destList); 
//        
//         /** *//***********test#2*******************/
//         /**//*
//         try {
//             destList = dc.copyBySerialize(srcList);
//         } catch (IOException e) {
//             e.printStackTrace();
//         } catch (ClassNotFoundException e) {            
//             e.printStackTrace();
//         }
//         */
//         /** *//***********test#3*******************/
//         dc.copyByAdd(srcList,destList);
//         ((List)srcList.get(0)).remove(0);
//         /** *//***********test#4*******************/
//         /**//*
//         destList = dc.evalByRef(srcList);        
//         */
//         srcList.remove(1);
//         dc.printList(destList);

//     }
//     
//     private void printList(List destList) {
//         //for (Object obj : destList) {//jdk 1.5 以上版本
//         for (int i = 0 ; i< destList.size() ;i++) {//jdk 1.4 
//             Object obj = destList.get(i);
//             if (obj instanceof List){
//                 List listObj = (List)obj;
//                     printList((List)listObj);
//             }else{
//                 System.out.println(obj.toString());
//             }
//         }
//        
//     }
}
