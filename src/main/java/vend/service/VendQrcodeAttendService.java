package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendQrcodeAttend;

public interface VendQrcodeAttendService {
	/**
	 * 根据输入信息条件查询二维码关注信息列表，并分页显示
	 * @param vendQrcodeAttend
	 * @param page
	 * @return
	 */
	List<VendQrcodeAttend> listVendQrcodeAttend(VendQrcodeAttend vendQrcodeAttend,Page page);
	/**
	 * 添加二维码关注信息
	 * @param vendQrcodeAttend
	 * @return
	 */
	int insertVendQrcodeAttend(VendQrcodeAttend vendQrcodeAttend);
	/**
	 * 修改二维码关注信息
	 * @param vendQrcodeAttend
	 * @return
	 */
	int editVendQrcodeAttend(VendQrcodeAttend vendQrcodeAttend);
	/**
	 * 删除某个角色
	 * @param id
	 */
	void delVendQrcodeAttend(int id);
	/**
	 * 批量删除二维码关注信息
	 * @param id
	 */
	int delVendQrcodeAttends(int ids[]);
	/**
	 * 根据ID查找某个角色
	 * @param id
	 * @return
	 */
	VendQrcodeAttend getOne(int id);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendQrcodeAttend> findAll();
	/**
	 * 按条件查找全部
	 * @param vendQrcodeAttend
	 * @return
	 */
	List<VendQrcodeAttend> selectByParams(VendQrcodeAttend vendQrcodeAttend);
}
