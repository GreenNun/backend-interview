package spongecell.backend.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spongecell.backend.api.exception.BrandNotFoundException;
import spongecell.backend.api.model.Brand;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("v1")
public class BrandController {
    public static final List<Brand> BRANDS;
    public static final Map<Integer, Brand> BRAND_MAP;

    static {
        List<Brand> brands = IntStream.range(65, 91).mapToObj(i -> new Brand(i, "Brand " + (char) i)).collect(Collectors.toList());
        BRANDS = Collections.unmodifiableList(brands);
        Map<Integer, Brand> bm = new HashMap<>(brands.size());
        BRANDS.forEach(b -> {
            bm.put(b.getId(), b);
        });

        BRAND_MAP = Collections.unmodifiableMap(bm);
    }

    @RequestMapping("brands")
    public List<Brand> getBrands() {
        return BRANDS;
    }

    @RequestMapping("brands/{brandId}")
    public Brand getBrand(@PathVariable int brandId) {
        validateBrandId(brandId);
        return BRAND_MAP.get(brandId);
    }

    public static void validateBrandId(int brandId) {
        if (!BRAND_MAP.containsKey(brandId)) {
            throw new BrandNotFoundException("Invalid brandId " + brandId);
        }
    }
}
