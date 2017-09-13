package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

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
	 * 根据输入信息条件查询商品列表，并分页显示
	 * @param vendGoods
	 * @param page
	 * @return
	 */
	@Cacheable(value="goodsCache",key="vendgoodss")                                                                      
	public List<VendGoods> listVendGoods(VendGoods vendGoods,Page page){
		int totalNumber = vendGoodsMapper.countVendGoods(vendGoods);
		page.setTotalNumber(totalNumber);
		return vendGoodsMapper.listVendGoods(vendGoods, page);
	}
	/**
	 * 添加商品
	 * @param vendGoods
	 * @return
	 */
	public int insertVendGoods(VendGoods vendGoods){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendGoods.setCreateTime(createTime);
		vendGoods.setUpdateTime(createTime);
		return vendGoodsMapper.insertSelective(vendGoods);
	}
	/**
	 * 修改商品
	 * @param vendGoods
	 * @return
	 */
	public int editVendGoods(VendGoods vendGoods){
		return vendGoodsMapper.updateByPrimaryKeySelective(vendGoods);
	}
	/**
	 * 删除一个商品
	 * @param id
	 */
	public void delVendGoods(int id){
		vendGoodsMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 批量删除商品
	 * @param id
	 */
	public int delVendGoodss(int ids[]){
		return vendGoodsMapper.deleteBatch(ids);
	}
	/**
	 * 根据ID查找一个商品
	 * @param id
	 * @return
	 */
	public VendGoods getOne(int id){
		return vendGoodsMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查找全部
	 */
	public List<VendGoods> findAll() {
		// TODO Auto-generated method stub
		return vendGoodsMapper.findAll();
	}
}
