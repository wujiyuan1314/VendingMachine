package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import base.util.CacheUtils;
import base.util.DateUtil;
import base.util.Page;
import vend.dao.VendAccountDetailMapper;

import vend.entity.VendAccountDetail;
import vend.entity.VendGoods;
import vend.service.VendAccountDetailService;

@Service
public class VendAccountDetailServiceImpl implements VendAccountDetailService {
	@Autowired
	VendAccountDetailMapper vendAccountDetailMapper;
	/**
	 * 根据输入信息条件查询账户操作纪录列表，并分页显示
	 * @param vendAccountDetail
	 * @param page
	 * @return
	 */
	public List<VendAccountDetail> listVendAccountDetail(VendAccountDetail vendAccountDetail,Page page){
		int totalNumber = vendAccountDetailMapper.countVendAccountDetail(vendAccountDetail);
		page.setTotalNumber(totalNumber);
		String title=vendAccountDetail.getUsercode();
		if(title==null){
			title="";
		}
		String currentPage=Integer.toString(page.getCurrentPage());
		String key="key_listVendAccountDetail"+title+currentPage;
		List<VendAccountDetail> vendAccountDetails=(List<VendAccountDetail>)CacheUtils.get("accountCache", key);
		if(vendAccountDetails==null){
			vendAccountDetails =vendAccountDetailMapper.listVendAccountDetail(vendAccountDetail, page);
			CacheUtils.put("accountCache",key, vendAccountDetails);
		}
		return vendAccountDetails;
	}
	/**
	 * 根据输入信息条件查询账户提现纪录列表，并分页显示
	 * @param vendAccountDetail
	 * @param page
	 * @return
	 */
	public List<VendAccountDetail> listVendAccountDetailTx(VendAccountDetail vendAccountDetail,Page page){
		String title=vendAccountDetail.getUsercode();
		if(title==null){
			title="";
		}
		String currentPage=Integer.toString(page.getCurrentPage());
		String key="key_listVendAccountDetailTx"+title+currentPage;
		List<VendAccountDetail> vendAccountDetails=(List<VendAccountDetail>)CacheUtils.get("accountCache", key);
		if(vendAccountDetails==null){
			int totalNumber = vendAccountDetailMapper.countVendAccountDetailTx(vendAccountDetail);
			page.setTotalNumber(totalNumber);
			vendAccountDetails =vendAccountDetailMapper.listVendAccountDetailTx(vendAccountDetail, page);
			CacheUtils.put("accountCache",key, vendAccountDetails);
		}
		return vendAccountDetails;
	}
	/**
	 * 添加账户操作纪录
	 * @param vendAccountDetail
	 * @return
	 */
	public int insertVendAccountDetail(VendAccountDetail vendAccountDetail){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendAccountDetail.setCreateTime(createTime);
		return vendAccountDetailMapper.insertSelective(vendAccountDetail);
	}
	/**
	 * 修改账户操作纪录
	 * @param vendAccountDetail
	 * @return
	 */
	public int editVendAccountDetail(VendAccountDetail vendAccountDetail){
		return vendAccountDetailMapper.updateByPrimaryKeySelective(vendAccountDetail);
	}
	/**
	 * 删除一个账户操作纪录
	 * @param id
	 */
	public void delVendAccountDetail(int id){
		vendAccountDetailMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 批量删除账户操作纪录
	 * @param id
	 */
	public int delVendAccountDetails(int ids[]){
		return vendAccountDetailMapper.deleteBatch(ids);
	}
	/**
	 * 根据ID查找一个账户操作纪录
	 * @param id
	 * @return
	 */
	@Cacheable(value="accountCache")
	public VendAccountDetail getOne(int id){
		String key="key_VendAccountDetail_getOne"+id;
		VendAccountDetail vendAccountDetail=(VendAccountDetail)CacheUtils.get("accountCache", key);
		if(vendAccountDetail==null){
			vendAccountDetail=vendAccountDetailMapper.selectByPrimaryKey(id);
			CacheUtils.put("accountCache", key, vendAccountDetail);
		}
		return vendAccountDetail;
	}
	/**
	 * 每次查询10条
	 */
	public List<VendAccountDetail> selectLimit(String usercode,int number){
		return vendAccountDetailMapper.selectLimit(usercode, number);
	}
	/**
	 * 查找全部
	 * @return
	 */
	public List<VendAccountDetail> findAll() {
		// TODO Auto-generated method stub
		String key="key_VendAccountDetail_findAll";
		List<VendAccountDetail> vendAccountDetails=(List<VendAccountDetail>)CacheUtils.get("accountCache", key);
		if(vendAccountDetails==null){
			vendAccountDetails=vendAccountDetailMapper.findAll();
			CacheUtils.put("accountCache", key, vendAccountDetails);
		}
		return vendAccountDetails;
	}
}
