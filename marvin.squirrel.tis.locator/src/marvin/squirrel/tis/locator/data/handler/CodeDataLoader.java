/**
 * 
 */
package marvin.squirrel.tis.locator.data.handler;

import org.eclipse.core.internal.registry.osgi.Activator;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;

import marvin.squirrel.tis.locator.data.model.GlobalModel;

/**
 * @author Marvin
 * @date 2012-12-23
 */
public class CodeDataLoader {

	private static CodeDataLoader dataLoader;
	
	private GlobalModel globalModel;
	
	private CodeDataLoader(){
	}
	
	public static CodeDataLoader getInstance(){
		if(dataLoader == null)
			dataLoader = new CodeDataLoader();
		return dataLoader;
	}
	
	public GlobalModel loadData(){
		globalModel = new GlobalModel();
		
		
		return globalModel;
	}

	
	
	
	/**
	 * @return the globalModel
	 */
	public GlobalModel getGlobalModel() {
		return globalModel;
	}

	/**
	 * @param globalModel the globalModel to set
	 */
	public void setGlobalModel(GlobalModel globalModel) {
		this.globalModel = globalModel;
	}
	
}
