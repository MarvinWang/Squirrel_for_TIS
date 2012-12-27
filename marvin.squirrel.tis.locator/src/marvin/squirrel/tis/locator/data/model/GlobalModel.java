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

	private List<TFunctionModel> functionModels;
	
	public GlobalModel(){
		functionModels = new ArrayList<TFunctionModel>();
	}

	public void addFunctionModel(TFunctionModel functionModel){
		functionModels.add(functionModel);
	}
}
