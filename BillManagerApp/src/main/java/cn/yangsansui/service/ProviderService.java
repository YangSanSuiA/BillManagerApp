package cn.yangsansui.service;

import cn.yangsansui.pojo.Provider;
import cn.yangsansui.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderService {

    /**
     * 查询所有供应商信息
     *
     * @return
     */
    public List<Provider> providerList();

    /**
     * 查询所有供应商信息 根据名称
     *
     * @return
     */
    public List<Provider> providerListByName(String providerName);

    /**
     * 查询所有供应商信息 根据编号
     *
     * @return
     */
    public Provider providerListById(Integer pid);

    /**
     * 修改供应商信息
     *
     * @param provider
     * @return
     */
    public Integer updateProvider(Provider provider);

    /**
     * 新增供应商信息
     *
     * @param provider
     * @return
     */
    public Integer addProvider(Provider provider);

    /**
     * 删除供应商信息
     *
     * @return
     */
    public Integer delProvider(Integer pid);

}
