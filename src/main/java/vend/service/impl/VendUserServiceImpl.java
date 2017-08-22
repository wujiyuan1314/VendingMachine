package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.DateUtil;
import base.util.Function;
import base.util.Page;
import vend.dao.VendUserMapper;
import vend.entity.VendUser;
import vend.service.VendUserService;

@Service
public class VendUserServiceImpl implements VendUserService {
	@Autowired
	VendUserMapper vendUserMapper;
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
}
