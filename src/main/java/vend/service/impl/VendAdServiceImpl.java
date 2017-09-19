package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import base.util.DateUtil;
import base.util.Page;
import vend.dao.VendAdMapper;
import vend.entity.VendAd;
import vend.service.VendAdService;

@Service
public class VendAdServiceImpl implements VendAdService {
	@Autowired
	VendAdMapper vendAdMapper;
	/**
	 * 根据输入信息条件查询广告列表，并分页显示
	 * @param vendAd
	 * @param page
	 * @return
	 */
	@Cacheable(value="adCache")
	public List<VendAd> listVendAd(VendAd vendAd,Page page){
		int totalNumber = vendAdMapper.countVendAd(vendAd);
		page.setTotalNumber(totalNumber);
		return vendAdMapper.listVendAd(vendAd, page);
	}
	/**
	 * 添加广告
	 * @param vendAd
	 * @return
	 */
	public int insertVendAd(VendAd vendAd){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendAd.setCreateTime(createTime);
		vendAd.setUpdateTime(createTime);
		return vendAdMapper.insertSelective(vendAd);
	}
	/**
	 * 修改广告
	 * @param vendAd
	 * @return
	 */
	public int editVendAd(VendAd vendAd){
		return vendAdMapper.updateByPrimaryKeySelective(vendAd);
	}
	/**
	 * 删除一个广告
	 * @param id
	 */
	public void delVendAd(int id){
		vendAdMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 批量删除广告
	 * @param id
	 */
	public int delVendAds(int ids[]){
		return vendAdMapper.deleteBatch(ids);
	}
	/**
	 * 根据ID查找一个广告
	 * @param id
	 * @return
	 */
	@Cacheable(value="adCache")
	public VendAd getOne(int id){
		return vendAdMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查找全部
	 */
	@Cacheable(value="adCache")
	public List<VendAd> findAll() {
		// TODO Auto-generated method stub
		return vendAdMapper.findAll();
	}
}
