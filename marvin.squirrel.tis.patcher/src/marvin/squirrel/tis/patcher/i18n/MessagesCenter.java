/**
 * 
 */
package marvin.squirrel.tis.patcher.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Jan 22, 2013
 */
public abstract class MessagesCenter {
	private static Logger logger = Logger.getLogger(MessagesCenter.class);

	public static final String KEY_NOT_FOUND_PREFIX = "!!!!"; //$NON-NLS-1$

    public static final String KEY_NOT_FOUND_SUFFIX = "!!!!"; //$NON-NLS-1$
    
    public static String getString(String key, String pluginId, ResourceBundle resourceBundle) {
        if (resourceBundle == null) {
            return KEY_NOT_FOUND_PREFIX + key + KEY_NOT_FOUND_SUFFIX;
        }
        logger.trace("Getting key \'" + key + "\' in" + resourceBundle.toString()); //$NON-NLS-1$ //$NON-NLS-2$
        try {
            return resourceBundle.getString(key);

        } catch (MissingResourceException e) {
            return KEY_NOT_FOUND_PREFIX + key + KEY_NOT_FOUND_SUFFIX;
        }
    }
}
