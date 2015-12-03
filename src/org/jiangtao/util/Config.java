package org.jiangtao.util;

import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class Config {
public static JsonConfig getConfig(){
	JsonConfig jsonConfig = new JsonConfig();
	jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessor() {
	  private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	  public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
	    return  value == null ?"" : sd.format(value);
	  }
	  public Object processArrayValue(Object value, JsonConfig jsonConfig) {
	    return null;
	  }
	});
	return jsonConfig;
}
}
