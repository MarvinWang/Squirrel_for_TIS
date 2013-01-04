/**
 * 
 */
package marvin.squirrel.tis.locator.editors.controller;

import org.eclipse.core.runtime.IProgressMonitor;

import marvin.squirrel.tis.locator.data.handler.TModelProxyFactory;
import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.editors.TCodeLocatorEditor;
import marvin.squirrel.tis.locator.editors.TCodeLocatorEditorInput;
import marvin.squirrel.tis.locator.enums.TModelStatusEnum;
import marvin.squirrel.tis.locator.exception.TDataSourceException;

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
		String functionName = functionModel.getName();
		editor.getFunctionNameTxt().setText(functionName);
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
