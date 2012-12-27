/**
 * 
 */
package marvin.squirrel.tis.locator.exception;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 25, 2012
 */
public class DataSourceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataSourceException(){
		super();
	}
	
	public DataSourceException(String msg){
		super(msg);
	}
	
	public DataSourceException(String msg, Exception ex){
		super(msg,ex);
	}
}
