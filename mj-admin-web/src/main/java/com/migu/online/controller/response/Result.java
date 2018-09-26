package com.migu.online.controller.response;

public class Result {
	private String code = ResultCode.RESULT_SUCCESS;
	private String desc;
	private Object data;
	

	public Result() {
		super();
	}
	
	public Result(Object data) {
		super();
		this.data = data;
	}
	

	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static Result result(String resultCode) {
		return result(resultCode, ResultCode.getValueByKey(resultCode));
	}

	public static Result result(String resultCode, String desc) {
		Result result = new Result();
		result.setCode(resultCode);
		result.setDesc(desc);
		return result;
	}
}
