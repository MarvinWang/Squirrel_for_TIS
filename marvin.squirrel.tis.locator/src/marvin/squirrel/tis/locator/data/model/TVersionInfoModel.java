/**
 * 
 */
package marvin.squirrel.tis.locator.data.model;


/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 27, 2012
 */
public class TVersionInfoModel {

	private String svnDir;
	
	private String version;
	
	public TVersionInfoModel(){}

	public String getSvnDir() {
		return svnDir;
	}

	public void setSvnDir(String svnDir) {
		this.svnDir = svnDir;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
}
