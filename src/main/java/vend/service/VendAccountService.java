package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendAccount;

public interface VendAccountService {
	/**
	 * 根据输入信息条件查询用户账户列表，并分页显示
	 * @param vendAccount
	 * @param page
	 * @return
	 */
	List<VendAccount> listVendAccount(VendAccount vendAccount,Page page);
	/**
	 * 添加用户账户
	 * @param vendAccount
	 * @return
	 */
	int insertVendAccount(VendAccount vendAccount);
	/**
	 * 修改用户账户
	 * @param vendAccount
	 * @return
	 */
	int editVendAccount(VendAccount vendAccount);
	/**
	 * 删除一个用户账户
	 * @param id
	 */
	void delVendAccount(String usercode);
	/**
	 * 批量删除用户账户
	 * @param id
	 */
	int delVendAccounts(String usercodes[]);
	/**
	 * 根据ID查找一个用户账户
	 * @param id
	 * @return
	 */
	VendAccount getOne(String usercode);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendAccount> findAll();
	
}
