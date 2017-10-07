package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendQrcodeAttend;

public interface VendQrcodeAttendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendQrcodeAttend record);

    int insertSelective(VendQrcodeAttend record);

    VendQrcodeAttend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendQrcodeAttend record);

    int updateByPrimaryKey(VendQrcodeAttend record);
    /**涓嬮潰鏄嚜瀹氫箟鏂规硶*/
    List<VendQrcodeAttend> listVendQrcodeAttend(@Param("vendQrcodeAttend") VendQrcodeAttend vendQrcodeAttend, @Param("page") Page page);
    
    int countVendQrcodeAttend(VendQrcodeAttend vendQrcodeAttend);
    
    int deleteBatch(int ids[]);
    
    List<VendQrcodeAttend> selectByParams(@Param("vendQrcodeAttend") VendQrcodeAttend vendQrcodeAttend);
    
    List<VendQrcodeAttend> findAll();
}