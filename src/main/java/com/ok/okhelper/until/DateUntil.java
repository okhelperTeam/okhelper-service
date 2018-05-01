package com.ok.okhelper.until;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author: zc
 * Date: 2018/4/28
 * Description:
 */
public class DateUntil {
    /**
     *
     * @param date 当前时间
     * @flag 0 返回yyyy-MM-dd 00:00:00日期<br>
     *       1 返回yyyy-MM-dd 23:59:59日期
     * @return
     */
    public static Date weeHours(Date date, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis()-millisecond);

        if (flag == 0) {
            return cal.getTime();
        } else if (flag == 1) {
            //凌晨23:59:59
            cal.setTimeInMillis(cal.getTimeInMillis()+23*60*60*1000 + 59*60*1000 + 59*1000);
        }
        return cal.getTime();
    }

//    public static Date lastMonth(){
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.MONTH, -1);
////        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
////        String time = format.format(c.getTime());
//
//        //得到一个月最后一天日期(31/30/29/28)
//        int MaxDay=c.getActualMaximum(Calendar.DAY_OF_MONTH);
//        //按你的要求设置时间
//        c.set( c.get(Calendar.YEAR), c.get(Calendar.MONTH), MaxDay, 23, 59, 59);
//        //按格式输出
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
//        String gtime = sdf.format(c.getTime()); //上月最后一天
//    }
}
