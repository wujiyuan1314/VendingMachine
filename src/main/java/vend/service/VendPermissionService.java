package vend.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendPermission;

public interface VendPermissionService {
	/**
	 * 根据输入信息条件查询权限列表，并分页显示
	 * @param vendPermission
	 * @param page
	 * @return
	 */
	List<VendPermission> listVendPermission(VendPermission vendPermission,Page page);
	/**
	 * 添加权限
	 * @param vendPermission
	 * @return
	 */
	int insertVendPermission(VendPermission vendPermission);
	/**
	 * 修改权限
	 * @param vendPermission
	 * @return
	 */
	int editVendPermission(VendPermission vendPermission);
	/**
	 * 删除一个权限
	 * @param id
	 */
	void delVendPermission(int id);
	/**
	 * 批量删除权限
	 * @param id
	 */
	int delVendPermissions(int id[]);
	/**
	 * 根据ID查找一个权限
	 * @param id
	 * @return
	 */
	VendPermission getOne(int id);
	/**
	 * 按参数查找
	 * @param vendPermission
	 * @return
	 */
	List<VendPermission> selectByParams(VendPermission vendPermission);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendPermission> findAll();
	/**
	 * 按权限名字查询
	 * @param roleName
	 * @return
	 */
	VendPermission selectByPermissionName(String permissionName);
	
}
