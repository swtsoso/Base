package com.voting.service.resource.impl;

import com.voting.db.dao.resource.ResourceMapper;
import com.voting.db.param.PageResult;
import com.voting.db.param.ressource.ResourceListParam;
import com.voting.model.resource.Resource;
import com.voting.service.resource.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 请写出类的用途 
 * @author peng.wang
 * @date  2017-11-14 22:30:28
 * @version 1.0.0
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService{
    @Autowired
    private ResourceMapper resourceMapper;

	@Override
	public PageResult<Resource> queryListByParam(
			ResourceListParam param) {
		resourceMapper.queryListByParam(param);
		return param.getPageResult();
	}

   
}