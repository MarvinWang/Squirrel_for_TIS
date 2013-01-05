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
import marvin.squirrel.tis.locator.exception.TDataSourceException;
import marvin.squirrel.tis.locator.utils.TFunctionTypeUtils;
import marvin.squirrel.tis.locator.utils.TProductUtils;

/**
 * @author Marvin Wang
 * @date 2013-1-2
 * @email mwang@talend.com
 */
public class TCodeEditorController {
	
	private TCodeLocatorEditor editor;
	private TCodeLocatorEditorInput input;

	public TCodeEditorController(TCodeLocatorEditor editor){
		this.editor = editor;
		input = (TCodeLocatorEditorInput)editor.getEditorInput();
	}
	
	public void init(){
		TFunctionModel functionModel = input.getFunctionModel();
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
		
		
//		SVN Version
		
	}
	
	public void doFunctionNameChange(){
		TFunctionModel functionmodel = input.getFunctionModel();
		String functionName = editor.getFunctionNameTxt().getText();
		functionmodel.setName(functionName);
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
	
	public boolean checkIfFunctionExist(String functionName){
		return TModelProxyFactory.getInstance().isFunctionModelExist(functionName);
	}
}
