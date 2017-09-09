package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.DateUtil;
import base.util.Page;
import vend.dao.VendRoleMapper;
import vend.entity.VendRole;
import vend.service.VendRoleService;

@Service
public class VendRoleServiceImpl implements VendRoleService {
	@Autowired
	VendRoleMapper vendRoleMapper;
	/**
	 * 根据输入信息条件查询角色列表，并分页显示
	 * @param vendRole
	 * @param page
	 * @return
	 */
	public List<VendRole> listVendRole(VendRole vendRole,Page page){
		int totalNumber = vendRoleMapper.countVendRole(vendRole);
		page.setTotalNumber(totalNumber);
		return vendRoleMapper.listVendRole(vendRole, page);
	}
	/**
	 * 添加角色
	 * @param vendRole
	 * @return
	 */
	public int insertVendRole(VendRole vendRole){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendRole.setCreateTime(createTime);
		vendRole.setUpdateTime(createTime);
		return vendRoleMapper.insertSelective(vendRole);
	}
	/**
	 * 修改角色
	 * @param vendRole
	 * @return
	 */
	public int editVendRole(VendRole vendRole){
		return vendRoleMapper.updateByPrimaryKeySelective(vendRole);
	}
	/**
	 * 删除一个角色
	 * @param id
	 */
	public void delVendRole(int id){
		vendRoleMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 批量删除角色
	 * @param id
	 */
	public int delVendRoles(int ids[]){
		return vendRoleMapper.deleteBatch(ids);
	}
	/**
	 * 根据ID查找一个角色
	 * @param id
	 * @return
	 */
	public VendRole getOne(int id){
		return vendRoleMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查找全部
	 * @return
	 */
	public List<VendRole> findAll() {
		// TODO Auto-generated method stub
		return vendRoleMapper.findAll();
	}
	/**
	 * 查找下一级
	 * @param parentId
	 * @return
	 */
	public List<VendRole> findNext(Integer roleId){
		return vendRoleMapper.findNext(roleId);
	}
	/**
	 * 查找全部下一级
	 * @param roleId
	 * @return
	 */
	public List<VendRole> findNextAll(Integer roleId){
		return vendRoleMapper.findNextAll(roleId);
	}
	/**
	 * 按角色名字查询
	 * @param roleName
	 * @return
	 */
	public VendRole selectByRoleName(String roleName){
		return vendRoleMapper.selectByRoleName(roleName);
	}
}
