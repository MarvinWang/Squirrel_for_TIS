/**
 * 
 */
package marvin.squirrel.tis.locator.data.handler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import marvin.squirrel.tis.locator.TISLocatorPlugin;
import marvin.squirrel.tis.locator.data.model.GlobalModel;
import marvin.squirrel.tis.locator.data.model.TClassModel;
import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.data.model.TVersionInfoModel;
import marvin.squirrel.tis.locator.exception.DataSourceException;
import marvin.squirrel.tis.locator.i18n.Messages;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
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
	
	/**
	 * Loads data from data source file to build a global mode <code>GlobalModel</code>. When the viewer is opened, 
	 * then this method is invoked. If the memory mode is changed, before invoking this method, you should invoke 
	 * {@link #pushModelToFile()} first.
	 * @return
	 * @throws IOException If file "DataSource.xml" can not find.
	 */
	public GlobalModel loadData() throws DataSourceException{
		globalModel = new GlobalModel();
		File datasourceFile = fetchDataSourceFile();
		buildGlobalModel(datasourceFile, globalModel);
		return globalModel;
	}

	public void pushModelToFile(){}
	
	/**
	 * Fetches the data source file from the current plugin directory.
	 * @return
	 * @throws DataSourceException 
	 */
	private File fetchDataSourceFile() throws DataSourceException {
		File datasourceFile = null;
		Bundle bundle = Platform.getBundle(TISLocatorPlugin.PLUGIN_ID);
		URL datasourceURL = FileLocator.find(bundle, new Path(Messages.getString("DataSourceLoader.datasource.file")), null);
		
		try {
			URL fileURL = FileLocator.toFileURL(datasourceURL);
			String path = fileURL.getPath();
			datasourceFile = new File(path);
		} catch (IOException e) {
			throw new DataSourceException(Messages.getString("DataSourceLoader.exception.notfound.sourcefile"),e);
		}
		return datasourceFile;
	}
	
	/**
	 * Builds a global model for code locator, this global model is used to operate in memory.
	 * @param datasourceFile
	 * @param globalModel
	 * @throws DataSourceException 
	 */
	protected void buildGlobalModel(File datasourceFile, GlobalModel globalModel) throws DataSourceException{
		Element root = getDataSourceRootElement(datasourceFile);
		Iterator<Element> children = root.elementIterator(Messages.getString("DataSource.tag.function"));
		while(children.hasNext()){
			Element funcEle = (Element)children.next();
			Attribute funcEleAttr = funcEle.attribute(Messages.getString("DataSource.tag.function.attr.name"));
//			If the function name is not empty, then create a function model.
			if(!"".equals(funcEleAttr.getValue())){
				TFunctionModel functionModel = new TFunctionModel(funcEleAttr.getValue());
				globalModel.addFunctionModel(functionModel);
				functionModel.setType(funcEle.attributeValue(Messages.getString("DataSource.tag.function.attr.type")));
				functionModel.setProduct(funcEle.attributeValue(Messages.getString("DataSource.tag.function.attr.product")));
				
//				Build class info model
				buildClassInfoModel(funcEle, functionModel);
				
//				Set description
				Element descEle = funcEle.element(Messages.getString("DataSource.tag.function.desc"));
				functionModel.setDesc(descEle.getText());
				
//				Build version info model
				buildVersionInfoModel(funcEle,functionModel);
			}
		}
	}
	
	protected void buildClassInfoModel(Element functionEle, TFunctionModel functionModel){
		Element classInfoEle = functionEle.element(Messages.getString("DataSource.tag.classInfo"));
		String className = classInfoEle.attributeValue(Messages.getString("DataSource.tag.classInfo.attr.name"));
		TClassModel classModel = new TClassModel(className);
		functionModel.setClassModel(classModel);
		Element packageEle = classInfoEle.element(Messages.getString("DataSource.tag.classInfo.package"));
		Element pluginEle = classInfoEle.element(Messages.getString("DataSource.tag.classInfo.plugin"));
		Element methodEle = classInfoEle.element(Messages.getString("DataSource.tag.classInfo.method"));
		
		classModel.setPackageStr(packageEle.getText());
		classModel.setMethod(methodEle.getText());
		classModel.setPluginName(pluginEle.getText());
	}
	
	protected void buildVersionInfoModel(Element functionEle, TFunctionModel functionModel){
		Element versionInfoEle = functionEle.element(Messages.getString("DataSource.tag.function.versionInfo"));
		TVersionInfoModel versionInfoModel = new TVersionInfoModel();
		functionModel.setVersionInfoModel(versionInfoModel);
		String urlValue = versionInfoEle.attributeValue(Messages.getString("DataSource.tag.function.versionInfo.attr.url"));
		versionInfoModel.setSvnDir(urlValue);
		Element versionEle = versionInfoEle.element(Messages.getString("DataSource.tag.function.versionInfo.version"));
		versionInfoModel.setVersion(versionEle.getText());
	}
	
	/**
	 * Gets the root element of document by reading data source file. Note that the file is red by dom4j.
	 * @param datasourceFile
	 * @return
	 * @throws DataSourceException
	 */
	protected Element getDataSourceRootElement(File datasourceFile) throws DataSourceException{
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(datasourceFile);
			return document.getRootElement();
		} catch (DocumentException e) {
			throw new DataSourceException(Messages.getString("DataSourceLoader.exception.notfound.sourcefile"),e);
		}
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
