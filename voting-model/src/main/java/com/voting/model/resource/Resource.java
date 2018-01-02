package com.voting.model.resource;

import java.io.Serializable;
import java.util.Date;

/**
 * 请写出类的用途 
 * @author peng.wang
 * @date  2017-11-14 22:30:28
 * @version 1.0.0
 */
public class Resource implements Serializable {
    /**
     * 资源ID
     */
    private Long id;

    /**
     * 父资源ID
     */
    private Long parentId;

    /**
     * 类型：1、菜单；2、按钮
     */
    private Byte type;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 排序
     */
    private Byte sort;

    /**
     * 创建人ID
     */
    private Long createId;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人ID
     */
    private Long updateId;

    /**
     * 修改人名称
     */
    private String updateName;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 日志编号，对应java代码 ObjectTypeEnum
     */
    private String logIds;

    private static final long serialVersionUID = 1L;

    /**
     * 
     * {@linkplain #id}
     *
     * @return the value of resource.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * {@linkplain #id}
     * @param id the value for resource.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * {@linkplain #parentId}
     *
     * @return the value of resource.parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 
     * {@linkplain #parentId}
     * @param parentId the value for resource.parent_id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 
     * {@linkplain #type}
     *
     * @return the value of resource.type
     */
    public Byte getType() {
        return type;
    }

    /**
     * 
     * {@linkplain #type}
     * @param type the value for resource.type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 
     * {@linkplain #name}
     *
     * @return the value of resource.name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * {@linkplain #name}
     * @param name the value for resource.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * {@linkplain #url}
     *
     * @return the value of resource.url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * {@linkplain #url}
     * @param url the value for resource.url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * {@linkplain #icon}
     *
     * @return the value of resource.icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * {@linkplain #icon}
     * @param icon the value for resource.icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 
     * {@linkplain #status}
     *
     * @return the value of resource.status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 
     * {@linkplain #status}
     * @param status the value for resource.status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 
     * {@linkplain #sort}
     *
     * @return the value of resource.sort
     */
    public Byte getSort() {
        return sort;
    }

    /**
     * 
     * {@linkplain #sort}
     * @param sort the value for resource.sort
     */
    public void setSort(Byte sort) {
        this.sort = sort;
    }

    /**
     * 
     * {@linkplain #createId}
     *
     * @return the value of resource.create_id
     */
    public Long getCreateId() {
        return createId;
    }

    /**
     * 
     * {@linkplain #createId}
     * @param createId the value for resource.create_id
     */
    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    /**
     * 
     * {@linkplain #createName}
     *
     * @return the value of resource.create_name
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 
     * {@linkplain #createName}
     * @param createName the value for resource.create_name
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 
     * {@linkplain #createTime}
     *
     * @return the value of resource.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * {@linkplain #createTime}
     * @param createTime the value for resource.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * {@linkplain #updateId}
     *
     * @return the value of resource.update_id
     */
    public Long getUpdateId() {
        return updateId;
    }

    /**
     * 
     * {@linkplain #updateId}
     * @param updateId the value for resource.update_id
     */
    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    /**
     * 
     * {@linkplain #updateName}
     *
     * @return the value of resource.update_name
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * 
     * {@linkplain #updateName}
     * @param updateName the value for resource.update_name
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    /**
     * 
     * {@linkplain #updateTime}
     *
     * @return the value of resource.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * {@linkplain #updateTime}
     * @param updateTime the value for resource.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 
     * {@linkplain #logIds}
     *
     * @return the value of resource.log_ids
     */
    public String getLogIds() {
        return logIds;
    }

    /**
     * 
     * {@linkplain #logIds}
     * @param logIds the value for resource.log_ids
     */
    public void setLogIds(String logIds) {
        this.logIds = logIds;
    }
}