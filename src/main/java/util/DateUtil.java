package util;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
/**
 * 处理日期问题的类
 * @author ylsoft
 *
 */
public class DateUtil{
	

    public static final String C_DATE_DIVISION = "-";
    public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";
    public static final String C_DATA_PATTON_YYYYMMDD = "yyyyMMdd";
    public static final String C_DATA_PATTON_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String C_TIME_PATTON_HHMMSS = "HH:mm:ss";
    public static final int C_ONE_SECOND = 1000;
    public static final int C_ONE_MINUTE = 60000;
    public static final int C_ONE_HOUR = 0x36ee80;
    public static final long C_ONE_DAY = 0x5265c00L;
    public DateUtil(){ }
    public static String getCurrentYear(){return  format(new Date(), "yyyy");}//返回当前年格式:yyyy
    public static String getCurrentMonth(){return  format(new Date(), "MM");}  //返回当前月。格式:MM
    public static String getCurrentDay(){return  format(new Date(), "dd");} //返回当前天。格式:dd
    
    public static String getCurrentHour(){return  format(new Date(), "HH");} //返回当前天。格式:dd
    public static String getCurrentMin(){return  format(new Date(), "mm");} //返回当前天。格式:dd
    public static String getCurrentSecond(){return  format(new Date(), "ss");} //返回当前天。格式:dd
    
    
    public static String getYear(String DateTime){return format(parseDate("yyyy-MM-dd", DateTime), "yyyy");} //返回日期字符串中的年。格式:yyyy
    public static String getMonth(String DateTime){return format(parseDate("yyyy-MM-dd", DateTime), "MM");}  //返回日期字符串中的月。格式:MM
    
    
    //返回日期字符串中的月。格式:IsIncluede0为1位时前面是否包含0
    public static String getMonth(String DateTime,boolean IsIncluede0 ){
    	String month=format(parseDate("yyyy-MM-dd", DateTime), "MM");
    	if(!IsIncluede0)month=String.valueOf(Integer.parseInt(month));
    	return month;
    }
    public static String getDay(String DateTime){return format(parseDate("yyyy-MM-dd", DateTime), "dd");}//返回日期字符串中的天。
    public static String  DateStr(String DateTime){return  format(parseDate("yyyy-MM-dd", DateTime), "yyyy-MM-dd");} //返回日期字符串。格式:yyyy-MM-dd
    public static String  getCurrentTimeStr(){return  format(new Date(), "HH:mm:ss");}	   //返回当前时间。格式:hh:mm:ss
    public static String  getCurrentTime_hhmmss(){return  format(new Date(), "HHmmss");} //返回当前时间。格式:hh:mm:ss	 
    public static String  getCurrentDateTimeStr(){return format(new Date(), "yyyy-MM-dd HH:mm:ss");}	 //返回当前时间。格式:yyyy-MM-dd hh:mm:ss
    
    //返回当前时间字符串。格式:yyyyMMddhhmmss+4为随机数
    public static String  getCurrentDateTimeKeyStr(){
			Random random = new Random();
			String key=format(new Date(), "yyyyMMddHHmmss");
				   key+=String.valueOf(random.nextInt(10));
				   key+=String.valueOf(random.nextInt(10));
				   key+=String.valueOf(random.nextInt(10));
				   key+=String.valueOf(random.nextInt(10));
			return key;
	}	
    
  //返回当前时间字符串。格式:yyyyMMddhhmmss+4为随机数
    public static String  getCurrentDateTimeKeyStr2(){
			Random random = new Random();
			String key=format(new Date(), "yyMMddHHmmss");
				   key+=String.valueOf(random.nextInt(10));
				   key+=String.valueOf(random.nextInt(10));
				   key+=String.valueOf(random.nextInt(10));
				   key+=String.valueOf(random.nextInt(10));
			return key;
	}	
  //返回当前时间毫秒数字符串。格式:yyyyMMddhhmmss+7为随机数
    public static String  getCurrentDateTimeKeyHMStr(){
    		Random random = new Random();
    		String key=format(new Date(), "yyyyMMddHHmmssSSS");
    			   key+=String.valueOf(random.nextInt(10));
    			   key+=String.valueOf(random.nextInt(10));
    			   key+=String.valueOf(random.nextInt(10));
    			   key+=String.valueOf(random.nextInt(10));
    			   key+=String.valueOf(random.nextInt(10));
    			   key+=String.valueOf(random.nextInt(10));
    			   key+=String.valueOf(random.nextInt(10));
    		return key;
    }
    //返回日期类型的当前日期
    public static Date getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        return currDate;
    }
    //返回日期类型的当前日期。格式:yyyy-MM-dd
    public static String getCurrentDateStr(){
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        return format(currDate);
    }
    //返回当前日期。strFormat为自定义格式。
    public static String getCurrentDateStr(String strFormat){
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        return format(currDate, strFormat);
    }
    //返回当前月第一天
    public static String getFirstDayOfMonth(Date date){
    	String currentMonthFirst=format(date, "yyyy-MM-")+"01";
    	return currentMonthFirst;
    }
    //返回当前月最后一天
    public static String getLastDayOfMonth(Date date) {  
    	    Calendar calendar = Calendar.getInstance();   
    	    calendar.setTime(date);   
            calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));  
            
            String lastDayOfMonth=format(addDays(calendar.getTime(),-1), "yyyy-MM-dd");
            return lastDayOfMonth;   
    }   
    public static Date parseDate(String dateValue){return parseDate("yyyy-MM-dd", dateValue);}//把字符串日期格式转化为日期类型。
    public static Date parseDate2(String dateValue){ return parseDate("yyyy-MM-dd HH:mm:ss", dateValue);}//把字符串日期格式转化为日期类型。
    public static Date parseDateTime(String dateValue){return parseDate("yyyy-MM-dd HH:mm:ss", dateValue);}//把字符串日期格式转化为日期类型。
  //格式化时间
    public static Date parseDate(String strFormat, String dateValue){
        if(dateValue == null){return null;}
        if(strFormat == null){strFormat = "yyyy-MM-dd HH:mm:ss";}
        SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
        Date newDate = null;
        try{
            newDate = dateFormat.parse(dateValue);
        }catch(ParseException pe){
            newDate = null;
        }
        return newDate;
    }
    public static String format(Date aTs_Datetime){return format(aTs_Datetime, "yyyy-MM-dd");}//格式化时间
    public static String formatTime(Date aTs_Datetime){return format(aTs_Datetime, "yyyy-MM-dd HH:mm:ss");}//格式化时间
  //格式化时间
    public static String format(Date aTs_Datetime, String as_Pattern){
        if(aTs_Datetime == null || as_Pattern == null){
        	return null;
        } else{
            SimpleDateFormat dateFromat = new SimpleDateFormat();
            dateFromat.applyPattern(as_Pattern);
            return dateFromat.format(aTs_Datetime);
        }
    }
//格式化时间
    public static String formatTime(Date aTs_Datetime, String as_Format){
        if(aTs_Datetime == null || as_Format == null){
            return null;
        } else{
            SimpleDateFormat dateFromat = new SimpleDateFormat();
            dateFromat.applyPattern(as_Format);
            return dateFromat.format(aTs_Datetime);
        }
    }
   //得到时间
    public static String getFormatTime(Date dateTime){return formatTime(dateTime, "HH:mm:ss");}

   
//格式化日期字符串
    public static String format(Timestamp aTs_Datetime, String as_Pattern){
        if(aTs_Datetime == null || as_Pattern == null){
            return null;
        } else{
            SimpleDateFormat dateFromat = new SimpleDateFormat();
            dateFromat.applyPattern(as_Pattern);
            return dateFromat.format(aTs_Datetime);
        }
    }
    //当前月天数
    public static int getDayNum(String dateValue){
    	Date date=parseDate(dateValue);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    //多少分钟之后的时间
    public static Date addMinus(Date date,int min){
    	 Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         cal.add(Calendar.MINUTE, min);
         return cal.getTime(); 
    }
   //多少天之后的日期
    public static Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, days);
        return cal.getTime();
    }
    //多少分秒之后的时间
    public static Date addSeconds(Date date,int second){
    	 Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         cal.add(Calendar.SECOND, second);
         return cal.getTime(); 
    }
//返回两个日期之间的天数
    public static int daysBetween(Date date1, Date date2){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 0x5265c00L;
        return Integer.parseInt(String.valueOf(between_days));
    }
    
    /**
     * 返回两个日期之间的整数天
     * 2015-11-01 12:12:00和2015-11-02 00:00:00相差为1
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetweenForDay(Date startDate, Date endDate){
    	startDate = parseDate(formatTime(startDate, "yyyy-MM-dd"));
    	endDate = parseDate(DateUtil.formatTime(endDate,"yyyy-MM-dd"));
    	return daysBetween(startDate,endDate);
    }
    
    //返回两个日期时间段
    public static String timeBetween(Date date1, Date date2){
    	if(date1==null||date2==null) return "";
       String resultStr="";
       long miaocount=0;
       long mincount=0;
       long hourCount=0;
       long dayCount=0;
       long DAY = 24L * 60L * 60L * 1000L;
       long HOUR=60L * 60L * 1000L;
       long MIN=60L * 1000L;
       long MIAO=1000L;
       long miaolen=date2.getTime()-date1.getTime();
       if(miaolen>60)miaocount=(miaolen / MIAO)%60;
           mincount=(miaolen / MIN)%60;
           hourCount=(miaolen / HOUR)%24;
           dayCount=miaolen / DAY;
       resultStr=dayCount+"天"+hourCount+"小时"+mincount+"分"+miaocount+"秒";
       if(dayCount==0)
    	   resultStr=hourCount+"小时"+mincount+"分"+miaocount+"秒";
       if(dayCount==0&&hourCount==0)
    	   resultStr=mincount+"分"+miaocount+"秒";
       if(dayCount==0&&hourCount==0&&mincount==0)
    	   resultStr=miaocount+"秒";
        return resultStr;
    }
    public static long getRelativeDays(Date date){
        Date relativeDate = parseDate("yyyy-MM-dd", "1977-12-01");
        return (long)daysBetween(relativeDate, date);
    }

    public static Date getDateBeforTwelveMonth(){
        String date = "";
        Calendar cla = Calendar.getInstance();
        cla.setTime(getCurrentDate());
        int year = cla.get(1) - 1;
        int month = cla.get(2) + 1;
        if(month > 9){
            date = (new StringBuilder(String.valueOf(String.valueOf(year)))).append("-").append(String.valueOf(month)).append("-").append("01").toString();
        } else{
            date = (new StringBuilder(String.valueOf(String.valueOf(year)))).append("-").append("0").append(String.valueOf(month)).append("-").append("01").toString();
        }
        Date dateBefore = parseDate(date);
        return dateBefore;
    }

    public static Date addDate(String date){
        if(date == null){
            return null;
        } else{
            Date tempDate = parseDate("yyyy-MM-dd", date);
            String year = format(tempDate, "yyyy");
            String month = format(tempDate, "MM");
            String day = format(tempDate, "dd");
            GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
            calendar.add(5, 1);
            return calendar.getTime();
        }
    }

    public static String operateDateTime(Object obj, char tp, int amount, String format) {
		Date date = new Date();
		if (obj != null) {
			if (obj instanceof Date) {
				date = (Date) obj;
			}
			if (obj instanceof String) {
				date = parseToDate(obj.toString());
			}
		}

		int field = Calendar.DAY_OF_MONTH;

		// 操作类型 'y' : ?? 'M' : ?? 'd' : ?? 'H' : 小时; 'm' : 分钟; 's' : ??
		switch (tp) {
		case 'y':field = Calendar.YEAR;break;
		case 'M':field = Calendar.MONTH;break;
		case 'd':field = Calendar.DAY_OF_MONTH;break;
		case 'H':field = Calendar.HOUR_OF_DAY;break;
		case 'm':field = Calendar.MINUTE;break;
		case 's':field = Calendar.SECOND;break;
		default:break;
		}

		Calendar riqi = new GregorianCalendar();
		riqi.setTime(date);
		riqi.add(field, amount);
		if (format == null || format.equals("")) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		return (new SimpleDateFormat(format)).format(riqi.getTime());
	}
    public static Date parseToDate(String source) {
		Date date = null;
		if (!"".equals(Function.ClearTrim(source))) {
			try {String format = "yyyy-MM-dd";
				if (source.matches("^\\d{4}\\d{2}\\d{2}$")) {format = "yyyyMMdd";
				} else if (source.matches("^\\d{4}-\\d{2}-\\d{2}$")) {format = "yyyy-MM-dd";
				} else if (source.matches("^\\d{2}\\d{2}\\d{2}$")) {format = "HHmmss";
				} else if (source.matches("^\\d{2}:\\d{2}:\\d{2}$")) {format = "HH:mm:ss";
				} else if (source.matches("^\\d{4}\\d{2}\\d{2}\\d{2}\\d{2}\\d{2}$")) {format = "yyyyMMddHHmmss";
				} else if (source.matches("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$")) {format = "yyyy-MM-dd HH:mm:ss";
				} else {source = format(null, format);}
				date = (new SimpleDateFormat(format)).parse(source);
			} catch (ParseException e) {
				date = new Date();
			}
		}
		return date;
	}

    /**
     * 返回
     * @param startTimeStr
     * @param endTime
     * @return
     */
    public static long secondsBetween(String startTimeStr,Date endTime) {
        Date startTime = parseDateTime(startTimeStr);
    	
        long seconds = (endTime.getTime() - startTime.getTime())/1000;  
    	
    	return seconds;
    }
    public static String getSpecifiedDayBefore() {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); 
	  	  Calendar c = Calendar.getInstance(); 
	  	  Date date=new Date(); 
	  	  c.setTime(date); 
	  	  int day=c.get(Calendar.DATE); 
	  	  c.set(Calendar.DATE,day-1); 
	  	  String dayBefore=new SimpleDateFormat("yyyyMMdd").format(c.getTime()); 
	  	  return dayBefore;
	}

}

