package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.CodeCatalog;

public interface CodeCatalogMapper {
    int deleteByPrimaryKey(String codeno);

    int insert(CodeCatalog record);

    int insertSelective(CodeCatalog record);

    CodeCatalog selectByPrimaryKey(String codeno);

    int updateByPrimaryKeySelective(CodeCatalog record);

    int updateByPrimaryKey(CodeCatalog record);
 // 下面为自定义方法
 	List<CodeCatalog> listCodeCatalog(@Param("codeCatalog") CodeCatalog bookInfo, @Param("page") Page page);
 	
 	int countCodeCatalog(CodeCatalog codeCatalog);
}