package per.hss.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    //将字符串转换为日期
    public static Date stringToDate(String string,String format)
    {
System.out.println("");
        Date date=null;
        //字符串为空
        if(StringUtil.isEmpty(string))
        {
            return null;
        }
        else{
            DateFormat sdf=new SimpleDateFormat(format);
            try {
                date=sdf.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    //将日期转换为字符串
    public static  String dateToString(Date date,String format)
    {
        String result="";
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        if(date!=null)
        {
            result=sdf.format(date);
        }
        return result;
    }

    //获取当前日期
    public static String getCurrentDateStr()
    {
        Date date=new Date();
        DateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }
}
