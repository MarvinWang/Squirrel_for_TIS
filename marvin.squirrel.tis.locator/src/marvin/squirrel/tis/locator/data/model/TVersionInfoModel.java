/**
 * 
 */
package marvin.squirrel.tis.locator.data.model;

import marvin.squirrel.tis.locator.enums.TSVNSourceEnum;
import marvin.squirrel.tis.locator.enums.TSVNVersionEnum;


/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 27, 2012
 */
public class TVersionInfoModel {

	private TSVNSourceEnum svnDir;
	
	private TSVNVersionEnum version;
	
	public TVersionInfoModel(){}

	public TSVNSourceEnum getSvnDir() {
		return svnDir;
	}

	public void setSvnDir(TSVNSourceEnum svnDir) {
		this.svnDir = svnDir;
	}

	public TSVNVersionEnum getVersion() {
		return version;
	}

	public void setVersion(TSVNVersionEnum version) {
		this.version = version;
	}

	
}
