/**
 * 
 */
package marvin.squirrel.tis.locator.enums;

/**
 * @author Marvin Wang
 * @date 2013-1-3
 * @email mwang@talend.com
 */
public enum TSVNVersionEnum {

	VERSION_B4_UP("VERSION_B4_UP", "Branch4_upper",""),
	VERSION_B5_1("VERSION_B5_1","Branch5.1",""),
	VERSION_B5_1_UP("VERSION_B5_1_UP","Branch5.1_upper",""),
	VERSION_B5_2("VERSION_B5_2","Branch5.2",""),
	VERSION_B5_2_UP("VERSION_B5_2_UP","Branch5.2_upper",""),
	VERSION_TRUNK("VERSION_TRUNK","Trunk","");
	
	private String name;//That is stored in DataSource.xml
	
	private String version;//The display version in UI.
	
	private String desc;
	
	TSVNVersionEnum(String name, String version, String desc){
		this.name = name;
		this.version = version;
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
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
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
	
	public boolean equals(TSVNVersionEnum svnVersion){
		if(svnVersion == null){
			return false;
		}
		if(this.name.equals(svnVersion.getName()) && 
				this.getVersion().equals(svnVersion.getVersion() )&&
						this.getDesc().equals(svnVersion.getDesc())){
			return true;
		}
		return false;
	}
}
