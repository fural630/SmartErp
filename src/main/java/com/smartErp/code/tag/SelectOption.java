package com.smartErp.code.tag;

import java.util.Iterator;
import java.util.Map;



public class SelectOption {
	private String id;
    /** 名字 */
    private String name;
    /** class */
    private String cssClass;
    /** 样式 */
    private String style;
    /** 默认key值 */
    private String headerKey;
    /** 默认value值 */
    private String headerValue;
    /** 默认选择的值 */
    private String selected;
    
    private String optionClass;
    
    private String method;
    
    private Map<String, String> optionMap;
    
	public String parse() {
		StringBuffer sb = new StringBuffer("<select ");
		if (this.id != null) {
            sb.append(" id=\"");
            sb.append(this.id);
            sb.append("\"");
        }
        // name属性
        if (this.name != null) {
            sb.append(" name=\"");
            sb.append(this.name);
            sb.append("\"");
        }
        // cssClass属性
        if (this.cssClass != null) {
            sb.append(" class=\"");
            sb.append(this.cssClass);
            sb.append("\"");
        }
        // style属性
        if (this.style != null) {
            sb.append(" style=\"");
            sb.append(this.style);
            sb.append("\"");
        }
        sb.append(">");
        if (this.headerKey != null && this.headerValue != null) {
            sb.append("<option value=\"");
            sb.append(this.headerKey);
            sb.append("\"");
            if (this.selected != null && this.selected.equals(this.headerKey)) {
                sb.append(" selected=\"selected\"");
            }
            sb.append(">");
            sb.append(this.headerValue);
            sb.append("</option>");
        }
        
        if (null != optionMap && optionMap.size() > 0) {
	        Iterator iterator = optionMap.entrySet().iterator();
	        while (iterator.hasNext()) {
	            Map.Entry entry = (Map.Entry) iterator.next();  
	            String key = (String) entry.getKey();  
	            String value = (String) entry.getValue();  
	            sb.append("<option value=\"");
				sb.append(key);
				sb.append("\"");
				if (this.selected != null && this.selected.equals(key)) {
					sb.append(" selected=\"selected\"");
				}
				sb.append(">");
				sb.append(value);
				sb.append("</option>");
	        }
        }
        sb.append("</select>");
		return sb.toString();
	}
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getHeaderKey() {
		return headerKey;
	}
	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}
	public String getHeaderValue() {
		return headerValue;
	}
	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}

	public Map<String, String> getOptionMap() {
		return optionMap;
	}

	public void setOptionMap(Map<String, String> optionMap) {
		this.optionMap = optionMap;
	}

	public String getOptionClass() {
		return optionClass;
	}

	public void setOptionClass(String optionClass) {
		this.optionClass = optionClass;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
