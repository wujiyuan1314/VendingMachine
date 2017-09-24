package vend.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<VendUser> listVendUser(VendUser vendUser,String usersArray[],Page page){
		String title=vendUser.getUsercode()+usersArray.length;
		String currentPage=Integer.toString(page.getCurrentPage());
		if(title==null){
			title="";
		}
		String key="key_listVendUser"+title+currentPage;
		List<VendUser> vendUsers=(List<VendUser>)CacheUtils.get("userCache", key);
		if(vendUsers==null){
			if(usersArray.length!=0){
				int totalNumber = vendUserMapper.countVendUser(vendUser,usersArray);
				page.setTotalNumber(totalNumber);
				vendUsers= vendUserMapper.listVendUser(vendUser,usersArray, page);
			}else{
				int totalNumber = vendUserMapper.countVendUser1(vendUser);
				page.setTotalNumber(totalNumber);
				vendUsers= vendUserMapper.listVendUser1(vendUser,page);
			}
			CacheUtils.put("userCache",key, vendUsers);
		}
		return vendUsers;
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
		
		int isOk=vendUserMapper.insertSelective(vendUser);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 修改用户
	 * @param vendUser
	 * @return
	 */
	public int editVendUser(VendUser vendUser){
		int isOk=vendUserMapper.updateByPrimaryKeySelective(vendUser);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 删除一个用户
	 * @param id
	 */
	public void delVendUser(String usercode){
		int isOk=vendUserMapper.deleteByPrimaryKey(usercode);
		if(isOk==1){
			CacheUtils.clear();
		}
	}
	/**
	 * 批量删除用户
	 * @param id
	 */
	public int delVendUsers(String usercodes[]){
		int isOk=vendUserMapper.deleteBatch(usercodes);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
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
		String key="key_VendUser_findAll";
		List<VendUser> vendUsers=(List<VendUser>)CacheUtils.get("userCache", key);
		if(vendUsers==null){
			vendUsers=vendUserMapper.findAll();
			CacheUtils.put("userCache",key, vendUsers);
		}
		return vendUsers;
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
	 * 按照微信公众号号码查找用户 
	 * @param wechatpubNo
	 * @return
	 */
	public VendUser selectByWechatpubNo(String wechatpubNo){
		String key="key_selectByWechatpubNo"+wechatpubNo;
		VendUser vendUser=(VendUser)CacheUtils.get("userCache", key);
		if(vendUser==null){
			vendUser=vendUserMapper.selectByWechatpubNo(wechatpubNo);
			CacheUtils.put("userCache", key, vendUser);
		}
		return vendUser;
	}
	/**
	 * 按照地区查找用户
	 * @param arealist
	 * @return
	 */
	public List<VendUser> selectByArealist(String arealist[]){
		String key="key_selectByArealist"+arealist.length;
		List<VendUser> vendUsers=(List<VendUser>)CacheUtils.get("userCache", key);
		if(vendUsers==null){
			if(arealist.length==0){
				vendUsers=vendUserMapper.selectByArealist1();
			}else{
				vendUsers=vendUserMapper.selectByArealist(arealist);
			}
			CacheUtils.put("userCache", key, vendUsers);
		}
		return vendUsers;
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
			set2=new HashSet<String>();
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
	public String getNextUsers(String parentUsercode){
		String userslist="";
		List<VendUser> vendUsers=vendUserMapper.selectByParentUsercode(parentUsercode);
		for(VendUser vendUser:vendUsers){
			if(vendUser!=null){
				userslist+=vendUser.getUsercode()+",";
				userslist+=getNextUsers(vendUser.getUsercode());
			}
		}
		return userslist;
	}
	/**
	 * 得到该用户的下级用户(包括自己)
	 * @param parentUsercode
	 * @return
	 */
	public String getNextUsersOwnSelf(String parentUsercode){
		String userslist=parentUsercode+",";
		List<VendUser> vendUsers=vendUserMapper.selectByParentUsercode(parentUsercode);
		for(VendUser vendUser:vendUsers){
			if(vendUser!=null){
				userslist+=vendUser.getUsercode()+",";
				userslist+=getNextUsers(vendUser.getUsercode());
			}
		}
		return userslist;
	}
}
