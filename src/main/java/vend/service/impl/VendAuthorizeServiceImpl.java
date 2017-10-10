package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.Page;
import vend.dao.VendAuthorizeMapper;
import vend.entity.VendAuthorize;
import vend.service.VendAuthorizeService;
@Service
public class VendAuthorizeServiceImpl implements VendAuthorizeService{
	@Autowired
	VendAuthorizeMapper vendAuthorizeMapper;
	/**
	 * 根据输入信息条件查询授权列表，并分页显示
	 * @param vendAuthorize
	 * @param page
	 * @return
	 */
	public List<VendAuthorize> listVendAuthorize(VendAuthorize vendAuthorize,Page page){
		int totalNumber = vendAuthorizeMapper.countVendAuthorize(vendAuthorize);
		List<VendAuthorize> vendAuthorizes=vendAuthorizeMapper.listVendAuthorize(vendAuthorize, page);
		return vendAuthorizes;
	}
	/**
	 * 添加授权
	 * @param vendAuthorize
	 * @return
	 */
	public int insertVendAuthorize(VendAuthorize vendAuthorize){
		return vendAuthorizeMapper.insertSelective(vendAuthorize);
	}
	/**
	 * 修改授权
	 * @param vendAuthorize
	 * @return
	 */
	public int editVendAuthorize(VendAuthorize vendAuthorize){
		return vendAuthorizeMapper.updateByPrimaryKeySelective(vendAuthorize);
	}
	/**
	 * 删除一个授权
	 * @param id
	 */
	public void delVendAuthorize(int id){
		vendAuthorizeMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 根据ID查找一个授权
	 * @param id
	 * @return
	 */
	public VendAuthorize getOne(int id) {
		return vendAuthorizeMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查找全部
	 * @return
	 */
	public List<VendAuthorize> findAll(){
		return vendAuthorizeMapper.findAll();
		
	}
	/**
	 * 按所属用户名
	 * @param roleName
	 * @return
	 */
	public List<VendAuthorize> selectByUsercode(String usercode){
		return vendAuthorizeMapper.selectByUsercode(usercode);
	}
}
