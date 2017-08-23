package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendAd;

public interface VendAdService {
	/**
	 * 根据输入信息条件查询广告列表，并分页显示
	 * @param vendAd
	 * @param page
	 * @return
	 */
	List<VendAd> listVendAd(VendAd vendAd,Page page);
	/**
	 * 添加广告
	 * @param vendAd
	 * @return
	 */
	int insertVendAd(VendAd vendAd);
	/**
	 * 修改广告
	 * @param vendAd
	 * @return
	 */
	int editVendAd(VendAd vendAd);
	/**
	 * 删除一个广告
	 * @param id
	 */
	void delVendAd(int id);
	/**
	 * 批量删除广告
	 * @param id
	 */
	int delVendAds(int id[]);
	/**
	 * 根据ID查找一个广告
	 * @param id
	 * @return
	 */
	VendAd getOne(int id);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendAd> findAll();
	
}
