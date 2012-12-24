/**
 * 
 */
package marvin.squirrel.tis.locator.data.handler;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import marvin.squirrel.tis.locator.TISLocatorPlugin;
import marvin.squirrel.tis.locator.data.model.GlobalModel;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

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
		
		Bundle bundle = Platform.getBundle(TISLocatorPlugin.PLUGIN_ID);
		URL datasourceURL = FileLocator.find(bundle, new Path("/dataware_house/DataSource.xml"), null);
		
		try {
			URL fileURL = FileLocator.toFileURL(datasourceURL);
			String path = fileURL.getPath();
			File datasourceFile = new File(path);
			String abPath = datasourceFile.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
