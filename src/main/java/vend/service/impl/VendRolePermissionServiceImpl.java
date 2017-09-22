package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
import vend.dao.VendRolePermissionMapper;
import vend.entity.VendRolePermission;
import vend.service.VendRolePermissionService;
@Service
public class VendRolePermissionServiceImpl implements VendRolePermissionService {
	@Autowired
	VendRolePermissionMapper vendRolePermissionMapper;
	/**
	 * 按照roleid查找角色权限权限
	 * @param roleId
	 * @return
	 */
	public List<VendRolePermission> selectByRoleId(Integer roleId){
		String key="key_selectByRoleId"+roleId;
		List<VendRolePermission> vendRolePermissions=(List<VendRolePermission>)CacheUtils.get("permissionCache", key);
		if(vendRolePermissions==null){
			vendRolePermissions=vendRolePermissionMapper.selectByRoleId(roleId);
			CacheUtils.put("permissionCache", key, vendRolePermissions);
		}
		return vendRolePermissions;
	}
	/**
	 * 修改一位角色权限
	 * @param vendRolePermission
	 */
	public int editVendRolePermission(VendRolePermission vendRolePermission){
		int isOk= vendRolePermissionMapper.updateByPrimaryKeySelective(vendRolePermission);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 添加一位角色权限
	 * @param vendRolePermission
	 */
	public int insertVendRolePermission(VendRolePermission vendRolePermission){
		int isOk= vendRolePermissionMapper.insertSelective(vendRolePermission);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	
	/**
	 * 删除一位角色权限
	 * @param id
	 */
	public int deleteVendRolePermission(int id){
		int isOk= vendRolePermissionMapper.deleteByPrimaryKey(id);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	
	/**
	 * 批量删除角色权限
	 * @param ids
	 */
	public void deleteVendRolePermissions(int[] ids){
		for(int id:ids){
			vendRolePermissionMapper.deleteByPrimaryKey(id);
		}
	}
}
