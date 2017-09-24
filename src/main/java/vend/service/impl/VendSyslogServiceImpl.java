package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
import base.util.DateUtil;
import base.util.Page;
import vend.dao.CodeCatalogMapper;
import vend.dao.VendSyslogMapper;
import vend.entity.CodeCatalog;
import vend.entity.VendSyslog;
import vend.service.VendSyslogService;
@Service
public class VendSyslogServiceImpl implements VendSyslogService {
	@Autowired
	VendSyslogMapper vendSyslogMapper;
	/**
	 * 根据输入信息条件查询日志，并分页显示
	 * @param vendSyslog
	 * @param page
	 * @return
	 */
	public List<VendSyslog> listVendSyslog(VendSyslog vendSyslog, Page page){
		// TODO Auto-generated method stub
		String title=vendSyslog.getUsercode();
		String currentPage=Integer.toString(page.getCurrentPage());
		if(title==null){
			title="";
		}
		String key="key_listVendSyslog"+title+currentPage;
		List<VendSyslog> vendSyslogs=(List<VendSyslog>)CacheUtils.get("codeCache", key);
		if(vendSyslogs==null){
			int totalNumber = vendSyslogMapper.countVendSyslog(vendSyslog);
			page.setTotalNumber(totalNumber);
			page.setPageNumber(20);
			vendSyslogs = vendSyslogMapper.listVendSyslog(vendSyslog, page);
			CacheUtils.put("codeCache",key, vendSyslogs);
		}
		return vendSyslogs;
	}
	
	/**
	 * 修改一个日志
	 * @param vendSyslog
	 */
	public int editVendSyslog(VendSyslog vendSyslog){
		return vendSyslogMapper.updateByPrimaryKeySelective(vendSyslog);
	}
	/**
	 * 添加一个日志
	 * @param vendSyslog
	 */
	public int insertVendSyslog(VendSyslog vendSyslog){
		Date operTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendSyslog.setOperTime(operTime);
		int isOk=vendSyslogMapper.insertSelective(vendSyslog);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	
	/**
	 * 删除一个日志
	 * @param id
	 */
	public int deleteVendSyslog(int id){
		int isOk=vendSyslogMapper.deleteByPrimaryKey(id);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	
	/**
	 * 批量删除日志
	 * @param ids
	 */
	public void deleteVendSyslogs(int[] ids){
		for(int i=0;i<ids.length;i++){
			vendSyslogMapper.deleteByPrimaryKey(ids[i]);
		}
		CacheUtils.clear();
	}
	
	/**
	 * 根据ID获取日志信息
	 * @param id
	 * @return
	 */
	public VendSyslog getOne(int id){
		return vendSyslogMapper.selectByPrimaryKey(id);
	}
}
