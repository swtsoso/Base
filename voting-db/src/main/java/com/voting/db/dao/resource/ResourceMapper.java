package com.voting.db.dao.resource;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.voting.db.param.ressource.ResourceListParam;
import com.voting.model.resource.Resource;

/**
 * 请写出类的用途 
 * @author peng.wang
 * @date  2017-11-14 22:30:28
 * @version 1.0.0
 */
@Repository
public interface ResourceMapper {
    int deleteById(Long id);

    int insertSel(Resource record);

    Resource getById(Long id);
    
    List<Resource> queryListByParam(ResourceListParam param);

    int updateSel(Resource record);
}