package vend.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendUser;

public interface VendUserMapper {
    int deleteByPrimaryKey(String usercode);

    int insert(VendUser record);

    int insertSelective(VendUser record);

    VendUser selectByPrimaryKey(String usercode);

    int updateByPrimaryKeySelective(VendUser record);

    int updateByPrimaryKey(VendUser record);
    /**下为自定义方法 */
    List<VendUser> listVendUser(@Param("vendUser") VendUser vendUser,@Param("usersArray") String usersArray[],@Param("page") Page page);
    
    int countVendUser(@Param("vendUser") VendUser vendUser,@Param("usersArray") String usersArray[]);
    
    void insertBatch(List<VendUser> list);
    
    int deleteBatch(String usercodes[]);
    
    List<VendUser> findAll();
    
    List<VendUser> selectByArealist(String arealist[]);
    
    VendUser selectByUsername(String username);
    
    List<VendUser> selectByParentUsercode(String parentUsercode);
    
    public Set<String> getRoles(String userName);
	
   	public Set<String> getPermissions(String userName);
}