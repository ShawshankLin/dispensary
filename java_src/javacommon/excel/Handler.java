/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javacommon.excel;

/**
 * Excel处理接口
 * @author xc
 */
public interface Handler {
   /**
    * 处理Excel文件
    * @param reader 
    */
   public void handle(Reader reader);
   
}