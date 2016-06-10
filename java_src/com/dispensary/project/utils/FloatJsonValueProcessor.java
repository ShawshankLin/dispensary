package com.dispensary.project.utils;

import java.math.RoundingMode;  
import java.text.NumberFormat;  
import java.util.Locale;  
  
import net.sf.json.JsonConfig;  
import net.sf.json.processors.JsonValueProcessor;  
/** 
 * JSONArray.fromObject转换一个float类型时的转换器
 */  
public class FloatJsonValueProcessor implements JsonValueProcessor {  
  
    public FloatJsonValueProcessor() { }  
  
    /** 
     * 处理数组类型 
     */  
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {  
          
        if (value instanceof float[]) {  
              
            String[] obj = {};  
              
            float[] nums = (float[]) value;  
              
            for (int i = 0; i < nums.length; i++) {  
                obj[i] = roundHalfUp(nums[i], 3);  
            }  
              
            return obj;  
        }  
          
        return value;  
    }  
  
    /** 
     * 处理单个对象 
     */  
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {  
          
        if (value instanceof Float) {  
            return roundHalfUp((Float)value, 3);  
        }  
          
        return value;  
    }  
      
    /** 
     * 四舍五入。 
     *  
     * @param number 数值 
     * @return 舍入后的数值 
     * @see java.text.RoundingMode.HALF_UP 
     */  
    public String roundHalfUp(double number, int frac) {  
        NumberFormat fmt = NumberFormat.getInstance(Locale.CHINA);  
          
        fmt.setMaximumFractionDigits(frac);  
        fmt.setRoundingMode(RoundingMode.HALF_UP);  
          
        return fmt.format(number);  
    }  
}  
