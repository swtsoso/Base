package com.voting.manager.resource;

import com.voting.db.param.PageResult;
import com.voting.db.param.ressource.ResourceListParam;
import com.voting.model.resource.Resource;

public interface ResourceManager {
	PageResult<Resource> queryListByParam(ResourceListParam param);
}