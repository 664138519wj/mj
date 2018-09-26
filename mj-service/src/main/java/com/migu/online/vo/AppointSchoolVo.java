package com.migu.online.vo;

import java.math.BigDecimal;

//预约
public class AppointSchoolVo {

    private Integer id;
    private String name;
    private BigDecimal price;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
   
}
