package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import base.util.DateUtil;
import base.util.Page;
import vend.dao.VendPermissionMapper;
import vend.entity.VendPermission;
import vend.service.VendPermissionService;

@Service
public class VendPermissionServiceImpl implements VendPermissionService {
	@Autowired
	VendPermissionMapper vendPermissionMapper;
	/**
	 * 根据输入信息条件查询权限列表，并分页显示
	 * @param vendPermission
	 * @param page
	 * @return
	 */
	 @Cacheable(value="permissionCache")
	public List<VendPermission> listVendPermission(VendPermission vendPermission,Page page){
		int totalNumber = vendPermissionMapper.countVendPermission(vendPermission);
		page.setTotalNumber(totalNumber);
		return vendPermissionMapper.listVendPermission(vendPermission, page);
	}
	/**
	 * 添加权限
	 * @param vendPermission
	 * @return
	 */
	public int insertVendPermission(VendPermission vendPermission){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendPermission.setCreateTime(createTime);
		vendPermission.setUpdateTime(createTime);
		return vendPermissionMapper.insertSelective(vendPermission);
	}
	/**
	 * 修改权限
	 * @param vendPermission
	 * @return
	 */
	public int editVendPermission(VendPermission vendPermission){
		return vendPermissionMapper.updateByPrimaryKeySelective(vendPermission);
	}
	/**
	 * 删除一个权限
	 * @param id
	 */
	public void delVendPermission(int id){
		vendPermissionMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 批量删除权限
	 * @param id
	 */
	public int delVendPermissions(int ids[]){
		return vendPermissionMapper.deleteBatch(ids);
	}
	/**
	 * 根据ID查找一个权限
	 * @param id
	 * @return
	 */
	@Cacheable(value="permissionCache")
	public VendPermission getOne(int id){
		return vendPermissionMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查找全部
	 * @return
	 */
	@Cacheable(value="permissionCache")
	public List<VendPermission> findAll() {
		// TODO Auto-generated method stub
		return vendPermissionMapper.findAll();
	}
	/**
	 * 按权限名字查询
	 * @param roleName
	 * @return
	 */
	@Cacheable(value="permissionCache")
	public VendPermission selectByPermissionName(String permissionName){
		return vendPermissionMapper.selectByPermissionName(permissionName);
	}
}
