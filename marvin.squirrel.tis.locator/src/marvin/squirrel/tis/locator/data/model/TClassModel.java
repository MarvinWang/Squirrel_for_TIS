/**
 * 
 */
package marvin.squirrel.tis.locator.data.model;

import marvin.squirrel.tis.locator.constants.TPropertyForModel;

/**
 * @author Marvin
 * @date 2012-12-23
 */
public class TClassModel{

	private TFunctionModel functionModel;
	
	private String className;
	
	private String method;
	
	private String packageStr;
	
	private String pluginName;
	
	private String desc;
	
	public TClassModel(){
		super();
	}
	
	public TClassModel(String className){
		this();
		this.className = className;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		if(this.className != className){
			this.className = className;
			functionModel.firePropertyChange(TPropertyForModel.CLASS_NAME, this.className, className);
		}
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		if(this.method != null){
			this.method = method;
			functionModel.firePropertyChange(TPropertyForModel.CLASS_METHOD, this.method, method);
		}
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		if(this.packageStr != packageStr){
			this.packageStr = packageStr;
			functionModel.firePropertyChange(TPropertyForModel.CLASS_PACKAGE, this.packageStr, packageStr);
		}
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		if(this.pluginName != pluginName){
			this.pluginName = pluginName;
			functionModel.firePropertyChange(TPropertyForModel.CLASS_PLUGIN, this.pluginName, pluginName);
		}
	}

	public TFunctionModel getFunctionModel() {
		return functionModel;
	}

	public void setFunctionModel(TFunctionModel functionModel) {
		this.functionModel = functionModel;
	}
	
	
}
