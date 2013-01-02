/**
 * 
 */
package marvin.squirrel.tis.locator.editors.controller;

import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.editors.TCodeLocatorEditor;
import marvin.squirrel.tis.locator.editors.TCodeLocatorEditorInput;

/**
 * @author Marvin Wang
 * @date 2013-1-2
 * @email mwang@talend.com
 */
public class TFunctionEditorController {
	
	private TCodeLocatorEditor editor;
	private TCodeLocatorEditorInput input;

	public TFunctionEditorController(TCodeLocatorEditor editor){
		this.editor = editor;
		input = (TCodeLocatorEditorInput)editor.getEditorInput();
	}
	
	public void doFunctionNameChange(){
		TFunctionModel functionmodel = input.getFunctionModel();
		String functionName = editor.getFunctionNameTxt().getText();
		functionmodel.setName(functionName);
	}
}
