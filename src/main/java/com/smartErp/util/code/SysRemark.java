package com.smartErp.util.code;

import org.apache.commons.lang.StringUtils;

import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.model.User;

public class SysRemark {
	public static String startFix = "【";
	public static String endFix = "】";
	public static String newLine = "<br/>";

	public SysRemark() {
	}

	private static String _fix(String remark) {
		return startFix + remark + endFix;
	}

	public static String append(String existsRemark, String newRemark) {
		int rows = StringUtils.countMatches(existsRemark, startFix);
		String newIndex = new Integer(rows + 1).toString() + ": ";
		if ((null == existsRemark) || (existsRemark.isEmpty())) {
			existsRemark = "";
		} else {
			existsRemark = existsRemark + newLine;
		}

		return existsRemark + newIndex + _fix(newRemark);
	}

	public static String appendMore(String existsRemark, String newRemark) {
		int rows = StringUtils.countMatches(existsRemark, startFix);
		String newIndex = new Integer(rows + 1).toString() + ": ";
		if ((null == existsRemark) || (existsRemark.isEmpty())) {
			existsRemark = "";
		} else {
			existsRemark = existsRemark + newLine;
		}

		MyDate mydate = new MyDate();
		User user = UserSingleton.getInstance().getUser();
		String before = "于 " + mydate.getCurrentTime() + " 由 " + user.getName()
				+ " 操作, ";

		return existsRemark + newIndex + startFix + before + newRemark + endFix;
	}
}
