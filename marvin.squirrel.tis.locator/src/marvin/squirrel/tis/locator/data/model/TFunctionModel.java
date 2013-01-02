/**
 * 
 */
package marvin.squirrel.tis.locator.data.model;

import marvin.squirrel.tis.locator.constants.TPropertyForModel;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 26, 2012
 */
public class TFunctionModel extends TAbstractModel{
	
	private String name;
	
	private String type;
	
	private String product;
	
	private TClassModel classModel;
	
	private TVersionInfoModel versionInfoModel;
	
	private String desc;

	/**
	 * 
	 */
	public TFunctionModel() {
		super();
	}
	
	public TFunctionModel(String name) {
		super();
		this.name = name;
	}

	public String getRepository(){
		return versionInfoModel.getSvnDir();
	}
	
	public String getPluginName(){
		return classModel.getPluginName();
	}
	
	public String getMethodName(){
		return classModel.getMethod();
	}
	
	public String getClassName(){
		return classModel.getClassName();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(this.name != name){
			this.name = name;
			firePropertyChange(TPropertyForModel.FUNCTION_NAME, this.name, name);
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public TClassModel getClassModel() {
		return classModel;
	}

	public void setClassModel(TClassModel classModel) {
		this.classModel = classModel;
	}

	public TVersionInfoModel getVersionInfoModel() {
		return versionInfoModel;
	}

	public void setVersionInfoModel(TVersionInfoModel versionInfoModel) {
		this.versionInfoModel = versionInfoModel;
	}
}
