package vend.service;
import java.util.List;

import vend.entity.VendRolePermission;

public interface VendRolePermissionService {
	/**
	 * 按照roleid查找角色权限
	 * @param roleId
	 * @return
	 */
	List<VendRolePermission> selectByRoleId(Integer roleId);
	/**
	 * 修改一位角色
	 * @param vendRolePermission
	 */
	int editVendRolePermission(VendRolePermission vendRolePermission);
	/**
	 * 添加一位角色
	 * @param vendRolePermission
	 */
	int insertVendRolePermission(VendRolePermission vendRolePermission);
	
	/**
	 * 删除一位角色
	 * @param id
	 */
	int deleteVendRolePermission(int id);
	
	/**
	 * 批量删除角色
	 * @param ids
	 */
	void deleteVendRolePermissions(int[] ids);
}
