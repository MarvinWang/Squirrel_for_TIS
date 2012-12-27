/**
 * 
 */
package marvin.squirrel.tis.locator.data.model;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 26, 2012
 */
public enum TProducts {

	TALEND_DI("DI","Data Integation"), 
	TALEND_DQ("DQ", "Data Quality"), 
	TALEND_MDM("MDM", "Master Data Management"), 
	TALEND_ESB("ESB", "");
	
	private String name;
	
	private String fullName;
	
	TProducts(String name){
		this.name = name;
	}
	
	TProducts(String name, String fullName){
		this.name = name;
		this.fullName = fullName;
	}

	public String getName() {
		return name;
	}

	public String getFullName() {
		return fullName;
	}
	
	
}
