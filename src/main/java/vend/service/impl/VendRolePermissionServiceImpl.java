package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vend.dao.VendRolePermissionMapper;
import vend.entity.VendRolePermission;
import vend.service.VendRolePermissionService;
@Service
public class VendRolePermissionServiceImpl implements VendRolePermissionService {
	@Autowired
	VendRolePermissionMapper vendRolePermissionMapper;
	/**
	 * 按照roleid查找角色权限
	 * @param roleId
	 * @return
	 */
	public List<VendRolePermission> selectByRoleId(Integer roleId){
		return vendRolePermissionMapper.selectByRoleId(roleId);
	}
	/**
	 * 修改一位角色
	 * @param vendRolePermission
	 */
	public int editVendRolePermission(VendRolePermission vendRolePermission){
		return vendRolePermissionMapper.updateByPrimaryKeySelective(vendRolePermission);
	}
	/**
	 * 添加一位角色
	 * @param vendRolePermission
	 */
	public int insertVendRolePermission(VendRolePermission vendRolePermission){
		return vendRolePermissionMapper.insertSelective(vendRolePermission);
	}
	
	/**
	 * 删除一位角色
	 * @param id
	 */
	public int deleteVendRolePermission(int id){
		return vendRolePermissionMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 批量删除角色
	 * @param ids
	 */
	public void deleteVendRolePermissions(int[] ids){
		for(int id:ids){
			vendRolePermissionMapper.deleteByPrimaryKey(id);
		}
	}
}
