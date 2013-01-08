/**
 * 
 */
package marvin.squirrel.tis.locator.enums;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 26, 2012
 */
public enum TProductsEnum {

	TALEND_UNIDENTIFIED("Unidentified", "Product Unidentified"),
	TALEND_DI("DI","Data Integation"), 
	TALEND_DQ("DQ", "Data Quality"), 
	TALEND_MDM("MDM", "Master Data Management"), 
	TALEND_ESB("ESB", "");
	
	private String name;//Used to display in UI and store in data source file.
	
	private String fullName;
	
	TProductsEnum(String name){
		this.name = name;
	}
	
	TProductsEnum(String name, String fullName){
		this.name = name;
		this.fullName = fullName;
	}

	/**
	 * Gets the name of object like "<code>DI</code>", "<code>DQ</code>".
	 * @return
	 */
	public String getName() {
		return name;
	}

	public String getFullName() {
		return fullName;
	}
	
	public  boolean equals(TProductsEnum product){
		if(product  == null)
			return false;
		if(this.getName().equals(product.getName()) &&
				this.getFullName().equals(product.getFullName())){
			return true;
		}
		return false;
	}
	
}
