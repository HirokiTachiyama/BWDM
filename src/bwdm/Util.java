package bwdm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Util {

	public Util() {}

	static boolean isNumber(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}


	public static Object getPrivateField(Object _target_obj, String _field_name)
			throws NoSuchFieldException, IllegalAccessException {
		Class c = _target_obj.getClass();
		Field f = c.getDeclaredField(_field_name);
		f.setAccessible(true);
		return f.get(_target_obj);
	}


	public static Method getPrivateMethod(Object _target_obj, String _method_name)
			throws NoSuchMethodException {
		Class c = _target_obj.getClass();
		Method m = c.getMethod(_method_name);
		m.setAccessible(true);
		return m;
	}

	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	public static void printTestResults(String _expected, String _actual) {
		System.out.println("Exp.:"+_expected+"  Act.:"+_actual);
	}


}
