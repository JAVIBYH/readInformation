package com.briup.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * 咨询栏目分类表
 * @author Administrator
 *
 */
public class Category {
	private Long id;
	private String name;
	private String description;
	private Category parent;
	private Set<Category> subCategorys = new HashSet<>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public Set<Category> getSubCategorys() {
		return subCategorys;
	}
	public void setSubCategorys(Set<Category> subCategorys) {
		this.subCategorys = subCategorys;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", subCategorys="
				+ subCategorys==null?"0":subCategorys.size() + "]";
	}
	
}
