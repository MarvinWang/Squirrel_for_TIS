/**
 * 
 */
package marvin.squirrel.tis.patcher.i18n;

import java.util.ResourceBundle;

import marvin.squirrel.tis.patcher.PatcherPlugin;


/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Jan 22, 2013
 */
public class Messages extends MessagesCenter {

    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    
    public static String getString(final String key) {
        return getString(key, PatcherPlugin.PLUGIN_ID, resourceBundle);
    }
}
