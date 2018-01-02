package com.voting.manager.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.db.param.PageResult;
import com.voting.db.param.ressource.ResourceListParam;
import com.voting.manager.resource.ResourceManager;
import com.voting.service.resource.ResourceService;

/**
 * 请写出类的用途 
 * @author peng.wang
 * @date  2017-11-14 22:30:28
 * @version 1.0.0
 * 
 */
@Service("resourceManager")
public class ResourceManagerImpl implements ResourceManager{
    @Autowired
    private ResourceService resourceService;

	@Override
	public PageResult<com.voting.model.resource.Resource> queryListByParam(
			ResourceListParam param) {
		return resourceService.queryListByParam(param);
	}

}