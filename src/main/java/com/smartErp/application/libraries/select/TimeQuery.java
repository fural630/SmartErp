package com.smartErp.application.libraries.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.smartErp.application.libraries.constentEnum.TimeQueryEnum;
import com.smartErp.util.code.MyLocale;

public class TimeQuery {
	public Map<String, String> getOptions() {
        Map<String, String> options = new LinkedHashMap<String, String>();
        MyLocale myLocale = new MyLocale();
    	for (TimeQueryEnum status : TimeQueryEnum.values()) {
    		options.put(status.getValue(), myLocale.getText(status.toString()));
    	}
        return options;
    }
}
