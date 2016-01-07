package nuaa.ggx.pos.collector.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeiboTools {
	

	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static DateFormat ddf = new SimpleDateFormat("MM月dd日 HH:mm");
	public static DateFormat dddf = new SimpleDateFormat("MM-dd HH:mm");
	
	public static String getFormulaDateString(String date0) {
		
		String date;
		
		String regex1 = "今天\\s\\d{2}:\\d{2}";
		String regex2 = "\\d{1,2}分钟";
		String regex3 = "\\d{2}月\\d{2}日\\s\\d{2}:\\d{2}";
		String regex4 = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}";
		
		Pattern p = Pattern.compile(regex1);
		Matcher m = p.matcher(date0);		
		if (m.find()) {
			date = df.format(new Date()).substring(0, 11) + m.group().substring(3);
			return date;
		} 
		
		p = Pattern.compile(regex2);
		m = p.matcher(date0);
		if (m.find()) {
			Date d = new Date();
			long t = d.getTime()-Integer.parseInt(m.group().split("分钟")[0])*60*1000;			
			date = df.format(new Date(t));
			return date;
		}
		
		p = Pattern.compile(regex3);
		m = p.matcher(date0);
		if (m.find()) {
			try {
				date = df.format(new Date()).substring(0,5) + dddf.format(ddf.parse(m.group()));
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return "";
			}
		}
		
		p = Pattern.compile(regex4);
		m = p.matcher(date0);
		if (m.find()) {
			date = m.group();
			return date;
		}
		return "";
	}
	
	public static Long getFormulaDateTime(String date0) throws ParseException {
		
		String date;
		Date d = new Date();
		
		String regex1 = "今天\\s\\d{2}:\\d{2}";
		String regex2 = "\\d{1,2}分钟";
		String regex3 = "\\d{2}月\\d{2}日\\s\\d{2}:\\d{2}";
		String regex4 = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}";
		
		Pattern p = Pattern.compile(regex1);
		Matcher m = p.matcher(date0);		
		if (m.find()) {
			date = df.format(d).substring(0, 11) + m.group().substring(3);
			return df.parse(date).getTime();
		} 
		
		p = Pattern.compile(regex2);
		m = p.matcher(date0);
		if (m.find()) {			
			return d.getTime()-Integer.parseInt(m.group().split("分钟")[0])*60*1000;			
		}
		
		p = Pattern.compile(regex3);
		m = p.matcher(date0);
		if (m.find()) {
			date = df.format(new Date()).substring(0,5) + dddf.format(ddf.parse(m.group()));
			return df.parse(date).getTime();
		}
		
		p = Pattern.compile(regex4);
		m = p.matcher(date0);
		if (m.find()) {
			date = m.group();
			return df.parse(date).getTime();
		}
		return 0L;
	}
}
