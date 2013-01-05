/**
 * 
 */
package marvin.squirrel.tis.locator.data.model;

import marvin.squirrel.tis.locator.constants.TPropertyForModel;
import marvin.squirrel.tis.locator.enums.TFunctionTypeEnum;
import marvin.squirrel.tis.locator.enums.TModelStatusEnum;
import marvin.squirrel.tis.locator.enums.TProductsEnum;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 26, 2012
 */
public class TFunctionModel extends TAbstractModel{
	
	private String id;
	
	private String name;
	
	private TFunctionTypeEnum type;
	
	private TProductsEnum product;
	
	private TClassModel classModel;
	
	private TVersionInfoModel versionInfoModel;
	
	private String desc;
	
	private TModelStatusEnum status = TModelStatusEnum.MERGED;

	/**
	 * 
	 */
	public TFunctionModel() {
		super();
		classModel = new TClassModel();
		versionInfoModel = new TVersionInfoModel();
	}
	
	public TFunctionModel(String id, String name) {
		this();
		this.id = id;
		this.name = name;
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

	public TFunctionTypeEnum getType() {
		return type;
	}

	public void setType(TFunctionTypeEnum type) {
		this.type = type;
	}

	public TProductsEnum getProduct() {
		return product;
	}

	public void setProduct(TProductsEnum product) {
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

	/**
	 * @return the status
	 */
	public TModelStatusEnum getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(TModelStatusEnum status) {
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
