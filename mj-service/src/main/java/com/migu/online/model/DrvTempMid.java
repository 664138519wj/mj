package com.migu.online.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//历史库，驾校报名情况
@Data
@Table(name = "drv_temp_mid")
@Alias("drvTempMId")
public class DrvTempMid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4435154743575589449L;
	private String lsh; // '流水号',
	private String sfzmhm;// '身份证号',
	private String sfzmmc;// '证件名称',
	private String hmcd;// ,
	private String xm;// '姓名',
	private String xb;// '性别',
	private Date csrq;// '出生日期',
	private String gj;// '国籍',
	private String djzsxzqh;// '登记住所行政区划',
	private String djzsxxdz;// '登记住所详细地址',
	private String lxzsxzqh;// '联系住所行政区划',
	private String lxzsxxdz;// '联系住所详细地址',
	private String lxzsyzbm;// '联系住所邮政编码',
	private String ly;// '来源',
	private String xzqh;// '行政区划',
	private String lxdh;// '联系电话',
	private String zzzm;// '暂住证号码',
	private String zkzmbh;// '准考证明编号',
	private String dabh;// '档案编号',
	private String zkcx;// '准考车型',
	private String jxmc;// '驾校名称',
	private Integer sg;// '身高',
	private Double zsl;// '左视力',
	private Double ysl;// '右视力',
	private String bsl;// '辩色力',
	private String tl;// '听力',
	private String sz;// '上肢',
	private String zxz;// '左下肢',
	private String yxz;// '右下肢',
	private String qgjb;// '躯干颈部',
	private Date tjrq;// '体检日期',
	private String tjyymc;// '体检医院名称',
	private String bjid;// '班级ID',
	private String gdbz;// '备注',
	private Date bmrq;// '报名日期',
	private Integer km1cj;// '科目一成绩',
	private Date km1ksrq;// '科目一考试日期',
	private String bmlx;// '报名类型',
	private Integer km2cj;// '科目二成绩',
	private Date km2ksrq;// '科目二考试日期',
	private Integer km3cj;// '科目三成绩',
	private Date km3ksrq;// '科目三考试日期',
	private String sqlx;// '申请类型',
	private String zjcx;// '准驾车型',
	private String bz;// '备注',
	private Integer xyid;// '学员ID',
	private String csbz;// '传输标志',
	private String glbm;// '管理部门',
	private String lrr;// '录入人',
	private Date lrsj;// '录入时间',
	private String sjhm;// '手机号码',
	private String dzyx;// '邮箱',
	private Integer examorder;// '预约考试0为未预约，1为科目一考试预约，2为科目二考试预约，3为科目三考试预约，4为科目4考试预约',
	private Integer km4cj;// '科目四成绩',
	private Date km4ksrq;// '科目四考试日期',
	private Integer bmsuc;// '0为未提交报名，1为已提交报名，2为提交报名成功',
	private Integer km1kscs;// '科目一考试次数',
	private Integer km2kscs;// '科目二考试次数',
	private Integer km3kscs;// '科目三考试次数',
	private Integer km4kscs;// '科目四考试次数',
	private Integer yyspsy;
	private String dyslze;

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getSfzmmc() {
		return sfzmmc;
	}

	public void setSfzmmc(String sfzmmc) {
		this.sfzmmc = sfzmmc;
	}

	public String getHmcd() {
		return hmcd;
	}

	public void setHmcd(String hmcd) {
		this.hmcd = hmcd;
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

	public Date getCsrq() {
		return csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}

	public String getGj() {
		return gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getDjzsxzqh() {
		return djzsxzqh;
	}

	public void setDjzsxzqh(String djzsxzqh) {
		this.djzsxzqh = djzsxzqh;
	}

	public String getDjzsxxdz() {
		return djzsxxdz;
	}

	public void setDjzsxxdz(String djzsxxdz) {
		this.djzsxxdz = djzsxxdz;
	}

	public String getLxzsxzqh() {
		return lxzsxzqh;
	}

	public void setLxzsxzqh(String lxzsxzqh) {
		this.lxzsxzqh = lxzsxzqh;
	}

	public String getLxzsxxdz() {
		return lxzsxxdz;
	}

	public void setLxzsxxdz(String lxzsxxdz) {
		this.lxzsxxdz = lxzsxxdz;
	}

	public String getLxzsyzbm() {
		return lxzsyzbm;
	}

	public void setLxzsyzbm(String lxzsyzbm) {
		this.lxzsyzbm = lxzsyzbm;
	}

	public String getLy() {
		return ly;
	}

	public void setLy(String ly) {
		this.ly = ly;
	}

	public String getXzqh() {
		return xzqh;
	}

	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getZzzm() {
		return zzzm;
	}

	public void setZzzm(String zzzm) {
		this.zzzm = zzzm;
	}

	public String getZkzmbh() {
		return zkzmbh;
	}

	public void setZkzmbh(String zkzmbh) {
		this.zkzmbh = zkzmbh;
	}

	public String getDabh() {
		return dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	public String getZkcx() {
		return zkcx;
	}

	public void setZkcx(String zkcx) {
		this.zkcx = zkcx;
	}

	public String getJxmc() {
		return jxmc;
	}

	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}
	
	public Integer getSg() {
		return sg;
	}

	public void setSg(Integer sg) {
		this.sg = sg;
	}

	public Double getZsl() {
		return zsl;
	}

	public void setZsl(Double zsl) {
		this.zsl = zsl;
	}

	public Double getYsl() {
		return ysl;
	}

	public void setYsl(Double ysl) {
		this.ysl = ysl;
	}

	public String getBsl() {
		return bsl;
	}

	public void setBsl(String bsl) {
		this.bsl = bsl;
	}

	public String getTl() {
		return tl;
	}

	public void setTl(String tl) {
		this.tl = tl;
	}

	public String getSz() {
		return sz;
	}

	public void setSz(String sz) {
		this.sz = sz;
	}

	public String getZxz() {
		return zxz;
	}

	public void setZxz(String zxz) {
		this.zxz = zxz;
	}

	public String getYxz() {
		return yxz;
	}

	public void setYxz(String yxz) {
		this.yxz = yxz;
	}

	public String getQgjb() {
		return qgjb;
	}

	public void setQgjb(String qgjb) {
		this.qgjb = qgjb;
	}

	public Date getTjrq() {
		return tjrq;
	}

	public void setTjrq(Date tjrq) {
		this.tjrq = tjrq;
	}

	public String getTjyymc() {
		return tjyymc;
	}

	public void setTjyymc(String tjyymc) {
		this.tjyymc = tjyymc;
	}

	public String getBjid() {
		return bjid;
	}

	public void setBjid(String bjid) {
		this.bjid = bjid;
	}

	public String getGdbz() {
		return gdbz;
	}

	public void setGdbz(String gdbz) {
		this.gdbz = gdbz;
	}

	public Date getBmrq() {
		return bmrq;
	}

	public void setBmrq(Date bmrq) {
		this.bmrq = bmrq;
	}

	public Integer getKm1cj() {
		return km1cj;
	}

	public void setKm1cj(Integer km1cj) {
		this.km1cj = km1cj;
	}

	public Date getKm1ksrq() {
		return km1ksrq;
	}

	public void setKm1ksrq(Date km1ksrq) {
		this.km1ksrq = km1ksrq;
	}

	public String getBmlx() {
		return bmlx;
	}

	public void setBmlx(String bmlx) {
		this.bmlx = bmlx;
	}

	public Integer getKm2cj() {
		return km2cj;
	}

	public void setKm2cj(Integer km2cj) {
		this.km2cj = km2cj;
	}

	public Date getKm2ksrq() {
		return km2ksrq;
	}

	public void setKm2ksrq(Date km2ksrq) {
		this.km2ksrq = km2ksrq;
	}

	public Integer getKm3cj() {
		return km3cj;
	}

	public void setKm3cj(Integer km3cj) {
		this.km3cj = km3cj;
	}

	public Date getKm3ksrq() {
		return km3ksrq;
	}

	public void setKm3ksrq(Date km3ksrq) {
		this.km3ksrq = km3ksrq;
	}

	public String getSqlx() {
		return sqlx;
	}

	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}

	public String getZjcx() {
		return zjcx;
	}

	public void setZjcx(String zjcx) {
		this.zjcx = zjcx;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Integer getXyid() {
		return xyid;
	}

	public void setXyid(Integer xyid) {
		this.xyid = xyid;
	}

	public String getCsbz() {
		return csbz;
	}

	public void setCsbz(String csbz) {
		this.csbz = csbz;
	}

	public String getGlbm() {
		return glbm;
	}

	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getDzyx() {
		return dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public Integer getExamorder() {
		return examorder;
	}

	public void setExamorder(Integer examorder) {
		this.examorder = examorder;
	}

	public Integer getKm4cj() {
		return km4cj;
	}

	public void setKm4cj(Integer km4cj) {
		this.km4cj = km4cj;
	}

	public Date getKm4ksrq() {
		return km4ksrq;
	}

	public void setKm4ksrq(Date km4ksrq) {
		this.km4ksrq = km4ksrq;
	}

	public Integer getBmsuc() {
		return bmsuc;
	}

	public void setBmsuc(Integer bmsuc) {
		this.bmsuc = bmsuc;
	}

	public Integer getKm1kscs() {
		return km1kscs;
	}

	public void setKm1kscs(Integer km1kscs) {
		this.km1kscs = km1kscs;
	}

	public Integer getKm2kscs() {
		return km2kscs;
	}

	public void setKm2kscs(Integer km2kscs) {
		this.km2kscs = km2kscs;
	}

	public Integer getKm3kscs() {
		return km3kscs;
	}

	public void setKm3kscs(Integer km3kscs) {
		this.km3kscs = km3kscs;
	}

	public Integer getKm4kscs() {
		return km4kscs;
	}

	public void setKm4kscs(Integer km4kscs) {
		this.km4kscs = km4kscs;
	}

	public Integer getYyspsy() {
		return yyspsy;
	}

	public void setYyspsy(Integer yyspsy) {
		this.yyspsy = yyspsy;
	}

	public String getDyslze() {
		return dyslze;
	}

	public void setDyslze(String dyslze) {
		this.dyslze = dyslze;
	}

}
