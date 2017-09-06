package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendShopQrcode;

public interface VendShopQrcodeService {
	/**
	 * 根据输入信息条件查询二维码信息列表，并分页显示
	 * @param vendShopQrcode
	 * @param page
	 * @return
	 */
	List<VendShopQrcode> listVendShopQrcode(VendShopQrcode vendShopQrcode,Page page);
	/**
	 * 添加二维码信息
	 * @param vendShopQrcode
	 * @return
	 */
	int insertVendShopQrcode(VendShopQrcode vendShopQrcode);
	/**
	 * 修改二维码信息
	 * @param vendShopQrcode
	 * @return
	 */
	int editVendShopQrcode(VendShopQrcode vendShopQrcode);
	/**
	 * 删除某个角色
	 * @param id
	 */
	void delVendShopQrcode(int id);
	/**
	 * 批量删除二维码信息
	 * @param id
	 */
	int delVendShopQrcodes(int ids[]);
	/**
	 * 根据ID查找某个角色
	 * @param id
	 * @return
	 */
	VendShopQrcode getOne(int id);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendShopQrcode> findAll();
	/**
	 * 按照类型查找
	 * @param extend2
	 * @return
	 */
	List<VendShopQrcode> selectByType(String extend2);
}
