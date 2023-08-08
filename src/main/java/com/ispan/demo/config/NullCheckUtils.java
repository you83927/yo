package com.ispan.demo.config;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 確認並複製非null對象的屬性
 * 
 * @author User
 *
 */
public class NullCheckUtils {
	/**
	 * 傳入是否有任意屬性為null
	 * @param object
	 * @return
	 */
	public static boolean checkNullFields(Object object) {
		String toString = ReflectionToStringBuilder.toString(object);
		return !toString.contains("null");
	}

	/**
	 * 將非null的屬性值複製到目標物件上
	 * @param source
	 * @param destination
	 */
	public static void copyPropertiesNonNull(Object source, Object destination) {
		try {
			PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(source);
			for (PropertyDescriptor descriptor : descriptors) {
				String propertyName = descriptor.getName();
				Object propertyValue = PropertyUtils.getProperty(source, propertyName);
				if (propertyValue != null) {
					BeanUtils.copyProperty(destination, propertyName, propertyValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 找到值為非null的屬性
	 * 
	 * @param obj
	 * @return
	 */
	public static List<String> findNonNullProperty(Object obj) {
		Class<?> objClass = obj.getClass();
		Field[] fields = objClass.getDeclaredFields();
		List<String> stringList = new ArrayList<>();

		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.get(obj) != null) {
					stringList.add(field.getName());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return stringList;
	}

	/**
	 * 透過屬性名進行getter
	 * 
	 * @param obj
	 * @param property
	 * @return
	 */
	public static Object getPropertyValue(Object obj, String property) {
		try {
			// 獲取屬性對象
			Field field = obj.getClass().getDeclaredField(property);

			// 設置訪問權限（如果是私有属性）
			field.setAccessible(true);

			// 獲取屬性值
			Object value = field.get(obj);

			return value;
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 透過屬性名進行setter
	 * @param obj
	 * @param propertyName
	 * @param value
	 */
	public static void setProperty(Object obj, String propertyName, Object value) {
        try {
            // 獲取屬性對象
            Field field = obj.getClass().getDeclaredField(propertyName);
            
            // 設置訪問權限（如果是私有属性）
            field.setAccessible(true);
            
            // 設置属性值
            field.set(obj, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
