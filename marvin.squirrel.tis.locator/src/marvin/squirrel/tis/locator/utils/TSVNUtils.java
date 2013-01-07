/**
 * 
 */
package marvin.squirrel.tis.locator.utils;

import java.util.ArrayList;
import java.util.List;

import marvin.squirrel.tis.locator.enums.TFunctionTypeEnum;
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
	
	/**
	 * Gets the enum of <code>TSVNSourceEnum</code> by given svn source name like "tos".
	 * @param svnSourceName
	 * @return
	 */
	public static TSVNSourceEnum getSVNSource(String svnSourceName){
		TSVNSourceEnum[] all = TSVNSourceEnum.values();
		for(TSVNSourceEnum svnSource : all){
			if(svnSource.getName().equals(svnSourceName)){
				return svnSource;
			}
		}
		return null;
	}
	
	/**
	 * Gets the enum of <code>TSVNVersionEnum</code> by given svn version name like "VERSION_TRUNK".
	 * @param svnVersionName
	 * @return
	 */
	public static TSVNVersionEnum getSVNVersion(String svnVersionName){
		TSVNVersionEnum[] all = TSVNVersionEnum.values();
		for(TSVNVersionEnum svnVersion : all){
			if(svnVersion.getName().equals(svnVersionName)){
				return svnVersion;
			}
		}
		return null;
	}

	public static int indexSVNSource(TSVNSourceEnum svnSource){
		TSVNSourceEnum[] all = TSVNSourceEnum.values();
		int index = -1;
		for(TSVNSourceEnum tmp : all){
			++index;
			if(tmp.equals(svnSource)){
				return index;
			}
		}
		return 0;
	}
	
	public static int indexSVNVersion(TSVNVersionEnum svnVersion){
		TSVNVersionEnum[] all = TSVNVersionEnum.values();
		int index = -1;
		for(TSVNVersionEnum tmp : all){
			++index;
			if(tmp.equals(svnVersion)){
				return index;
			}
		}
		return 0;
	}
}
