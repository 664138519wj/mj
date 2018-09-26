package com.migu.online.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//原始数据
@Alias("drv_sys_user")
@Data
public class DrvSysUser {

	private String yhdh;
	private Integer yhlx;
	private String xm;
	private String xb;
	private String mm;
	private Integer zhjy;
	private Integer xgmm;
	private String glbm;
	private Date mmyxq;
	private Date yhyxq;
	private String ipstart;
	private String ipend;
	private Integer dlcgcs;
	private Integer dlsbcs;
	private Date scdlsj;
	private String bz;
	private Integer roleid;
	public String getYhdh() {
		return yhdh;
	}
	public void setYhdh(String yhdh) {
		this.yhdh = yhdh;
	}
	public Integer getYhlx() {
		return yhlx;
	}
	public void setYhlx(Integer yhlx) {
		this.yhlx = yhlx;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public Integer getZhjy() {
		return zhjy;
	}
	public void setZhjy(Integer zhjy) {
		this.zhjy = zhjy;
	}
	public Integer getXgmm() {
		return xgmm;
	}
	public void setXgmm(Integer xgmm) {
		this.xgmm = xgmm;
	}
	public String getGlbm() {
		return glbm;
	}
	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}
	public Date getMmyxq() {
		return mmyxq;
	}
	public void setMmyxq(Date mmyxq) {
		this.mmyxq = mmyxq;
	}
	public Date getYhyxq() {
		return yhyxq;
	}
	public void setYhyxq(Date yhyxq) {
		this.yhyxq = yhyxq;
	}
	public String getIpstart() {
		return ipstart;
	}
	public void setIpstart(String ipstart) {
		this.ipstart = ipstart;
	}
	public String getIpend() {
		return ipend;
	}
	public void setIpend(String ipend) {
		this.ipend = ipend;
	}
	public Integer getDlcgcs() {
		return dlcgcs;
	}
	public void setDlcgcs(Integer dlcgcs) {
		this.dlcgcs = dlcgcs;
	}
	public Integer getDlsbcs() {
		return dlsbcs;
	}
	public void setDlsbcs(Integer dlsbcs) {
		this.dlsbcs = dlsbcs;
	}
	public Date getScdlsj() {
		return scdlsj;
	}
	public void setScdlsj(Date scdlsj) {
		this.scdlsj = scdlsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	
}
