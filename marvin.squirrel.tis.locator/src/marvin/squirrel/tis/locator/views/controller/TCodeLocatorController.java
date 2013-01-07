/**
 * 
 */
package marvin.squirrel.tis.locator.views.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import marvin.squirrel.tis.locator.actions.TEditFunctionAction;
import marvin.squirrel.tis.locator.actions.TOpenFunctionEditorAction;
import marvin.squirrel.tis.locator.data.handler.TDataSourceLoader;
import marvin.squirrel.tis.locator.data.model.TGlobalModel;
import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.i18n.Messages;
import marvin.squirrel.tis.locator.views.TCodeLocatorView;

/**
 * @author Marvin Wang
 * @date 2012-12-23
 */
public class TCodeLocatorController {
	
	private Action openFunctionEditorAction;
	
	private Action editFunctionAction;
	
	private TCodeLocatorView view;

	public TCodeLocatorController(){}
	
	/**
	 * Constructs an instance of controller and invokes the method {@link #init()}.
	 * @param view
	 */
	public TCodeLocatorController(TCodeLocatorView view){
		this.view = view;
		init();
	}
	
	/**
	 * Does some initializations for controller, like making all actions by method {@link #makeActions()}.
	 */
	protected void init(){
		makeActions();
	}
	
	/**
	 * Do locating when clicking the button named "Locate".
	 * @param keyWords
	 */
	public void doLocate(String keyWords){
		List<String> functionNameList = new ArrayList<String>();
		TGlobalModel globalModel = TDataSourceLoader.getInstance().getGlobalModel();
		List<TFunctionModel> functionModels = globalModel.getFunctionModels();
		if(functionModels != null && functionModels.size() > 0){
			for(TFunctionModel functionModel : functionModels){
				String functionName = functionModel.getName();
				if(functionName.contains(keyWords)){
					functionNameList.add(functionName);
				}
			}
		}
		view.getFunctionNameList().setItems(functionNameList.toArray(new String[functionNameList.size()]));
	}
	
	/**
	 * 
	 * @param functionNames
	 */
	public void doFunctionNameSelect(String[] functionNames){
		String firstFunctionName = functionNames[0];
		TGlobalModel globalModel = TDataSourceLoader.getInstance().getGlobalModel();
		TFunctionModel functionModel = globalModel.lookupByFunctionName(firstFunctionName);
		
		String pluginName = functionModel.getClassModel().getPluginName();
		view.getPluginTxt().setText(pluginName == null ? "" : pluginName);
		
		String className = functionModel.getClassModel().getClassName();
		view.getClassNameTxt().setText(className == null ? "" : className);
		
		String method = functionModel.getClassModel().getMethod();
		view.getMethodTxt().setText(method == null ? "" : method);
		
		String functionDesc = functionModel.getDesc();
		view.getDescText().setText(functionDesc == null ? "" : functionDesc);
//		view.getRepositoryTxt().setText(functionModel.getVersionInfoModel().getSvnDir().getName());
	}
	
	public void doEditMenuItemSelect(){
		editFunctionAction.run();
	}
	
	/**
	 * Makes all actions for view.
	 */
	protected void makeActions(){
//    Action for opening a new function editor.
		openFunctionEditorAction = new TOpenFunctionEditorAction(Messages.getString("TCodeLocatorView.action.openFunctionEditor.text")
				, PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		openFunctionEditorAction.setToolTipText(Messages.getString("TCodeLocatorView.action.openFunctionEditor.tooltip"));
		
//      Action for editing a function.
		editFunctionAction = new TEditFunctionAction(Messages.getString("TCodeLocatorView.action.editFunction.text"));
		editFunctionAction.setToolTipText(Messages.getString("TCodeLocatorView.action.editFunction.tooltip"));
		((TEditFunctionAction) editFunctionAction).setView(view);
	}
	
	public Action getOpenFunctionEditorAction() {
		return openFunctionEditorAction;
	}

	public Action getEditFunctionAction() {
		return editFunctionAction;
	}
}
