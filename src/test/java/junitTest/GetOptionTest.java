package junitTest;

import java.lang.reflect.Method;
import java.util.Map;

import com.smartErp.util.code.Dumper;

public class GetOptionTest {
	public static void main(String[] args) {
		
		String type = "CdiscountPublishStatus";
		
		String className = "com.smartErp.application.libraries.select." + type;
		String methodName = "getOptions";
		try {
			Class customClass = Class.forName(className);
			Method method = customClass.getMethod(methodName);
			Object result = method.invoke(customClass.newInstance());
			if ( null != result) {
				Map optionMap = (Map) result;
				Dumper.dump(optionMap);
			}
		} catch (Exception e) {
            e.printStackTrace();
		}
	}
}
