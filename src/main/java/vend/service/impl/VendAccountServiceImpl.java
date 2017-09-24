package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
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
		String title=vendAccount.getUsercode();
		String currentPage=Integer.toString(page.getCurrentPage());
		if(title==null){
			title="";
		}
		String key="key_listVendAccount"+title+currentPage;
		List<VendAccount> vendAccounts=(List<VendAccount>)CacheUtils.get("accountCache", key);
		if(vendAccounts==null){
			vendAccounts=vendAccountMapper.listVendAccount(vendAccount, page);
			CacheUtils.put("accountCache", key, vendAccounts);
		}
		
		return vendAccounts;
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
		int isOk=vendAccountMapper.insertSelective(vendAccount);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
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
		int isOk=vendAccountMapper.deleteByPrimaryKey(usercode);
		if(isOk==1){
			CacheUtils.clear();
		}
	}
	/**
	 * 批量删除用户账户
	 * @param id
	 */
	public int delVendAccounts(String usercodes[]){
		int isOk= vendAccountMapper.deleteBatch(usercodes);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 根据ID查找一个用户账户
	 * @param id
	 * @return
	 */
	public VendAccount getOne(String usercode){
		String key="key_VendAccount_getOne"+usercode;
		VendAccount vendAccount=(VendAccount)CacheUtils.get("accountCache", key);
		if(vendAccount==null){
			vendAccount=vendAccountMapper.selectByPrimaryKey(usercode);
			CacheUtils.put("accountCache", key, vendAccount);
		}
		return vendAccount;
	}
	/**
	 * 查找全部
	 */
	public List<VendAccount> findAll() {
		// TODO Auto-generated method stub
		String key="key_VendAccount_findAll";
		List<VendAccount> vendAccounts=(List<VendAccount>)CacheUtils.get("accountCache", key);
		if(vendAccounts==null){
			vendAccounts= vendAccountMapper.findAll();
			CacheUtils.put("accountCache", key, vendAccounts);
		}
		return vendAccounts;
	}
}
