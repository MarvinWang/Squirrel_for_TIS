/**
 * 
 */
package marvin.squirrel.tis.locator.utils;

import java.util.ArrayList;
import java.util.List;

import marvin.squirrel.tis.locator.enums.TFunctionTypeEnum;

/**
 * @author Marvin Wang
 * @date 2013-1-3
 * @email mwang@talend.com
 */
public class TFunctionTypeUtils {

	/**
	 * Gets all function types from {@link TFunctionTypeEnum}.
	 * @return
	 */
	public static String[] fetchAllDisplayNames(){
		List<String> all = new ArrayList<String>();
		TFunctionTypeEnum[] allTypes = TFunctionTypeEnum.values();
		for(TFunctionTypeEnum type : allTypes){
			all.add(type.getDisplayName());
		}
		return all.toArray(new String[all.size()]);
	}
	
	/**
	 * 
	 * @param displayType
	 * @return
	 */
	public static TFunctionTypeEnum getFunctionType(String displayType){
		TFunctionTypeEnum[] allTypes = TFunctionTypeEnum.values();
		for(TFunctionTypeEnum type : allTypes){
			if(type.getDisplayName().equals(displayType)){
				return type;
			}
		}
		return null;
	}
	
	public static int indexFunctionType(TFunctionTypeEnum functionType){
		TFunctionTypeEnum[] allTypes = TFunctionTypeEnum.values();
		int index = -1;
		for(TFunctionTypeEnum type : allTypes){
			++index;
			if(type.equals(functionType)){
				return index;
			}
		}
		return 0;
	}
}
