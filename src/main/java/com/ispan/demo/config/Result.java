package com.ispan.demo.config;

import java.util.HashMap;
import java.util.Map;
//@Data
public class Result<T> {
	private Integer code;	//編碼:1成功, 0和其他數據失敗
	private T data;	//數據
	private String msg;		//錯誤訊息
	
	private Map map = new HashMap<>();  //動態數據
	
	public static <T>Result<T> success(T object){
		Result<T> r = new Result<T>();

		r.code = 1;
		r.data = object;

		return r;
	}
	
	public static <T>Result<T> error(String msg){
		Result<T> r = new Result<T>();
		r.code = 0;
		r.msg = msg;
		return r;
	}
	
	public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Result() {
		super();
	}
	
	

}
