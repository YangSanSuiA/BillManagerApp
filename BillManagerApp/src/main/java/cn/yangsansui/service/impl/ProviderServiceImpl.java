package cn.yangsansui.service.impl;

import cn.yangsansui.mapper.ProviderMapper;
import cn.yangsansui.pojo.Provider;
import cn.yangsansui.service.ProviderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Resource
    private ProviderMapper providerMapper;

    @Override
    public List<Provider> providerList() {
        return providerMapper.providerList();
    }

    @Override
    public List<Provider> providerListByName(String providerName) {
        return providerMapper.providerListByName(providerName);
    }

    @Override
    public Provider providerListById(Integer pid) {
        return providerMapper.providerListById(pid);
    }

    @Override
    public Integer updateProvider(Provider provider) {
        return providerMapper.updateProvider(provider);
    }

    @Override
    public Integer addProvider(Provider provider) {
        return providerMapper.addProvider(provider);
    }

    @Override
    public Integer delProvider(Integer pid) {
        return providerMapper.delProvider(pid);
    }
}
