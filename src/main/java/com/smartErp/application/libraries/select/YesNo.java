package com.smartErp.application.libraries.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.smartErp.application.libraries.constentEnum.YesNoEnum;
import com.smartErp.util.code.MyLocale;

public class YesNo {
	
	public Map<String, String> getOptions() {
        Map<String, String> options = new LinkedHashMap<String, String>();
        MyLocale myLocale = new MyLocale();
    	for (YesNoEnum status : YesNoEnum.values()) {
    		options.put(String.valueOf(status.getValue()), myLocale.getText(status.toString()));
    	}
        return options;
    }	
}
