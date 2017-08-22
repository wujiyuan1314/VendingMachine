package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendUser;

public interface VendUserService {
	/**
	 * 根据输入信息条件查询用户列表，并分页显示
	 * @param vendUser
	 * @param page
	 * @return
	 */
	List<VendUser> listVendUser(VendUser vendUser,Page page);
	/**
	 * 添加用户
	 * @param vendUser
	 * @return
	 */
	int insertVendUser(VendUser vendUser);
	/**
	 * 修改用户
	 * @param vendUser
	 * @return
	 */
	int editVendUser(VendUser vendUser);
	/**
	 * 删除一个用户
	 * @param id
	 */
	void delVendUser(String usercode);
	/**
	 * 批量删除用户
	 * @param id
	 */
	int delVendUsers(String usercodes[]);
	/**
	 * 根据ID查找一个用户
	 * @param id
	 * @return
	 */
	VendUser getOne(String usercode);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendUser> findAll();
	
}
