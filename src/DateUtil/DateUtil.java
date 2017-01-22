package DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class DateUtil {
	
	/**
	 * 日期格式
	 */
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 时间格式
	 */
	public final static String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
	
	/**
	 * 
	 * @Title: date2String @Description: date2String @param date @param
	 *         pattern @return @return String @throws
	 */
	public static String date2String(Date date, String pattern) {
		SimpleDateFormat stf = new SimpleDateFormat(pattern);
		String returnDate = null;
		try {
			returnDate = stf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnDate;
	}

	/**
	 * 
	 * @Title: parseDate @Description: string2Date @param date @param
	 *         pattern @return @return Date @throws
	 */
	public static Date parseDate(String date, String pattern) {
		SimpleDateFormat stf = new SimpleDateFormat(pattern);
		Date returnDate = null;
		try {
			returnDate = stf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}

	/**
	 * 
	 * @Title: getCurrdays @Description: 得到前多少天的日期 @param scale @return @return
	 *         Date @throws
	 */
	public static Date getCurrdays(int scale) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -scale);
		return cal.getTime();
	}

	/**
	 * 
	 * @Title: getCurrdays @Description: 得到后多少天的日期 @param scale @return @return
	 *         Date @throws
	 */
	public static Date getAfterCurrdays(int scale) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, scale);
		return cal.getTime();
	}

	/**
	 * 得到某年某周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.setFirstDayOfWeek(Calendar.MONDAY);

		return c.getTime();
	}

	/**
	 * 得到某年某周的最后一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);

		return c.getTime();
	}

	/**
	 * 取得指定日期的所处星期的最后一天
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处星期的最后一天
	 */
	public static Date getLastDayOfWeek(Date date) {
		/**
		 * 详细设计： 1.如果date是星期日，则加6天 2.如果date是星期一，则加5天 3.如果date是星期二，则加4天
		 * 4.如果date是星期三，则加3天 5.如果date是星期四，则加2天 6.如果date是星期五，则加1天
		 * 7.如果date是星期六，则加0天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 6);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, 5);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, 4);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, 2);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, 1);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 0);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 取得指定日期的所处星期的第一天
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处星期的第一天
	 */
	public static Date getFirstDayOfWeek(Date date) {
		/**
		 * 详细设计： 1.如果date是星期日，则减0天 2.如果date是星期一，则减1天 3.如果date是星期二，则减2天
		 * 4.如果date是星期三，则减3天 5.如果date是星期四，则减4天 6.如果date是星期五，则减5天
		 * 7.如果date是星期六，则减6天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 0);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, -1);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, -2);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, -4);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, -5);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, -6);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 取得指定日期是工作日还是休息日(周一到周五是工作日, 周六周日是休息)
	 * 
	 * @param date,日期
	 * 
	 * @return 1:工作日, 0:休息日
	 */
	public static int isWorkDay(Date date) {
		int isWorkDay = 1;
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			isWorkDay = 0;
			break;
		case (Calendar.MONDAY):
			break;
		case (Calendar.TUESDAY):
			break;
		case (Calendar.WEDNESDAY):
			break;
		case (Calendar.THURSDAY):
			break;
		case (Calendar.FRIDAY):
			break;
		case (Calendar.SATURDAY):
			isWorkDay = 0;
			break;
		}
		return isWorkDay;
	}

	/**
	 * 取得指定日期的一天开始时间
	 * 
	 * @param date,日期
	 * @param pattern,返回格式
	 * 
	 * @return String 一天的开始时间
	 */
	public static String getStarttimeOfDay(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String start = formater.format(date) + " 00:00:00";
		return start;
	}

	/**
	 * 取得指定日期的一天结束时间
	 * 
	 * @param date,日期
	 * @param pattern,返回格式
	 * 
	 * @return String 一天的结束时间
	 */
	public static String getEndtimeOfDay(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String end = formater.format(date) + " 23:59:59";
		return end;
	}

	/**
	 * 判断时间是否在指定范围内
	 * 
	 * @param tStart
	 * 			@param tEnd @param t @return @throws ParseException @return
	 *            boolean @throws
	 */
	public static boolean isInZone(long tStart, long tEnd, long t) throws ParseException {
		return tStart <= t && t <= tEnd;
	}

	public static boolean isInZone(Date tStart, Date tEnd, Date t) {
		return tStart.getTime() <= t.getTime() && t.getTime() <= tEnd.getTime();
	}

	/**
	 * 获取指定时间的long值
	 * 
	 * @param timeStr
	 * 			@return @throws ParseException @return boolean @throws
	 */
	public static long getLong(String timeStr, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(timeStr).getTime();
	}

	/**
	 * @throws ParseException
	 * 
	 * @Title: daysBetween @Description: 日期相差天数 @param smdate @param
	 *         bdate @return @throws ParseException @return int @throws
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 
	 * @Title: daysBetween @Description: @param smdate @param
	 *         bdate @return @throws ParseException @return int @throws
	 */
	public static int daysBetween(String smdate, String bdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			return 0;
		}
	}

	public static double hourBetween(Date startTime, Date endTime) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(startTime);
			long time1 = cal.getTimeInMillis();
			cal.setTime(endTime);
			long time2 = cal.getTimeInMillis();
			double sub = Double.valueOf(time2 - time1);
			double between_days = sub / (1000 * 60 * 60);
			return between_days;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取当前日期周几 （1周日 2周一 3周二 4周三 5周四 6周五 7周六）
	 */
	public static int getDayOfWeek(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取当前日期周几 （1周日 2周一 3周二 4周三 5周四 6周五 7周六）
	 */
	public static int getDayOfWeek(String dateStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateStr);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 
	 * @Title: monthOfDate @Description: 获取当前日期月份 @param date @return @return
	 *         int @throws
	 */
	public static int monthOfDate(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.MONTH) + 1;
	}

	public static int getYear(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @Title: monthAdd @Description: 获取当前日期前后几个月 @param date @param
	 *         offset @return @return Date @throws
	 */
	public static Date monthAdd(Date date, int offset) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.MONTH, offset);
		date = cl.getTime();
		return date;
	}

	public static String transferLongToDate(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}

	/**
	 * 
	 * @Title: getFirstDayOfMonth @Description: 获取指定月份的第一天 @param date
	 *         指定月份 @param offset 指定月份前后几个月（负数代表前几个月 整数代表后几个月） @return @return
	 *         Date @throws
	 */
	public static Date getFirstDayOfMonth(Date date, int offset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		cal.set(Calendar.YEAR, year);// 设置年份
		cal.set(Calendar.MONTH, month + offset);// 设置月份
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);// 获取某月最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);// 设置日历中月份的最小天数
		return cal.getTime();
	}

	/**
	 * 
	 * @Title: getLastDayOfMonth @Description: 获取指定月份的最后天 @param date
	 *         指定月份 @param offset 指定月份前后几个月（负数代表前几个月 整数代表后几个月） @return @return
	 *         Date @throws
	 */
	public static Date getLastDayOfMonth(Date date, int offset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		cal.set(Calendar.YEAR, year);// 设置年份
		cal.set(Calendar.MONTH, month + offset);// 设置月份
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 获取某月最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);// 设置日历中月份的最大天数
		return cal.getTime();
	}

	/**
	 * 
	 * @Title: getLastDayOfWeek @Description: 获取指定日期前几周的最后一天 @param date
	 *         指定日期 @param offset（负数代表前几个周 整数代表后几个周） @return @return
	 *         Date @throws
	 */
	public static Date getLastDayOfWeek(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int week = calendar.get(Calendar.WEEK_OF_YEAR) - 1;

		week = week + offset;
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);
		Calendar cal = (Calendar) calendar.clone();
		cal.add(Calendar.DATE, week * 7);
		// return DateUtils.addDays(getLastDayOfWeek(cal.getTime()), 1);
		return new Date();
	}

	/**
	 * 
	 * @Title: getFirstDayOfWeek @Description: 获取指定日期前几周的第一天 @param date
	 *         指定日期 @param offset（负数代表前几个周 整数代表后几个周） @return @return
	 *         Date @throws
	 */
	public static Date getFirstDayOfWeek(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int week = calendar.get(Calendar.WEEK_OF_YEAR) - 1;

		week = week + offset;
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);
		Calendar cal = (Calendar) calendar.clone();
		cal.add(Calendar.DATE, week * 7);
		// return DateUtils.addDays(getFirstDayOfWeek(cal.getTime()), 1);
		return new Date();
	}

	/**
	 * 
	 * @Title: compareDate @Description: 判断时间是否在开始结束时间之间 @param start @param
	 *         end @param date @return @return boolean @throws
	 */
	public static boolean compareDate1(Date start, Date end, Date date) {
		if (date.getTime() >= start.getTime() && date.getTime() < end.getTime()) {
			return true;
		}
		return false;
	}

	public static boolean compareDate2(Date start, Date end, Date date) {
		if (date.getTime() >= start.getTime() && date.getTime() <= end.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 获取多少天之后/之前的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date, Integer num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, num);
		date = calendar.getTime();
		return date;
	}

	public static Date getNextDay(String date, Integer num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(date, "yyyy-MM-dd HH:mm:ss"));
		calendar.add(Calendar.DAY_OF_MONTH, num);
		return calendar.getTime();
	}

	/**
	 * 获取某段时间都有哪些天
	 * 
	 * @param dBegin
	 * @param dEnd
	 * @return
	 */
	public static List<Date> findDates(Date dBegin, Date dEnd) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}

	/**
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDatesByPeriod(Date date, int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String d = df.format(new Date(date.getTime() + day * 24 * 60 * 60 * 1000));
		return parseDate(d, "yyyy-MM-dd");
	}

	public static String getHourAndMinuteTime(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date, "yyyy-MM-dd HH:mm:ss"));
		int hour = cal.get(Calendar.HOUR_OF_DAY);// 小时
		int minute = cal.get(Calendar.MINUTE);// 分
		String hourString = "";
		String minuteString = "";
		if (hour >= 10) {
			hourString = String.valueOf(hour);
		} else {
			hourString = "0" + String.valueOf(hour);
		}
		if (minute >= 10) {
			minuteString = String.valueOf(minute);
		} else {
			minuteString = "0" + String.valueOf(minute);
		}
		return hourString + ":" + minuteString;

	}

	public static String getHourAndMinuteTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);// 小时
		int minute = cal.get(Calendar.MINUTE);// 分
		String hourString = "";
		String minuteString = "";
		if (hour >= 10) {
			hourString = String.valueOf(hour);
		} else {
			hourString = "0" + String.valueOf(hour);
		}
		if (minute >= 10) {
			minuteString = String.valueOf(minute);
		} else {
			minuteString = "0" + String.valueOf(minute);
		}
		return hourString + ":" + minuteString;

	}

	public static String getMounthAnddayTime(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date, "yyyy-MM-dd hh:mm:ss"));
		int month = cal.get(Calendar.MONTH) + 1;// 获取月份
		int day = cal.get(Calendar.DATE);// 获取日
		return month + "月" + day + "日";

	}

	public static String getMounthAnddayTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;// 获取月份
		int day = cal.get(Calendar.DATE);// 获取日
		return month + "月" + day + "日";

	}

	public static String getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DATE);// 获取日
		return day + "日";
	}

	/**
	 * 
	 * @Title: getDaySub
	 * @author hb
	 * @Description: 日期相减
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDaySub(Date startDate, Date endDate) {
		startDate = parseDate(date2String(startDate, "yyyy-MM-dd"), "yyyy-MM-dd");
		endDate = parseDate(date2String(endDate, "yyyy-MM-dd"), "yyyy-MM-dd");
		return (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取指定日期月份的第一天
	 * 
	 * @param datadate
	 * @return
	 * @throws Exception
	 */
	public static String getMonthFirstDay(String datadate) throws Exception {
		Date date = null;
		String day_first = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		date = format.parse(datadate);

		// 创建日历
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		day_first = format.format(calendar.getTime());
		return day_first;
	}

	/**
	 * 获取指定日期月份的最后一天
	 * 
	 * @param datadate
	 * @return
	 * @throws Exception
	 */
	public static String getMonthLastDay(String datadate) throws Exception {
		Date date = null;
		String day_last = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		date = format.parse(datadate);

		// 创建日历
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1); // 加一个月
		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
		day_last = format.format(calendar.getTime());
		return day_last;
	}

	/**
	 * 获取指定月的前一月（年）或后一月（年）
	 * 
	 * @param dateStr
	 *            日期
	 * @param addYear
	 *            0 ，今年 1， 明年 -1，去年
	 * @param addMonth
	 *            0，本月 1，下个月 -1 上个月
	 * @param addDate
	 *            0，今天 1， 明天 -1，昨天
	 * @return 输入的时期格式为yyyy-MM-dd，输出的日期格式为yyyy-MM-dd
	 * @throws Exception
	 */
	public static String getSpecifiedDay(String dateStr, int addYear, int addMonth, int addDate) throws Exception {
		try {
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			Date sourceDate = sdf.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sourceDate);
			cal.add(Calendar.YEAR, addYear);
			cal.add(Calendar.MONTH, addMonth);
			cal.add(Calendar.DATE, addDate);

			SimpleDateFormat returnSdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			String dateTmp = returnSdf.format(cal.getTime());
			return dateTmp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 获取24个小时list str
	 * 
	 * @return
	 */
	public static List<String> get24Hour() {
		List<String> dateList = new ArrayList<String>();
		dateList.add("00:00");
		dateList.add("01:00");
		dateList.add("02:00");
		dateList.add("03:00");
		dateList.add("04:00");
		dateList.add("05:00");
		dateList.add("06:00");
		dateList.add("07:00");
		dateList.add("08:00");
		dateList.add("09:00");
		dateList.add("10:00");
		dateList.add("11:00");
		dateList.add("12:00");
		dateList.add("13:00");
		dateList.add("14:00");
		dateList.add("15:00");
		dateList.add("16:00");
		dateList.add("17:00");
		dateList.add("18:00");
		dateList.add("19:00");
		dateList.add("20:00");
		dateList.add("21:00");
		dateList.add("22:00");
		dateList.add("23:00");

		return dateList;
	}

	/**
	 * 与今天比判断是否过期
	 * 
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static Boolean isExpire(Date endTime) throws Exception {
		Date today = new Date();
		try {
			if (DateUtil.daysBetween(endTime, today) > 0) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return false;
	}

	/**
	 *  * 根据开始时间和结束时间返回时间段内的时间集合  * @param beginDateStr  * @param endDateStr
	 *  * @return List  
	 * 
	 * @throws ParseException
	 */
	public static List<String> getDatesBetweenTwoDate(String beginDateStr, String endDateStr) throws ParseException {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		// SimpleDateFormat formateMonth = new SimpleDateFormat("MM-dd");
		Date beginDate = formate.parse(beginDateStr);
		Date endDate = formate.parse(endDateStr);
		List<String> lDate = new ArrayList<String>();
		lDate.add(formate.format(beginDate));// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				Date nextDate = cal.getTime();
				lDate.add(formate.format(nextDate));
			} else {
				break;
			}
		}
		lDate.add(formate.format(endDate));// 把结束时间加入集合
		return lDate;
	}

}
