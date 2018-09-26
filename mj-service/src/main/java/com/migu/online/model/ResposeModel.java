package com.migu.online.model;

import org.apache.ibatis.type.Alias;

@Alias("resposeModelModel")
public class ResposeModel {
	public ResposeModel() {
		status = "1";
		code = "000000";
		msg = "";
		data = "";
	}

	private String status;
	private String code;
	private String msg;
	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
