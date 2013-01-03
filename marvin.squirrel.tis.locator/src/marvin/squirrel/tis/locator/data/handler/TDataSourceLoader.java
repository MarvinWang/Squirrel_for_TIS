/**
 * 
 */
package marvin.squirrel.tis.locator.data.handler;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import marvin.squirrel.tis.locator.data.model.TClassModel;
import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.data.model.TGlobalModel;
import marvin.squirrel.tis.locator.data.model.TVersionInfoModel;
import marvin.squirrel.tis.locator.exception.TDataSourceException;
import marvin.squirrel.tis.locator.i18n.Messages;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Marvin
 * @date 2012-12-23
 */
public class TDataSourceLoader {

	private static TDataSourceLoader dataLoader;
	
	private TGlobalModel globalModel;
	
	private TDataSourceLoader(){
	}
	
	public static TDataSourceLoader getInstance(){
		if(dataLoader == null)
			dataLoader = new TDataSourceLoader();
		return dataLoader;
	}
	
	/**
	 * Loads data from data source file to build a global mode <code>GlobalModel</code>. When the viewer is opened, 
	 * then this method is invoked. If the memory mode is changed, before invoking this method, you should invoke 
	 * {@link TDataSourceWriter#pushModelToFile()} first.
	 * @return
	 * @throws IOException If file "DataSource.xml" can not find.
	 */
	public TGlobalModel loadDataFromFile() throws TDataSourceException{
		globalModel = new TGlobalModel();
		File datasourceFile = TDataSourceManager.fetchDataSourceFile();
		buildGlobalModel(datasourceFile, globalModel);
		return globalModel;
	}
	
	/**
	 * Builds a global model for code locator, this global model is used to operate in memory.
	 * @param datasourceFile
	 * @param globalModel
	 * @throws TDataSourceException 
	 */
	protected void buildGlobalModel(File datasourceFile, TGlobalModel globalModel) throws TDataSourceException{
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
	 * @throws TDataSourceException
	 */
	protected Element getDataSourceRootElement(File datasourceFile) throws TDataSourceException{
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(datasourceFile);
			return document.getRootElement();
		} catch (DocumentException e) {
			throw new TDataSourceException(Messages.getString("DataSourceLoader.ex.notfound.sourcefile"),e);
		}
	}
	
	/**
	 * @return the globalModel
	 */
	public TGlobalModel getGlobalModel() {
		return globalModel;
	}

	/**
	 * @param globalModel the globalModel to set
	 */
	public void setGlobalModel(TGlobalModel globalModel) {
		this.globalModel = globalModel;
	}
	
}
