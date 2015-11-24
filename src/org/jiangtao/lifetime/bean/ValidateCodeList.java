package org.jiangtao.lifetime.bean;

import java.util.ArrayList;
import java.util.List;

public class ValidateCodeList {
private static ValidateCodeList validateCode;
public static List<String> validateList = new ArrayList<>();
private ValidateCodeList(){}
public static ValidateCodeList getInstance() {
	if (validateCode==null) {
		validateCode = new ValidateCodeList();
	}
	return validateCode;
}

}
