package com.voting.service.resource;

import com.voting.db.param.PageResult;
import com.voting.db.param.ressource.ResourceListParam;
import com.voting.model.resource.Resource;

public interface ResourceService {
	PageResult<Resource> queryListByParam(ResourceListParam param);
}