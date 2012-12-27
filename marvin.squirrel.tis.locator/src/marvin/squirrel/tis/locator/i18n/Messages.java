/**
 * 
 */
package marvin.squirrel.tis.locator.i18n;

import java.util.ResourceBundle;

import marvin.squirrel.tis.locator.TISLocatorPlugin;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 26, 2012
 */
public class Messages extends MessagesCenter{

    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    
    public static String getString(final String key) {
        return getString(key, TISLocatorPlugin.PLUGIN_ID, resourceBundle);
    }
}
