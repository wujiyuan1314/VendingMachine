package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendAuthorize;

public interface VendAuthorizeService {
	/**
	 * 根据输入信息条件查询授权列表，并分页显示
	 * @param vendAuthorize
	 * @param page
	 * @return
	 */
	List<VendAuthorize> listVendAuthorize(VendAuthorize vendAuthorize,Page page);
	/**
	 * 添加授权
	 * @param vendAuthorize
	 * @return
	 */
	int insertVendAuthorize(VendAuthorize vendAuthorize);
	/**
	 * 修改授权
	 * @param vendAuthorize
	 * @return
	 */
	int editVendAuthorize(VendAuthorize vendAuthorize);
	/**
	 * 删除一个授权
	 * @param id
	 */
	void delVendAuthorize(int id);
	/**
	 * 根据ID查找一个授权
	 * @param id
	 * @return
	 */
	VendAuthorize getOne(int id);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendAuthorize> findAll();
	/**
	 * 按所属用户名
	 * @param roleName
	 * @return
	 */
	List<VendAuthorize> selectByUsercode(String usercode);
	
}
