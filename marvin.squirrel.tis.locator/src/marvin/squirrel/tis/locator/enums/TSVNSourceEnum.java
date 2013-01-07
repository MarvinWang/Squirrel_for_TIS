/**
 * 
 */
package marvin.squirrel.tis.locator.enums;

/**
 * @author Marvin
 * @date 2012-12-23
 */
public enum TSVNSourceEnum {

	TSVN_UNIDENTIFIED("Unidentified", ""),
	TSVN_SOURCE_TOS("tos", "www.talendforge.org/svn/tos"),
	TSVN_SOURCE_TIS_SHARED("tis_shared","www.talendforge.org/svn/tis_shared"),
	TSVN_SOURCE_TIS_PRIVATE("tis_private","www.talendforge.org/svn/tis_private"),
	TSVN_SOURCE_TOP("top","www.talendforge.org/svn/top"),
	TSVN_SOURCE_TDQ("tdq","www.talendforge.org/svn/tdq"),
	TSVN_SOURCE_COMMON("common","www.talendforge.org/svn/common");
	
	private String name;//Display name and stored in DataSource.xml
	
	private String path;
	
	TSVNSourceEnum(String name, String path){
		this.name = name;
		this.path = path;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * 
	 * @param svnSource
	 * @return
	 */
	public boolean equals(TSVNSourceEnum svnSource){
		if(svnSource == null)
			return false;
		if(this.name().equals(svnSource.getName()) && this.path.equals(svnSource.getPath())){
			return true;
		}
		return false;
	}
	
}
