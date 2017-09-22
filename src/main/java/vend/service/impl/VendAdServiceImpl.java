package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
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
	public List<VendAd> listVendAd(VendAd vendAd,Page page){
		String title=vendAd.getAdName();
		String currentPage=Integer.toString(page.getCurrentPage());
		if(title==null){
			title="";
		}
		String key="key_listVendAd"+title+currentPage;
		List<VendAd> vendAds=(List<VendAd>)CacheUtils.get("adCache", key);
		if(vendAds==null){
			int totalNumber = vendAdMapper.countVendAd(vendAd);
			page.setTotalNumber(totalNumber);
			vendAds=vendAdMapper.listVendAd(vendAd, page);
			CacheUtils.put("adCache",key, vendAds);
		}
		return vendAds;
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
		int isOk=vendAdMapper.insertSelective(vendAd);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 修改广告
	 * @param vendAd
	 * @return
	 */
	public int editVendAd(VendAd vendAd){
		int isOk=vendAdMapper.updateByPrimaryKeySelective(vendAd);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 删除一个广告
	 * @param id
	 */
	public void delVendAd(int id){
		int isOk=vendAdMapper.deleteByPrimaryKey(id);
		if(isOk==1){
			CacheUtils.clear();
		}
	}
	/**
	 * 批量删除广告
	 * @param id
	 */
	public int delVendAds(int ids[]){
		int isOk=vendAdMapper.deleteBatch(ids);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 根据ID查找一个广告
	 * @param id
	 * @return
	 */
	public VendAd getOne(int id){
		String key="VendAd_getOne"+id;
		VendAd vendAd=(VendAd)CacheUtils.get("adCache", key);
		if(vendAd==null){
			vendAd=vendAdMapper.selectByPrimaryKey(id);
			CacheUtils.put("adCache", key, vendAd);
		}
		return vendAd;
	}
	/**
	 * 查找全部
	 */
	public List<VendAd> findAll() {
		// TODO Auto-generated method stub
		String key="key_VendAd_findAll";
		List<VendAd> vendAds=(List<VendAd>)CacheUtils.get("adCache", key);
		if(vendAds==null){
			vendAds= vendAdMapper.findAll();
			CacheUtils.put("adCache", key, vendAds);
		}
		return vendAds;
	}
}
