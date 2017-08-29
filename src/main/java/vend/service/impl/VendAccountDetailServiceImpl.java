package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.DateUtil;
import base.util.Page;
import vend.dao.VendAccountDetailMapper;
import vend.entity.VendAccountDetail;
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
		return vendAccountDetailMapper.listVendAccountDetail(vendAccountDetail, page);
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
	public VendAccountDetail getOne(int id){
		return vendAccountDetailMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查找全部
	 * @return
	 */
	public List<VendAccountDetail> findAll() {
		// TODO Auto-generated method stub
		return vendAccountDetailMapper.findAll();
	}
}
