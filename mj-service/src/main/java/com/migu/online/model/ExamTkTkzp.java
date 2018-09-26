package com.migu.online.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//题库图片
@Alias("exam_tk_tkzp")
@Data
public class ExamTkTkzp implements Serializable {

	private static final long serialVersionUID = -5516786843814211249L;

	private String stbb; // 试题版本
	private String stxh; // 试题序号
	private String txlj;// 图像路径
	private String zplx; // 图片类型 1：图片 3：flv 动画文件
	private String kstzp;// blog图片存储

	private Date gxsj; // 更新时间
	private String sernames; // 服务器集合

	public String getStbb() {
		return stbb;
	}

	public void setStbb(String stbb) {
		this.stbb = stbb;
	}

	public String getStxh() {
		return stxh;
	}

	public void setStxh(String stxh) {
		this.stxh = stxh;
	}

	public String getTxlj() {
		return txlj;
	}

	public void setTxlj(String txlj) {
		this.txlj = txlj;
	}

	public String getZplx() {
		return zplx;
	}

	public void setZplx(String zplx) {
		this.zplx = zplx;
	}

	public String getKstzp() {
		return kstzp;
	}

	public void setKstzp(String kstzp) {
		this.kstzp = kstzp;
	}

	public Date getGxsj() {
		return gxsj;
	}

	public void setGxsj(Date gxsj) {
		this.gxsj = gxsj;
	}

	public String getSernames() {
		return sernames;
	}

	public void setSernames(String sernames) {
		this.sernames = sernames;
	}
}
