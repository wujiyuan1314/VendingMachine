package vend.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendAccountDetail;

public interface VendAccountDetailService {
	/**
	 * 根据输入信息条件查询账户操作纪录列表，并分页显示
	 * @param vendAccountDetail
	 * @param page
	 * @return
	 */
	List<VendAccountDetail> listVendAccountDetail(VendAccountDetail vendAccountDetail,Page page);
	/**
	 * 根据输入信息条件查询账户提现纪录列表，并分页显示
	 * @param vendAccountDetail
	 * @param page
	 * @return
	 */
	List<VendAccountDetail> listVendAccountDetailTx(VendAccountDetail vendAccountDetail,Page page);
	/**
	 * 添加账户操作纪录
	 * @param vendAccountDetail
	 * @return
	 */
	int insertVendAccountDetail(VendAccountDetail vendAccountDetail);
	/**
	 * 修改账户操作纪录
	 * @param vendAccountDetail
	 * @return
	 */
	int editVendAccountDetail(VendAccountDetail vendAccountDetail);
	/**
	 * 删除一个账户操作纪录
	 * @param id
	 */
	void delVendAccountDetail(int id);
	/**
	 * 批量删除账户操作纪录
	 * @param id
	 */
	int delVendAccountDetails(int id[]);
	/**
	 * 根据ID查找一个账户操作纪录
	 * @param id
	 * @return
	 */
	VendAccountDetail getOne(int id);
	/**
	 * 每次查询10条
	 * @param usercode
	 * @param number
	 * @return
	 */
	List<VendAccountDetail> selectLimit(String usercode,int number);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendAccountDetail> findAll();
}
