/**
 * 
 */
package marvin.squirrel.tis.locator.views.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import marvin.squirrel.tis.locator.views.TCodeLocatorView;

/**
 * @author Marvin Wang
 * @date 2012-12-23
 */
public class TCodeLocatorController {
	
	private TCodeLocatorView view;

	public TCodeLocatorController(){}
	
	public TCodeLocatorController(TCodeLocatorView view){
		this.view = view;
	}
	
	public void doLocate(String keyWords){
		List<String> classNameList = new ArrayList<String>();
		Pattern pattern = Pattern.compile(keyWords);
		Matcher matcher = pattern.matcher("");
	    boolean b= matcher.matches();
	    
	    if(b){
	    	
	    	if(classNameList.size() > 0){
	    		view.getClassNameList().setItems(classNameList.toArray(new String[classNameList.size()]));
	    	}
	    }
	}
}
