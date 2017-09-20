package vend.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
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
	@Cacheable(value="userCache")
	public List<VendUser> listVendUser(VendUser vendUser,String usersArray[],Page page){
		int totalNumber = vendUserMapper.countVendUser(vendUser,usersArray);
		page.setTotalNumber(totalNumber);
		return vendUserMapper.listVendUser(vendUser,usersArray, page);
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
	@Cacheable(value="userCache")
	public VendUser getOne(String usercode){
		return vendUserMapper.selectByPrimaryKey(usercode);
	}
	@Cacheable(value="userCache")
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
		String key="key_selectByUsername"+username;
		VendUser vendUser=(VendUser)CacheUtils.get("userCache", key);
		if(vendUser==null){
			vendUser=vendUserMapper.selectByUsername(username);
			CacheUtils.put("userCache", key, vendUser);
		}
		return vendUser;
	}
	/**
	 * 按照地区查找用户
	 * @param arealist
	 * @return
	 */
	@Cacheable(value="userCache")
	public List<VendUser> selectByArealist(String arealist[]){
		return vendUserMapper.selectByArealist(arealist);
	}
	 /**
     * 按照用户名得到角色信息
     * @param userName
     * @return
     */
    @Override
	public Set<String> getRoles(String username){
    	String key="key_getRoles"+username;
    	Set<String> set=(Set<String>)CacheUtils.get("userCache", key);
    	if(set==null){
    		set=vendUserMapper.getRoles(username);
    		CacheUtils.put("userCache", key, set);
    	}
    	return set;
    }
	/**
	 * 按到用户名得到权限信息
	 * @param userName
	 * @return
	 */
	@Override
	public Set<String> getPermissions(String username){
		String key2="key_usergetRoles"+username;
		Set<String> set2=(Set<String>)CacheUtils.get("userCache", key2);
		if(set2==null){
			VendUser pvendUser=(VendUser)CacheUtils.get("userCache", "key_selectByUsername"+username);
			if(pvendUser==null){
				pvendUser=vendUserMapper.selectByUsername(username);
				CacheUtils.put("userCache", "key_selectByUsername"+username, pvendUser);
			}
			
			String key1="key_getRoles"+pvendUser.getRoleId();
			Set<String> set1=(Set<String>)CacheUtils.get("userCache", key1);
			if(set1==null){
				set1=vendUserMapper.getPermissions(username);
				CacheUtils.put("userCache", key1, set1);
			}
			
			String permissionlist="";
			if(pvendUser!=null){
				permissionlist=pvendUser.getPermissionList();
			}
			if(permissionlist==null||"".equals(permissionlist)){
				for(String permissionid:set1){
					VendPermission vendPermission=vendPermissionMapper.selectByPrimaryKey(Integer.parseInt(permissionid));
					if(vendPermission!=null){
						set2.add(vendPermission.getPermissionName());
					}
				}
			}else{
				for(String permissionid:set1){
					if(permissionlist.indexOf(permissionid+",")!=-1){
						VendPermission vendPermission=vendPermissionMapper.selectByPrimaryKey(Integer.parseInt(permissionid));
						if(vendPermission!=null){
							set2.add(vendPermission.getPermissionName());
						}
					}
				}
			}
			CacheUtils.put("userCache", key2, set2);
		}
		return set2;
	}
	/**
	 * 得到该用户的下级用户
	 * @param parentUsercode
	 * @return
	 */
	@Cacheable(value="userCache")
	public String getNextUsers(String parentUsercode){
		List<VendUser> vendUsers=vendUserMapper.selectByParentUsercode(parentUsercode);
		String userslist="";
		for(VendUser vendUser:vendUsers){
			if(vendUser!=null){
				userslist+=vendUser.getUsercode()+",";
				userslist+=getNextUsers(vendUser.getUsercode());
			}
		}
		return userslist;
	}
}
