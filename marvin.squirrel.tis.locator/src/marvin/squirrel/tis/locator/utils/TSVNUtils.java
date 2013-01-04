/**
 * 
 */
package marvin.squirrel.tis.locator.utils;

import java.util.ArrayList;
import java.util.List;

import marvin.squirrel.tis.locator.enums.TSVNSourceEnum;
import marvin.squirrel.tis.locator.enums.TSVNVersionEnum;

/**
 * @author Marvin Wang
 * @date 2013-1-3
 * @email mwang@talend.com
 */
public class TSVNUtils {

	/**
	 * Gets all repository names from {@link TSVNSourceEnum}.
	 * @return
	 */
	public static String[] fetchAllRepository(){
		List<String> repositories = new ArrayList<String>();
		TSVNSourceEnum[] all = TSVNSourceEnum.values();
		for(TSVNSourceEnum repository : all){
			repositories.add(repository.getName());
		}
		return repositories.toArray(new String[repositories.size()]);
	}
	
	/**
	 * Gets all versions from {@link TSVNVersionEnum}.
	 * @return
	 */
	public static String[] fetchAllSVNVersions(){
		List<String> versions = new ArrayList<String>();
		TSVNVersionEnum[] all = TSVNVersionEnum.values();
		for(TSVNVersionEnum version : all){
			versions.add(version.getVersion());
		}
		return versions.toArray(new String[versions.size()]);
	}
}
