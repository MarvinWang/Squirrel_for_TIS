/**
 * 
 */
package marvin.squirrel.tis.locator.data.handler;

import marvin.squirrel.tis.locator.data.model.GlobalModel;
import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.exception.DataSourceException;
import marvin.squirrel.tis.locator.i18n.Messages;

/**
 * @author Marvin Wang
 * @date 2013-1-1
 * @email mwang@talend.com
 */
public class ModelProxyFactory {
	
	public static final String NEW_FUNCTION_NAME = Messages.getString("FunctionLocatorEditor.title.newFunction");
	
	public static final String NEW_FUNCTION_NAME_LEFT_BRACKET = Messages.getString("FunctionLocatorEditor.title.newFunction.leftBracket");
	
	public static final String NEW_FUNCTION_NAME_RIGHT_BRACKET = Messages.getString("FunctionLocatorEditor.title.newFunction.rightBracket");
	
	private static int index = 1;

	private static ModelProxyFactory factory;
	
	private ModelProxyFactory(){}
	
	
	public static ModelProxyFactory getInstance(){
		if(factory == null)
			factory = new ModelProxyFactory();
		return factory;
	}
	
	/**
	 * Initializes the models from data source, it invokes {@link DataSourceLoader#loadData()} to load data 
	 * from <code>DataSource.xml</code>. Before invoking this method, you should identify the model changed 
	 * is saved to file.
	 * @throws DataSourceException
	 */
	public void initModel() throws DataSourceException{
		DataSourceLoader.getInstance().loadData();
	}
	
	public static TFunctionModel newFunctionModel(){
		return new TFunctionModel(newFunctionName());
	}
	
	/**
	 * Creates a new function name like the format <code>New Function('index')</code>.
	 * @return
	 */
	public static String newFunctionName(){
		
		String newName = makeUpName(index);
		GlobalModel globalModel = DataSourceLoader.getInstance().getGlobalModel();
		while(globalModel.hasFunctionName(newName)){
			++index;
			newName = makeUpName(index);
		}	
		index = 1;
		return newName;
	}
	
	private static String makeUpName(int index){
		return NEW_FUNCTION_NAME + NEW_FUNCTION_NAME_LEFT_BRACKET + index + NEW_FUNCTION_NAME_RIGHT_BRACKET;
	}
}
