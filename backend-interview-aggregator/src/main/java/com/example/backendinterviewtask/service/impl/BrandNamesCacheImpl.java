package com.example.backendinterviewtask.service.impl;

import com.example.backendinterviewtask.client.BrandsApiClient;
import com.example.backendinterviewtask.service.BrandNamesCache;
import com.example.backendinterviewtask.dto.Brand;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by GreenNun on 2019-04-22.
 */
@Component
public class BrandNamesCacheImpl implements BrandNamesCache {
    private final BrandsApiClient brandsApiClient;
    private Map<Integer, String> brandNamesCache = new ConcurrentHashMap<>();

    public BrandNamesCacheImpl(BrandsApiClient brandsApiClient) {
        this.brandsApiClient = brandsApiClient;
    }

    @PostConstruct
    public void cache() {
        final Map<Integer, String> requested = brandsApiClient.getBrands().stream()
                .collect(Collectors.toMap(Brand::getId, Brand::getName));

        brandNamesCache.putAll(requested);
    }

    @Override
    public String getNameByBrandId(int id) {
        final String fromCache = brandNamesCache.get(id);

        if (fromCache == null) {
            return requestRemote(id);
        }

        return fromCache;
    }

    private String requestRemote(int id) {
        final Brand remote = brandsApiClient.getBrand(id);

        if (remote != null) {
            final String name = remote.getName();
            brandNamesCache.put(id, name);
            return name;
        }

        return "";
    }
}
