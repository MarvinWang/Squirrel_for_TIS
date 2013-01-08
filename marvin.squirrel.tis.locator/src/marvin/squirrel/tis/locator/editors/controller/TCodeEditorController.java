/**
 * 
 */
package marvin.squirrel.tis.locator.editors.controller;

import org.eclipse.core.runtime.IProgressMonitor;

import marvin.squirrel.tis.locator.data.handler.TModelProxyFactory;
import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.editors.TCodeLocatorEditor;
import marvin.squirrel.tis.locator.editors.TCodeLocatorEditorInput;
import marvin.squirrel.tis.locator.enums.TFunctionTypeEnum;
import marvin.squirrel.tis.locator.enums.TModelStatusEnum;
import marvin.squirrel.tis.locator.enums.TProductsEnum;
import marvin.squirrel.tis.locator.enums.TSVNSourceEnum;
import marvin.squirrel.tis.locator.enums.TSVNVersionEnum;
import marvin.squirrel.tis.locator.exception.TDataSourceException;
import marvin.squirrel.tis.locator.utils.TFunctionTypeUtils;
import marvin.squirrel.tis.locator.utils.TProductUtils;
import marvin.squirrel.tis.locator.utils.TSVNUtils;

/**
 * @author Marvin Wang
 * @date 2013-1-2
 * @email mwang@talend.com
 */
public class TCodeEditorController {
	
	private TCodeLocatorEditor editor;
	private TCodeLocatorEditorInput input;
	
	private TFunctionModel functionModel;

	public TCodeEditorController(TCodeLocatorEditor editor){
		this.editor = editor;
		input = (TCodeLocatorEditorInput)editor.getEditorInput();
	}
	
	public void init(){
		functionModel = input.getFunctionModel();
//		Function Name
		String functionName = functionModel.getName();
		editor.getFunctionNameTxt().setText(functionName);
		
//		Class Name
		String className = functionModel.getClassModel().getClassName();
		editor.getClassNameTxt().setText(className == null ? "" : className);
		
//		Method
		String method = functionModel.getClassModel().getMethod();
		editor.getMethodTxt().setText(method == null ? "" : method);

//		Plugin
		String plugin = functionModel.getClassModel().getPluginName();
		editor.getPluginNameTxt().setText(plugin == null ? "" : plugin);
		
//		Function Type
		TFunctionTypeEnum type = functionModel.getType();
		editor.getTypeCombo().select(TFunctionTypeUtils.indexFunctionType(type));
		
//		Product
		TProductsEnum product = functionModel.getProduct();
		editor.getProductCombo().select(TProductUtils.indexProduct(product));
		
//		Description
		String desc = functionModel.getDesc();
		editor.getDescriptionTxt().setText(desc ==  null ? "" : desc);
		
//		Repository
		TSVNSourceEnum svnSource = functionModel.getVersionInfoModel().getSvnDir();
		editor.getRepositoryCombo().select(TSVNUtils.indexSVNSource(svnSource));
		
//		SVN Version
		TSVNVersionEnum svnVersion = functionModel.getVersionInfoModel().getVersion();
		editor.getSvnVersionCombo().select(TSVNUtils.indexSVNVersion(svnVersion));
		
	}
	
	public void doFunctionNameChange(){
//		TFunctionModel functionmodel = input.getFunctionModel();
		String functionName = editor.getFunctionNameTxt().getText();
		functionModel.setName(functionName);
	}
	
	public void doClassNameChange(){
		String className = editor.getClassNameTxt().getText();
		functionModel.getClassModel().setClassName(className);
	}
	
	public void doMethodNameChange(){
		String methodName = editor.getMethodTxt().getText();
		functionModel.getClassModel().setMethod(methodName);
	}
	
	public void doPluginNameChange(){
		String pluginName = editor.getPluginNameTxt().getText();
		functionModel.getClassModel().setPluginName(pluginName);
	}
	
	public void doDescChange(){
		String desc = editor.getDescriptionTxt().getText();
		functionModel.setDesc(desc);
	}
	
	public void doTypeChange(){
		String type = editor.getTypeCombo().getText();
		functionModel.setType(TFunctionTypeUtils.getFunctionType(type));
	}
	
	public void doProductChange(){
		String product = editor.getProductCombo().getText();
		functionModel.setProduct(TProductUtils.getProduct(product));
	}
	
	public void doRepositoryChange(){
		String repository = editor.getRepositoryCombo().getText();
		functionModel.getVersionInfoModel().setSvnDir(TSVNUtils.getSVNSource(repository));
	}
	
	public void doSvnVersionChange(){
		String svnVersion = editor.getSvnVersionCombo().getText();
		functionModel.getVersionInfoModel().setVersion(TSVNUtils.getSVNVersion(svnVersion));
	}
	
	public void doSave(IProgressMonitor monitor) throws TDataSourceException{
		this.input = (TCodeLocatorEditorInput)editor.getEditorInput();
		TFunctionModel functionModel = input.getFunctionModel();
		TModelStatusEnum modelStatus = functionModel.getStatus();
		if(TModelStatusEnum.SEPERATE == modelStatus){
			TModelProxyFactory.getInstance().getGlobalModel().addFunctionModel(functionModel);
		}
		
		TModelProxyFactory.getInstance().saveModel();
		
	}
	
	public boolean checkIfFunctionExist(){
		boolean exist = false;
		
		return exist;
	}
	
	/**
	 * 
	 * @param functionName
	 * @return
	 * @deprecated
	 */
	public boolean checkIfFunctionExist(String functionName){
		return TModelProxyFactory.getInstance().isFunctionModelExist(functionName);
	}
}
