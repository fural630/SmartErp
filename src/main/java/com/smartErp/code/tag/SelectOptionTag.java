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


@Component("selectOptionTag")
public class SelectOptionTag implements TemplateDirectiveModel{

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Environment environment, Map params, TemplateModel[] templateModels,
			TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
		SelectOption selectOption = initeParam(params);
		String className = "com.smartErp.application.libraries.select." + selectOption.getOptionClass();
		String methodName = "getOptions";
		Map<String, String> optionMap = null;
		try {
			Class customClass = Class.forName(className);
			Method method = customClass.getMethod(methodName);
			Object result = method.invoke(customClass.newInstance());
			if ( null != result) {
				optionMap = (Map<String, String>) result;
				selectOption.setOptionMap(optionMap);
			}
		} catch (Exception e) {
            e.printStackTrace();
		}
		String selectHtml = selectOption.parse();
		Writer out = environment.getOut();
		out.write(selectHtml);
		if (templateDirectiveBody != null) {
			templateDirectiveBody.render(out);
		}
		
	}

	private SelectOption initeParam(Map params) {
		SelectOption selectOption = new SelectOption();
		selectOption.setId( (null == params.get("id")) ? null :  params.get("id").toString());
		selectOption.setName( (null == params.get("name")) ? null :  params.get("name").toString());
		selectOption.setCssClass( (null == params.get("cssClass")) ? null :  params.get("cssClass").toString());
		selectOption.setStyle( (null == params.get("style")) ? null :  params.get("style").toString());
		selectOption.setHeaderKey( (null == params.get("headerKey")) ? null :  params.get("headerKey").toString());
		selectOption.setHeaderValue( (null == params.get("headerValue")) ? null :  params.get("headerValue").toString());
		selectOption.setSelected( (null == params.get("selected")) ? null :  params.get("selected").toString());
		selectOption.setOptionClass( (null == params.get("optionClass")) ? null :  params.get("optionClass").toString());
		return selectOption;
	}

}
