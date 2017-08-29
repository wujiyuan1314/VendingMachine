package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.DateUtil;
import base.util.Function;
import base.util.Page;
import vend.dao.VendAccountMapper;
import vend.entity.VendAccount;
import vend.service.VendAccountService;

@Service
public class VendAccountServiceImpl implements VendAccountService {
	@Autowired
	VendAccountMapper vendAccountMapper;
	/**
	 * 根据输入信息条件查询用户账户列表，并分页显示
	 * @param vendAccount
	 * @param page
	 * @return
	 */
	public List<VendAccount> listVendAccount(VendAccount vendAccount,Page page){
		int totalNumber = vendAccountMapper.countVendAccount(vendAccount);
		page.setTotalNumber(totalNumber);
		return vendAccountMapper.listVendAccount(vendAccount, page);
	}
	/**
	 * 添加用户账户
	 * @param vendAccount
	 * @return
	 */
	public int insertVendAccount(VendAccount vendAccount){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		String usercode=Function.getUsercode();
		vendAccount.setUsercode(usercode);
		vendAccount.setCreateTime(createTime);
		vendAccount.setUpdateTime(createTime);
		return vendAccountMapper.insertSelective(vendAccount);
	}
	/**
	 * 修改用户账户
	 * @param vendAccount
	 * @return
	 */
	public int editVendAccount(VendAccount vendAccount){
		return vendAccountMapper.updateByPrimaryKeySelective(vendAccount);
	}
	/**
	 * 删除一个用户账户
	 * @param id
	 */
	public void delVendAccount(String usercode){
		vendAccountMapper.deleteByPrimaryKey(usercode);
	}
	/**
	 * 批量删除用户账户
	 * @param id
	 */
	public int delVendAccounts(String usercodes[]){
		return vendAccountMapper.deleteBatch(usercodes);
	}
	/**
	 * 根据ID查找一个用户账户
	 * @param id
	 * @return
	 */
	public VendAccount getOne(String usercode){
		return vendAccountMapper.selectByPrimaryKey(usercode);
	}
	/**
	 * 查找全部
	 */
	public List<VendAccount> findAll() {
		// TODO Auto-generated method stub
		return vendAccountMapper.findAll();
	}
}
