package com.smartErp.application.libraries.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.smartErp.application.libraries.constentEnum.CdiscountPublishStatusEnum;
import com.smartErp.util.code.MyLocale;

public class CdiscountPublishStatus {
	
	public Map<String, String> getOptions() {
        Map<String, String> options = new LinkedHashMap<String, String>();
        MyLocale myLocale = new MyLocale();
    	for (CdiscountPublishStatusEnum status : CdiscountPublishStatusEnum.values()) {
    		options.put(String.valueOf(status.getValue()), myLocale.getText(status.toString()));
    	}
        return options;
    }	
}
