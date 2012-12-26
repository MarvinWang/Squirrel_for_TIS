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
public class DataSourceLoader {

	private static DataSourceLoader dataLoader;
	
	private GlobalModel globalModel;
	
	private DataSourceLoader(){
	}
	
	public static DataSourceLoader getInstance(){
		if(dataLoader == null)
			dataLoader = new DataSourceLoader();
		return dataLoader;
	}
	
	public GlobalModel loadData() throws IOException{
		globalModel = new GlobalModel();
		File datasourceFile;
		try {
			datasourceFile = fetchDataSourceFile();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException();
		}
		buildGlobalModel(datasourceFile, globalModel);
		return globalModel;
	}

	private File fetchDataSourceFile() throws IOException{
		Bundle bundle = Platform.getBundle(TISLocatorPlugin.PLUGIN_ID);
		URL datasourceURL = FileLocator.find(bundle, new Path("/dataware_house/DataSource.xml"), null);
		URL fileURL = FileLocator.toFileURL(datasourceURL);
		String path = fileURL.getPath();
		File datasourceFile = new File(path);
		return datasourceFile;
	}
	
	/**
	 * Builds a global model for code locator, this global model is used to operate in memory.
	 * @param datasourceFile
	 * @param globalModel
	 */
	protected void buildGlobalModel(File datasourceFile, GlobalModel globalModel){
		
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
