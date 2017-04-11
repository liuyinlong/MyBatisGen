/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public final class ReflectUtil {
	private static Object[] p_fieldsToArray(Object obj, int superLevels)
  {
    List result = new ArrayList();
    try {
      Class sourceClass = obj.getClass();
      for (int level = 0; level <= superLevels; ++level) {
        Field[] fields = sourceClass.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
          Field f = fields[i];
          int modifier = f.getModifiers();
          if (Modifier.isStatic(modifier))
            break label121:

          f.setAccessible(true);
          String name = f.getName();
          if (name.indexOf("m_") == 0)
            name = name.substring(2);

          Object value = f.get(obj);

          result.add(name);
          label121: result.add(value);
        }
        sourceClass = sourceClass.getSuperclass();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result.toArray();
  }

	public static Object[] fieldsToArray(Object obj) {
		int levels = 0;
		Class sourceClass = obj.getClass();
		while (!(sourceClass.getName().equals("java.lang.Object"))) {
			++levels;
			sourceClass = sourceClass.getSuperclass();
		}
		--levels;

		return p_fieldsToArray(obj, levels);
	}

	private static String p_fieldsToString(Object obj, int superLevels)
  {
    String[] results = new String[superLevels + 1];
    StringBuffer result = new StringBuffer();
    try {
      Class sourceClass = obj.getClass();

      for (int level = 0; level <= superLevels; ++level) {
        Field[] fields = sourceClass.getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {
          Field f = fields[i];
          int modifier = f.getModifiers();
          if (Modifier.isStatic(modifier))
            break label164:

          f.setAccessible(true);
          String name = f.getName();
          if (name.indexOf("m_") == 0)
            name = name.substring(2);

          Object tempObject = f.get(obj);
          result.append(name);
          result.append("[");
          result.append(tempObject);
          result.append("]");
          if (i < fields.length - 1)
            result.append(",");

          label164: result.append(" ");
        }
        results[(superLevels - level)] = result.toString();
        result.setLength(0);
        sourceClass = sourceClass.getSuperclass();
      }

      for (int i = 0; i <= superLevels; ++i)
        result.append(results[i]);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    return result.toString();
  }

	public static String fieldsToString(Object obj) {
		int levels = 0;
		Class sourceClass = obj.getClass();
		while (!(sourceClass.getName().equals("java.lang.Object"))) {
			++levels;
			sourceClass = sourceClass.getSuperclass();
		}
		--levels;

		return p_fieldsToString(obj, levels);
	}
}
