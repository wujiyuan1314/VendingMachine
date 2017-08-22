package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendRole;

public interface VendRoleService {
	/**
	 * 根据输入信息条件查询角色列表，并分页显示
	 * @param vendRole
	 * @param page
	 * @return
	 */
	List<VendRole> listVendRole(VendRole vendRole,Page page);
	/**
	 * 添加角色
	 * @param vendRole
	 * @return
	 */
	int insertVendRole(VendRole vendRole);
	/**
	 * 修改角色
	 * @param vendRole
	 * @return
	 */
	int editVendRole(VendRole vendRole);
	/**
	 * 删除一个角色
	 * @param id
	 */
	void delVendRole(int id);
	/**
	 * 批量删除角色
	 * @param id
	 */
	int delVendRoles(int id[]);
	/**
	 * 根据ID查找一个角色
	 * @param id
	 * @return
	 */
	VendRole getOne(int id);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendRole> findAll();
	/**
	 * 按角色名字查询
	 * @param roleName
	 * @return
	 */
	VendRole selectByRoleName(String roleName);
	
}
