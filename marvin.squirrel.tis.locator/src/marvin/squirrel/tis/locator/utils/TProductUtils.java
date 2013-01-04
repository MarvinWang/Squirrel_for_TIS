/**
 * 
 */
package marvin.squirrel.tis.locator.utils;

import java.util.ArrayList;
import java.util.List;

import marvin.squirrel.tis.locator.enums.TProductsEnum;

/**
 * @author Marvin Wang
 * @date 2013-1-3
 * @email mwang@talend.com
 */
public class TProductUtils {

	public static String[] fetchAllProducts(){
		List<String> products = new ArrayList<String>();
		TProductsEnum[] all = TProductsEnum.values();
		for(TProductsEnum product : all){
			products.add(product.getName());
		}
		return products.toArray(new String[products.size()]);
	}
}
