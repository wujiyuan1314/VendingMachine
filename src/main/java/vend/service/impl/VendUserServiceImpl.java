package vend.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import base.util.DateUtil;
import base.util.Function;
import base.util.Page;
import vend.dao.UserCouponMapper;
import vend.dao.VendAccountMapper;
import vend.dao.VendPermissionMapper;
import vend.dao.VendUserMapper;
import vend.entity.UserCoupon;
import vend.entity.VendAccount;
import vend.entity.VendPermission;
import vend.entity.VendUser;
import vend.service.VendUserService;

@Service
public class VendUserServiceImpl implements VendUserService {
	@Autowired
	VendUserMapper vendUserMapper;
	@Autowired
	VendPermissionMapper vendPermissionMapper;
	@Autowired
	VendAccountMapper vendAccountMapper;
	@Autowired
	UserCouponMapper userCouponMapper;
	/**
	 * 根据输入信息条件查询用户列表，并分页显示
	 * @param vendUser
	 * @param page
	 * @return
	 */
	public List<VendUser> listVendUser(VendUser vendUser,Page page){
		int totalNumber = vendUserMapper.countVendUser(vendUser);
		page.setTotalNumber(totalNumber);
		return vendUserMapper.listVendUser(vendUser, page);
	}
	/**
	 * 添加用户
	 * @param vendUser
	 * @return
	 */
	public int insertVendUser(VendUser vendUser){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		String usercode=Function.getUsercode();
		vendUser.setUsercode(usercode);
		vendUser.setCreateTime(createTime);
		vendUser.setUpdateTime(createTime);
		
		//添加该用户账户信息
		VendAccount vendAccount=new VendAccount();
		vendAccount.setUsercode(usercode);
		vendAccount.setOwnAmount(BigDecimal.valueOf(0.00));
		String moneyencrypt=Function.getEncrypt(BigDecimal.valueOf(0.00).toString());
		vendAccount.setMoneyencrypt(moneyencrypt);
		vendAccount.setCreateTime(createTime);
		vendAccount.setUpdateTime(createTime);
		vendAccountMapper.insert(vendAccount);
		
		//注册的新用户会获得一个优惠券
		UserCoupon userCoupon=new UserCoupon();
		userCoupon.setUsercode(usercode);
		userCoupon.setCouponId(1);
		userCoupon.setCreateTime(createTime);
		userCouponMapper.insert(userCoupon);
		
		return vendUserMapper.insertSelective(vendUser);
	}
	/**
	 * 修改用户
	 * @param vendUser
	 * @return
	 */
	public int editVendUser(VendUser vendUser){
		return vendUserMapper.updateByPrimaryKeySelective(vendUser);
	}
	/**
	 * 删除一个用户
	 * @param id
	 */
	public void delVendUser(String usercode){
		vendUserMapper.deleteByPrimaryKey(usercode);
	}
	/**
	 * 批量删除用户
	 * @param id
	 */
	public int delVendUsers(String usercodes[]){
		return vendUserMapper.deleteBatch(usercodes);
	}
	/**
	 * 根据ID查找一个用户
	 * @param id
	 * @return
	 */
	public VendUser getOne(String usercode){
		return vendUserMapper.selectByPrimaryKey(usercode);
	}
	public List<VendUser> findAll() {
		// TODO Auto-generated method stub
		return vendUserMapper.findAll();
	}
	/**
	 * 按照username查找用户
	 * @param username
	 * @return
	 */
	public VendUser selectByUsername(String username){
		return vendUserMapper.selectByUsername(username);
	}
	 /**
     * 按照用户名得到角色信息
     * @param userName
     * @return
     */
    @Override
	public Set<String> getRoles(String username){
    	return vendUserMapper.getRoles(username);
    }
	/**
	 * 按到用户名得到权限信息
	 * @param userName
	 * @return
	 */
 	@CachePut(value="userCache")
	@Override
	public Set<String> getPermissions(String username){
		Set<String> set1=vendUserMapper.getPermissions(username);
		Set<String> set2=new HashSet<String>();
		for(String permissionid:set1){
			VendPermission vendPermission=vendPermissionMapper.selectByPrimaryKey(Integer.parseInt(permissionid));
			set2.add(vendPermission.getPermissionName());
		}
		return set2;
	}
}
