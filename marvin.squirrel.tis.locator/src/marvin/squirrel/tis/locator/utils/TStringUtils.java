/**
 * 
 */
package marvin.squirrel.tis.locator.utils;

import marvin.squirrel.tis.locator.i18n.Messages;

/**
 * @author Marvin Wang
 * @date 2013-1-5
 * @email mwang@talend.com
 */
public class TStringUtils {
	
	public static final String NEW_FUNCTION_NAME = Messages.getString("FunctionLocatorEditor.title.newFunction");
	
	public static final String NEW_FUNCTION_NAME_LEFT_BRACKET = Messages.getString("FunctionLocatorEditor.title.newFunction.leftBracket");
	
	public static final String NEW_FUNCTION_NAME_RIGHT_BRACKET = Messages.getString("FunctionLocatorEditor.title.newFunction.rightBracket");

	/**
	 * Makes the part name short like "New Function(1)"
	 * @param functionName
	 * @return
	 * @deprecated
	 */
	public static String makePartNameShort(String functionName){
//		int length = 16;
//		if(functionName.startsWith(getNewFunctionNameStartWith()) && functionName.endsWith(getNewFunctionNameEndWith()))
//			return functionName;
//		if(functionName.length() < length){
//			return functionName;
//		}else{
//			String newFunctionName = functionName.substring(0, 16);
//			return newFunctionName + "...";
//		}
		return functionName;
	}
	
	/**
	 * Gets the start with for new function name as "New Function(". Also need to refer to the method 
	 * {@link #getNewFunctionNameEndWith()} to get the end with.
	 * @return
	 */
	public static String getNewFunctionNameStartWith(){
		return NEW_FUNCTION_NAME + NEW_FUNCTION_NAME_LEFT_BRACKET;
	}
	
	/**
	 * Gets the end with for new fucntion name as ")". Refer to this {@link #getNewFunctionNameStartWith()} 
	 * to get the start with.
	 * @return
	 */
	public static String getNewFunctionNameEndWith(){
		return NEW_FUNCTION_NAME_RIGHT_BRACKET;
	}
}
