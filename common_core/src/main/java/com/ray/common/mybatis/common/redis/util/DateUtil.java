package com.ray.common.mybatis.common.redis.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用静态工具类
 * 提供日期处理格式化方法
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/26/2018.
 */
public class DateUtil {
    /**
     * 获取当前日期，可格式化
     * @param format 日期格式化代码
     * @return
     */
    public static String now(String format) {
        if(null == format) format = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }


    /**
     * 获取当前日期
     * @return
     */
    public static Date now() {
        return  new Date();
    }


    /**
     * 字符串到日期
     * @param str_date 日期字符串数据
     * @param format 日期格式化代码
     * @return
     */
    public static Date strToDate(String str_date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    /**
     * 字符串到日期
     * @param str_date 只能格式化yyyy-MM-dd
     * @return
     */
    public static Date strToDate(String str_date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    /**
     * 日期到字符串,默认格式为"yyyy-MM-dd HH:mm:ss"
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return tmp.format(date);
    }

    /**
     * 日期到字符串
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        SimpleDateFormat tmp = new SimpleDateFormat(format);
        return tmp.format(date);
    }
}
