package com.example.backendinterviewtask.service.impl;

import com.example.backendinterviewtask.client.BrandsApiClient;
import com.example.backendinterviewtask.dto.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by GreenNun on 2019-04-22.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BrandNamesCacheImplTest.Config.class, BrandNamesCacheImpl.class})
public class BrandNamesCacheImplTest {
    @Autowired
    private BrandNamesCacheImpl brandNamesCache;
    @Autowired
    private BrandsApiClient brandsApiClient;

    @Test
    public void getNameByBrandId() {
        final String brandName1 = brandNamesCache.getNameByBrandId(1);
        verify(brandsApiClient, times(1)).getBrands();          // init cache
        verify(brandsApiClient, times(0)).getBrand(1);  // from cache
        assertEquals("name", brandName1, "A");

        final String brandName2 = brandNamesCache.getNameByBrandId(2);
        verify(brandsApiClient, times(1)).getBrands();          // from cache
        verify(brandsApiClient, times(0)).getBrand(2);  // from cache
        assertEquals("name", brandName2, "B");

        final String brandName3 = brandNamesCache.getNameByBrandId(3);
        verify(brandsApiClient, times(1)).getBrands();          // from cache
        verify(brandsApiClient, times(1)).getBrand(3);  // remote and put in cache
        assertEquals("name", brandName3, "C");

        final String brandName4 = brandNamesCache.getNameByBrandId(3);
        verify(brandsApiClient, times(1)).getBrands();          // from cache
        verify(brandsApiClient, times(1)).getBrand(3);  // from cache
        assertEquals("name", brandName4, "C");
    }

    @TestConfiguration
    public static class Config {

        @Bean
        public BrandsApiClient brandsApiClient() {
            final BrandsApiClient mock = mock(BrandsApiClient.class);

            when(mock.getBrands())
                    .thenReturn(Arrays.asList(getBrand(1, "A"), getBrand(2, "B")));

            when(mock.getBrand(3))
                    .thenReturn(getBrand(3, "C"));

            return mock;
        }

        private Brand getBrand(int id, String name) {
            final Brand brand = new Brand();
            brand.setId(id);
            brand.setName(name);
            return brand;
        }
    }
}