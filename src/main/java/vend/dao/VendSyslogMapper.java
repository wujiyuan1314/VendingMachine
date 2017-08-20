package vend.dao;

import vend.entity.VendSyslog;
import vend.entity.VendSyslogWithBLOBs;

public interface VendSyslogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendSyslogWithBLOBs record);

    int insertSelective(VendSyslogWithBLOBs record);

    VendSyslogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendSyslogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(VendSyslogWithBLOBs record);

    int updateByPrimaryKey(VendSyslog record);
}