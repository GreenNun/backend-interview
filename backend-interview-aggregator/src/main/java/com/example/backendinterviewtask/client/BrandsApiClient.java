package com.example.backendinterviewtask.client;

import com.example.backendinterviewtask.dto.Brand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by GreenNun on 2019-04-22.
 */
@FeignClient(name = "brands", url = "localhost:8081", path = "/v1")
public interface BrandsApiClient {

    @RequestMapping("/brands")
    List<Brand> getBrands();

    @RequestMapping("/brands/{brandId}")
    Brand getBrand(@PathVariable int brandId);
}
