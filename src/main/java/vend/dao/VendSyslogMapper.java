package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendSyslog;

public interface VendSyslogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendSyslog record);

    int insertSelective(VendSyslog record);

    VendSyslog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendSyslog record);

    int updateByPrimaryKey(VendSyslog record);
    // 下面为自定义方法
 	List<VendSyslog> listVendSyslog(@Param("vendSyslog") VendSyslog vendSyslog, @Param("page") Page page);
 	
 	int countVendSyslog(VendSyslog vendSyslog);
}