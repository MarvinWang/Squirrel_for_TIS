/**
 * 
 */
package marvin.squirrel.tis.locator.data.handler;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import marvin.squirrel.tis.locator.TISLocatorPlugin;
import marvin.squirrel.tis.locator.exception.TDataSourceException;
import marvin.squirrel.tis.locator.i18n.Messages;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * @author Marvin Wang
 * @date 2013-1-2
 * @email mwang@talend.com
 */
public class TDataSourceManager {

	/**
	 * Fetches the data source file from the current plugin directory.
	 * @return
	 * @throws TDataSourceException 
	 */
	public static File fetchDataSourceFile() throws TDataSourceException {
		File datasourceFile = null;
		Bundle bundle = Platform.getBundle(TISLocatorPlugin.PLUGIN_ID);
		URL datasourceURL = FileLocator.find(bundle, new Path(Messages.getString("DataSourceLoader.datasource.file")), null);
		
		try {
			URL fileURL = FileLocator.toFileURL(datasourceURL);
			String path = fileURL.getPath();
			datasourceFile = new File(path);
		} catch (IOException e) {
			throw new TDataSourceException(Messages.getString("DataSourceLoader.exception.notfound.sourcefile"),e);
		}
		return datasourceFile;
	}
	
	public static File fetchDataSourceTmpFile() throws TDataSourceException {
		File datasourceFile = null;
		Bundle bundle = Platform.getBundle(TISLocatorPlugin.PLUGIN_ID);
		URL datasourceURL = FileLocator.find(bundle, new Path(Messages.getString("DataSourceLoader.datasource.tmpFile")), null);
		
		try {
			URL fileURL = FileLocator.toFileURL(datasourceURL);
			String path = fileURL.getPath();
			datasourceFile = new File(path);
		} catch (IOException e) {
			throw new TDataSourceException(Messages.getString("DataSourceLoader.exception.notfound.sourceTmpFile"),e);
		}
		return datasourceFile;
	}
}
