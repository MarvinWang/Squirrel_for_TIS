/**
 * 
 */
package marvin.squirrel.tis.locator.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Marvin Wang
 * @date 2013-1-2
 * @email mwang@talend.com
 */
public class TUniqueIDCreater {
	
	private static long tmpId = 0l;

	private static TUniqueIDCreater creater;
	
	private TUniqueIDCreater(){}
	
//	public static TUniqueIDCreater getInstance(){
//		if(creater == null)
//			creater = new TUniqueIDCreater();
//		return creater;
//	}
	
	/**
	 * 
	 * @return
	 * TODO This method needs to enhance, add "Synchronize".
	 */
	public static long getUniqueID(){
		long time = 0l;
		String dateStr = new SimpleDateFormat("yyMMddhhmmssSSS").format(new Date()).toString();
		time = Long.valueOf(dateStr) * 10000;
		if(tmpId < time)
			tmpId = time;
		else{
			tmpId = tmpId + 1;
			time = tmpId;
		}
		return time;
	}
	
	public static void main(String[] args){
		long id = TUniqueIDCreater.getUniqueID();
		System.out.println(id);
	}
}
