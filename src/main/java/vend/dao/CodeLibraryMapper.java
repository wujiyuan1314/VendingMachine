package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vend.entity.CodeLibrary;

public interface CodeLibraryMapper {
    int deleteByPrimaryKey(String id);

    int insert(CodeLibrary record);

    int insertSelective(CodeLibrary record);

    CodeLibrary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CodeLibrary record);

    int updateByPrimaryKey(CodeLibrary record);
    
    List<CodeLibrary> selectByCodeNo(String codeno);
    
    CodeLibrary selectByItemno(@Param("codeno") String codeno,@Param("itemno") String itemno);
}