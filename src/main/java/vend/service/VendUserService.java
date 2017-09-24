package vend.service;

import java.util.List;
import java.util.Set;

import base.util.Page;
import vend.entity.VendUser;

public interface VendUserService {
	/**
	 * 根据输入信息条件查询用户列表，并分页显示
	 * @param vendUser
	 * @param page
	 * @return
	 */
	List<VendUser> listVendUser(VendUser vendUser,String usersArray[],Page page);
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
	/**
	 * 按照微信公众号号码查找用户 
	 * @param wechatpubNo
	 * @return
	 */
	VendUser selectByWechatpubNo(String wechatpubNo);
	/**
	 * 按照username查找用户 
	 * @param username
	 * @return
	 */
	VendUser selectByUsername(String username);
	/**
	 * 按照地区查找用户
	 * @param arealist
	 * @return
	 */
	List<VendUser> selectByArealist(String arealist[]);
	/**
     * 按照用户名得到角色信息
     * @param userName
     * @return
     */
	public Set<String> getRoles(String username);
	/**
	 * 按到用户名得到权限信息
	 * @param userName
	 * @return
	 */
	public Set<String> getPermissions(String username);
	/**
	 * 得到该用户的下级用户
	 * @param parentUsercode
	 * @return
	 */
	public String getNextUsers(String parentUsercode);
	/**
	 * 得到该用户的下级用户(包括自己)
	 * @param parentUsercode
	 * @return
	 */
	public String getNextUsersOwnSelf(String parentUsercode);
}
