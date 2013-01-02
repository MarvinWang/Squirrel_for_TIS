/**
 * 
 */
package marvin.squirrel.tis.locator.data.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marvin
 * @date 2013-1-1
 */
public class TAbstractModel {

	private List<PropertyChangeListener> listeners;
	
	public TAbstractModel(){
		listeners = new ArrayList<PropertyChangeListener>();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		listeners.add(listener);
	}
	
	public void firePropertyChange(String propertyName, Object oldName, Object newValue){
		for(PropertyChangeListener listener : listeners){
			listener.propertyChange(new PropertyChangeEvent(this, propertyName, oldName, newValue));
		}
	}
	
}
