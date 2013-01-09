/**
 * 
 */
package marvin.squirrel.tis.locator.data.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import marvin.squirrel.tis.locator.data.model.TClassModel;
import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.data.model.TGlobalModel;
import marvin.squirrel.tis.locator.data.model.TVersionInfoModel;
import marvin.squirrel.tis.locator.exception.TDataSourceException;
import marvin.squirrel.tis.locator.i18n.Messages;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * @author Marvin Wang
 * @date 2013-1-2
 * @email mwang@talend.com
 */
public class TDataSourceWriter {

	private static TDataSourceWriter writer;
	
	private TDataSourceWriter(){}
	
	public static TDataSourceWriter getInstance(){
		if(writer == null)
			writer = new TDataSourceWriter();
		return writer;
	}
	
	/**
	 * @throws TDataSourceException 
	 * 
	 */
	public void pushModelToFile() throws TDataSourceException{
		File outputFile = TDataSourceManager.fetchDataSourceTmpFile();
//		File outputFile = TDataSourceManager.fetchDataSourceFile();
		OutputFormat xmlFormat = OutputFormat.createPrettyPrint();
		xmlFormat.setEncoding("utf-8");
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(outputFile), xmlFormat);
			writer.write(buildDocument());
			writer.close();
		} catch (UnsupportedEncodingException e) {
			throw new TDataSourceException(Messages.getString("TDataSourceWriter.ex.encoding"), e);
		} catch (FileNotFoundException e) {
			throw new TDataSourceException(Messages.getString("DataSourceLoader.ex.notfound.sourcefile"), e);
		} catch (IOException e) {
			throw new TDataSourceException(Messages.getString("TDataSourceWriter.ex.cannot.writeToFile"), e);
		}
		
	}
	
	protected Document buildDocument(){
		TGlobalModel globalModel = TModelProxyFactory.getInstance().getGlobalModel();
		Document document = DocumentHelper.createDocument();
		Element globalEle = document.addElement(Messages.getString("DataSource.tag.global"));
		buildHeadComments(globalEle);
		buildRootElement(globalEle, globalModel);
		return document;
	}
	
	protected void buildRootElement(Element globalElement, TGlobalModel globalModel){
		List<TFunctionModel> functionModels = globalModel.getFunctionModels();
		if(functionModels != null){
			for(TFunctionModel funcModel : functionModels){
				if(funcModel.getName() != null && !"".equals(funcModel)){
					Element functionElement = globalElement.addElement(Messages.getString("DataSource.tag.function"));
					buildFunctionElement(functionElement,funcModel);
					
//					ClassInfo element
					Element classInfoElement = functionElement.addElement(Messages.getString("DataSource.tag.classInfo"));
					buildClassInfoElement(classInfoElement, funcModel.getClassModel());
					
//					Desc elment for function element
					Element descElement = functionElement.addElement(Messages.getString("DataSource.tag.function.desc"));
					descElement.addText(funcModel.getDesc() == null ? "" : funcModel.getDesc());
					
//					VersionInfo element
					Element versioninfoElement = functionElement.addElement(Messages.getString("DataSource.tag.function.versionInfo"));
					buildVersionInfoElement(versioninfoElement, funcModel.getVersionInfoModel());
				}
			}
		}
	}
	
	protected void buildHeadComments(Element globalElement){
//		globalElement.addComment(Messages.get);
	}
	
	protected void buildFunctionElement(Element functionElement, TFunctionModel funcModel){
		functionElement.addAttribute(Messages.getString("DataSource.tag.function.attr.id"), funcModel.getId());
		functionElement.addAttribute(Messages.getString("DataSource.tag.function.attr.name"), funcModel.getName());
		functionElement.addAttribute(Messages.getString("DataSource.tag.function.attr.type"), funcModel.getType().getDisplayName());
		functionElement.addAttribute(Messages.getString("DataSource.tag.function.attr.product"), funcModel.getProduct().getName());
	}
	
	protected void buildClassInfoElement(Element classInfoElement, TClassModel classModel){
		classInfoElement.addAttribute(Messages.getString("DataSource.tag.classInfo.attr.name"), classModel.getClassName());
		Element packageElement = classInfoElement.addElement(Messages.getString("DataSource.tag.classInfo.package"));
		packageElement.addText(classModel.getPackageStr() == null ? "" : classModel.getPackageStr());
		Element pluginElement = classInfoElement.addElement(Messages.getString("DataSource.tag.classInfo.plugin"));
		pluginElement.addText(classModel.getPluginName() == null ? "" : classModel.getPluginName());
		Element methodElement = classInfoElement.addElement(Messages.getString("DataSource.tag.classInfo.method"));
		methodElement.addText(classModel.getMethod() == null ? "" : classModel.getMethod());
	}
	

	protected void buildVersionInfoElement(Element versionInfoElement, TVersionInfoModel versionInfoModel){
		versionInfoElement.addAttribute(Messages.getString("DataSource.tag.function.versionInfo.attr.url"), versionInfoModel.getSvnDir().getName());
		Element versionElement = versionInfoElement.addElement(Messages.getString("DataSource.tag.function.versionInfo.version"));
		versionElement.addText(versionInfoModel.getVersion() == null ? "" : versionInfoModel.getVersion().getVersion());
	}
}
