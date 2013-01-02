/**
 * 
 */
package marvin.squirrel.tis.locator.editors.controller;

import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.editors.FunctionLocatorEditor;
import marvin.squirrel.tis.locator.editors.FunctionLocatorEditorInput;

/**
 * @author Marvin Wang
 * @date 2013-1-2
 * @email mwang@talend.com
 */
public class TFunctionEditorController {
	
	private FunctionLocatorEditor editor;
	private FunctionLocatorEditorInput input;

	public TFunctionEditorController(FunctionLocatorEditor editor){
		this.editor = editor;
		input = (FunctionLocatorEditorInput)editor.getEditorInput();
	}
	
	public void doFunctionNameChange(){
		TFunctionModel functionmodel = input.getFunctionModel();
		String functionName = editor.getFunctionNameTxt().getText();
		functionmodel.setName(functionName);
	}
}
