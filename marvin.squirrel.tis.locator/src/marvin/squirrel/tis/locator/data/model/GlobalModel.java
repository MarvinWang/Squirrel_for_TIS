/**
 * 
 */
package marvin.squirrel.tis.locator.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marvin
 * @date 2012-12-23
 */
public class GlobalModel {

	private List<TClassModel> classModels;
	
	public GlobalModel(){
		classModels = new ArrayList<TClassModel>();
	}

	/**
	 * @return the classModels
	 */
	public List<TClassModel> getClassModels() {
		return classModels;
	}
	
}
