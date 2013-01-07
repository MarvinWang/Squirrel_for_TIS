/**
 * 
 */
package marvin.squirrel.tis.locator.data.handler;

import java.util.List;

import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.data.model.TGlobalModel;
import marvin.squirrel.tis.locator.enums.TModelStatusEnum;
import marvin.squirrel.tis.locator.exception.TDataSourceException;
import marvin.squirrel.tis.locator.utils.TStringUtils;
import marvin.squirrel.tis.locator.utils.TUniqueIDCreater;

/**
 * @author Marvin Wang
 * @date 2013-1-1
 * @email mwang@talend.com
 */
public class TModelProxyFactory {
	
	private static int index = 1;

	private static TModelProxyFactory factory;
	
	private TModelProxyFactory(){}
	
	
	public static TModelProxyFactory getInstance(){
		if(factory == null)
			factory = new TModelProxyFactory();
		return factory;
	}
	
	/**
	 * Initializes the models from data source, it invokes {@link TDataSourceLoader#loadData()} to load data 
	 * from <code>DataSource.xml</code>. Before invoking this method, you should identify the model changed 
	 * is saved to file.
	 * @throws TDataSourceException
	 */
	public void initModel() throws TDataSourceException{
		TDataSourceLoader.getInstance().loadDataFromFile();
	}
	
	public void saveModel() throws TDataSourceException{
		TDataSourceWriter.getInstance().pushModelToFile();
	}
	
	public TFunctionModel newFunctionModel(){
		TFunctionModel functionModel = new TFunctionModel(String.valueOf(TUniqueIDCreater.getUniqueID()),newFunctionName()); 
		functionModel.setStatus(TModelStatusEnum.SEPERATE);
		return functionModel;
	}
	
	/**
	 * Creates a new function name like the format <code>New Function('index')</code>.
	 * @return
	 */
	public String newFunctionName(){
		
		String newName = makeUpName(index);
		TGlobalModel globalModel = TDataSourceLoader.getInstance().getGlobalModel();
		while(globalModel.hasFunctionName(newName)){
			++index;
			newName = makeUpName(index);
		}	
		index = 1;
		return newName;
	}
	
	private String makeUpName(int index){
		return TStringUtils.getNewFunctionNameStartWith() + index + TStringUtils.getNewFunctionNameEndWith();
	}
	
	public TGlobalModel getGlobalModel(){
		return TDataSourceLoader.getInstance().getGlobalModel();
	}
	
	/**
	 * 
	 * @param functionName
	 * @return
	 * @deprecated
	 */
	public boolean isFunctionModelExist(String functionName){
		return TDataSourceLoader.getInstance().getGlobalModel().hasFunctionName(functionName);
	}
	
	public boolean isMoreFunctionModelExist(String functionId, String functionName){
		return TDataSourceLoader.getInstance().getGlobalModel().hasSameFunctionName(functionId, functionName);
	}
	
	/**
	 * Gets function model by function name.
	 * @param functionName
	 * @return
	 */
	public TFunctionModel getFunctionModelByName(String functionName){
		List<TFunctionModel> functionModels = getGlobalModel().getFunctionModels();
		if(functionModels.size() > 0){
			for(TFunctionModel model : functionModels){
				if(model.getName().equals(functionName))
					return model;
			}
		}
		return null;
	}
}
