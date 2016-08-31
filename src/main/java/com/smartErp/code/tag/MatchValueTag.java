package com.smartErp.code.tag;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.smartErp.util.code.Dumper;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("matchValueTag")
public class MatchValueTag implements TemplateDirectiveModel{
	@Override
	public void execute(Environment environment, Map params, TemplateModel[] templateModels,
			TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
		String key = params.get("key").toString();
		String optionClass = params.get("optionClass").toString();
		
		String className = "com.smartErp.application.libraries.select." + optionClass;
		String methodName = "getOptions";
		Map<String, String> optionMap = null;
		try {
			Class customClass = Class.forName(className);
			Method method = customClass.getMethod(methodName);
			Object result = method.invoke(customClass.newInstance());
			if ( null != result) {
				optionMap = (Map<String, String>) result;
			}
		} catch (Exception e) {
            e.printStackTrace();
		}
		
		String valueHtml = "";
		if (!optionMap.isEmpty()) {
			valueHtml = optionMap.get(String.valueOf(key));
		}
		Writer out = environment.getOut();
		out.write(valueHtml);
		if (templateDirectiveBody != null) {
			templateDirectiveBody.render(out);
		}
	}
}
