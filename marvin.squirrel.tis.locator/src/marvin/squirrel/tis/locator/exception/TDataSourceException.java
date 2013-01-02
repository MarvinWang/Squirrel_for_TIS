/**
 * 
 */
package marvin.squirrel.tis.locator.exception;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 25, 2012
 */
public class TDataSourceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TDataSourceException(){
		super();
	}
	
	public TDataSourceException(String msg){
		super(msg);
	}
	
	public TDataSourceException(String msg, Exception ex){
		super(msg,ex);
	}
}
