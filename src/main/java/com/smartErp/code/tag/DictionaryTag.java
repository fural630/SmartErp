package com.smartErp.code.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;


@Component("dictionaryTag")
public class DictionaryTag implements TemplateDirectiveModel{

	@Override
	public void execute(Environment environment, Map params, TemplateModel[] templateModels,
			TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
		String id = null;
	    /** 名字 */
	    String name = null;
	    /** class */
	    String cssClass = null;
	    /** 样式 */
	    String style = null;
	    /** 默认key值 */
	    String headerKey = null;
	    /** 默认value值 */
	    String headerValue = null;
	    /** 默认选择的值 */
	    String selected = null;
	    /**模块ID**/
	    String moduleId = null;
		if (null != params.get("id")) {
			id = params.get("id").toString();
		}
		if (null != params.get("name")) {
			name = params.get("name").toString();
		}
		if (null != params.get("cssClass")) {
			cssClass = params.get("cssClass").toString();
		}
		if (null != params.get("style")) {
			style = params.get("style").toString();
		}
		if (null != params.get("headerKey")) {
			headerKey = params.get("headerKey").toString();
		}
		if (null != params.get("headerValue")) {
			headerValue = params.get("headerValue").toString();
		}
		if (null != params.get("selected")) {
			selected = params.get("selected").toString();
		}
		if (null != params.get("moduleId")) {
			moduleId = params.get("moduleId").toString();
		}
		
		Dictionary dictionary = new Dictionary();
		dictionary.setCssClass(cssClass);
		dictionary.setHeaderKey(headerKey);
		dictionary.setHeaderValue(headerValue);
		dictionary.setId(id);
		dictionary.setModuleId(moduleId);
		dictionary.setName(name);
		dictionary.setSelected(selected);
		dictionary.setStyle(style);
		
		String selectHtml = dictionary.create();
		Writer out = environment.getOut();
		out.write(selectHtml);
		if (templateDirectiveBody != null) {
			templateDirectiveBody.render(out);
		}
		
	}

}
