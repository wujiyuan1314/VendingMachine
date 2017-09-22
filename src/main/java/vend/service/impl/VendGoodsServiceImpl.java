package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
import base.util.DateUtil;
import base.util.Page;
import vend.dao.VendGoodsMapper;
import vend.entity.VendGoods;
import vend.service.VendGoodsService;

@Service
public class VendGoodsServiceImpl implements VendGoodsService {
	@Autowired
	VendGoodsMapper vendGoodsMapper;
	/**
	 * 鏍规嵁杈撳叆淇℃伅鏉′欢鏌ヨ鍟嗗搧鍒楄〃锛屽苟鍒嗛〉鏄剧ず
	 * @param vendGoods
	 * @param page
	 * @return
	 */                                                                   
	public List<VendGoods> listVendGoods(VendGoods vendGoods,Page page){
		String title=vendGoods.getGoodsName();
		String currentPage=Integer.toString(page.getCurrentPage());
		if(title==null){
			title="";
		}
		String key="key_listVendGoods"+title+currentPage;
		List<VendGoods> vendGoodss=(List<VendGoods>)CacheUtils.get("goodsCache", key);
		if(vendGoodss==null){
			int totalNumber = vendGoodsMapper.countVendGoods(vendGoods);
			page.setTotalNumber(totalNumber);
			vendGoodss= vendGoodsMapper.listVendGoods(vendGoods, page);
			CacheUtils.put("goodsCache",key, vendGoodss);
		}
		return vendGoodss;
	}
	/**
	 * 娣诲姞鍟嗗搧
	 * @param vendGoods
	 * @return
	 */
	public int insertVendGoods(VendGoods vendGoods){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendGoods.setCreateTime(createTime);
		vendGoods.setUpdateTime(createTime);
		int isOk= vendGoodsMapper.insertSelective(vendGoods);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 修改商品
	 * @param vendGoods
	 * @return
	 */
	public int editVendGoods(VendGoods vendGoods){
		int isOk=vendGoodsMapper.updateByPrimaryKeySelective(vendGoods);
		//淇敼鍚庡垹闄ょ紦瀛�
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 鍒犻櫎涓�涓晢鍝�
	 * @param id
	 */
	public void delVendGoods(int id){
		int isOk=vendGoodsMapper.deleteByPrimaryKey(id);
		if(isOk==1){
			CacheUtils.clear();
		}
	}
	/**
	 * 批量删除
	 * @param id
	 */
	public int delVendGoodss(int ids[]){
		int isOk=vendGoodsMapper.deleteBatch(ids);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 得到一个商品
	 * @param id
	 * @return
	 */
	public VendGoods getOne(int id){
		String key="key_Goods_getOne"+id;
		VendGoods vendGoods=(VendGoods)CacheUtils.get("goodsCache", key);
		if(vendGoods==null){
			vendGoods=vendGoodsMapper.selectByPrimaryKey(id);
		}
		return vendGoods;
	}
	/**
	 * 查找全部
	 */
	public List<VendGoods> findAll() {
		// TODO Auto-generated method stub
		String key="key_Goods_findAll";
		List<VendGoods> vendGoodss=(List<VendGoods>)CacheUtils.get("goodsCache", key);
		if(vendGoodss==null){
			vendGoodss=vendGoodsMapper.findAll();
			CacheUtils.put("goodsCache", key, vendGoodss);
		}
		return vendGoodss;
	}
}
