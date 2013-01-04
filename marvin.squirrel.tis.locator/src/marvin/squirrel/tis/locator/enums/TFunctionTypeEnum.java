/**
 * 
 */
package marvin.squirrel.tis.locator.enums;


/**
 * @author Marvin Wang
 * @date 2013-1-3
 * @email mwang@talend.com
 */
public enum TFunctionTypeEnum {

	REP_JOB("REP_JOB", "REP_Job",""),
	REP_CONTEXT("REP_CONTEXT","REP_Context",""), 
	REP_DBCONNECTION("REP_DBCONNECTION", "REP_DBConnection",""), 
	REP_VIEW("REP_VIEW","REP_View", "");
	
	private String name;
	
	private String displayName;
	
	private String desc;
	
	TFunctionTypeEnum(String name, String displayName, String desc){
		this.name = name;
		this.displayName = displayName;
		this.desc = desc;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	
	
}
