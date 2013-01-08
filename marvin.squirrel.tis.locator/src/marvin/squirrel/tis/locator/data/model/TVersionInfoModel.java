/**
 * 
 */
package marvin.squirrel.tis.locator.data.model;

import marvin.squirrel.tis.locator.constants.TPropertyForModel;
import marvin.squirrel.tis.locator.enums.TSVNSourceEnum;
import marvin.squirrel.tis.locator.enums.TSVNVersionEnum;


/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 27, 2012
 */
public class TVersionInfoModel {
	
	private TFunctionModel functionModel;

	private TSVNSourceEnum svnDir = TSVNSourceEnum.TSVN_UNIDENTIFIED;
	
	private TSVNVersionEnum version = TSVNVersionEnum.VERSION_UNIDENTIFIED;
	
	public TVersionInfoModel(){}
	
	public TVersionInfoModel(TFunctionModel functionModel){
		this.functionModel = functionModel;
	}

	public TSVNSourceEnum getSvnDir() {
		return svnDir;
	}

	public void setSvnDir(TSVNSourceEnum svnDir) {
		if(!this.svnDir.equals(svnDir)){
			this.svnDir = svnDir;
			functionModel.firePropertyChange(TPropertyForModel.SVN_REPOSITORY, this.svnDir, svnDir);
		}
	}

	public TSVNVersionEnum getVersion() {
		return version;
	}

	public void setVersion(TSVNVersionEnum version) {
		if(!this.version.equals(version)){
			this.version = version;
			functionModel.firePropertyChange(TPropertyForModel.SVN_VERSION, this.version, version);
		}
	}

	/**
	 * @return the functionModel
	 */
	public TFunctionModel getFunctionModel() {
		return functionModel;
	}

	/**
	 * @param functionModel the functionModel to set
	 */
	public void setFunctionModel(TFunctionModel functionModel) {
		this.functionModel = functionModel;
	}

	
}
