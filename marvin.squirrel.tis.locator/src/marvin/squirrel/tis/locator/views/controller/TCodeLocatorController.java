/**
 * 
 */
package marvin.squirrel.tis.locator.views.controller;

import java.util.ArrayList;
import java.util.List;

import marvin.squirrel.tis.locator.data.handler.TDataSourceLoader;
import marvin.squirrel.tis.locator.data.model.TGlobalModel;
import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.views.TCodeLocatorView;

/**
 * @author Marvin Wang
 * @date 2012-12-23
 */
public class TCodeLocatorController {
	
	private TCodeLocatorView view;

	public TCodeLocatorController(){}
	
	public TCodeLocatorController(TCodeLocatorView view){
		this.view = view;
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
		
		
		view.getPluginTxt().setText(functionModel.getPluginName());
		view.getClassNameTxt().setText(functionModel.getClassName());
		view.getMethodTxt().setText(functionModel.getMethodName());
		view.getDescText().setText(functionModel.getDesc());
		view.getRepositoryTxt().setText(functionModel.getRepository());
	}
}
