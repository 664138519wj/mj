package com.migu.online.model.system;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by Liaopan on 2018/1/10.
 */
@Data
@Table(name = "sys_role")
@Alias("sysRole")
public class SysRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述
    private Boolean available = Boolean.FALSE; // 是否可用

    @Transient
    private List<SysUser> users;

    @Transient
    private List<SysPermission> permissions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<SysUser> getUsers() {
		return users;
	}

	public void setUsers(List<SysUser> users) {
		this.users = users;
	}

	public List<SysPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}
    
    
}
