package com.voting.web.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voting.db.param.PageResult;
import com.voting.db.param.ressource.ResourceListParam;
import com.voting.manager.resource.ResourceManager;
import com.voting.model.resource.Resource;
import com.voting.web.result.WebResult;

/**
 * 请写出类的用途 
 * @author peng.wang
 * @date  2017-11-14 22:30:28
 * @version 1.0.0
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceManager resourceManager;

    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
	@ResponseBody
	public WebResult queryList(ResourceListParam param){
		WebResult result = new WebResult();
		PageResult<Resource> list  = resourceManager.queryListByParam(param);
		result.addData("rows", list.getData());
		result.addData("total", list.getCount());
		return result;
	}
}