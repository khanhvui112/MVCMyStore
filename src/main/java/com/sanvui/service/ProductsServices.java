package com.sanvui.service;

import com.sanvui.convert.DecimalFormats;
import com.sanvui.convert.ProductConvert;
import com.sanvui.model.dto.resp.ImagesResponseDto;
import com.sanvui.model.dto.resp.ProductResponseDto;
import com.sanvui.model.entity.Images;
import com.sanvui.model.entity.Products;
import com.sanvui.repository.ProductRepository;
import com.sanvui.service.imp.ImagesServiceImpl;
import com.sanvui.utils.SortUtil;
import com.sanvui.utils.ValidatorBean;
import com.sanvui.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 06/11/2021-3:12 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
public class ProductsServices {


    /*
    * value page size products
    * */
    @Value("${app.page.products.size}")
    private Integer pageProductsSize;

    /*
    * value page size10
    * */
    @Value("${app.page.products.size10}")
    private Integer pageProductsSize10;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ImagesServiceImpl imagesServiceImpl;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private FileLocalStorageService fileLocalStorageService;

    /*
    * Find product have name like
    * param name query
    * return List ProductResponseDto
    * */
    public List<ProductResponseDto> findByNameLike(String name) {

        List<ProductResponseDto> productRespDtos = repository.findByProductNameLike(name)
                .stream()
                .map(p -> ProductConvert.getInstance().productToResponseDto(p, fileLocalStorageService))
                .collect(Collectors.toList());

        return buildProductList(productRespDtos);
    }

    public List<Products> findAll() {
        return repository.findAll();
    }

//    TODO: Find top product sort by productId desc page size:10
    public Page<ProductResponseDto> findTopProductsPage(Integer page) throws IOException {

        List<String> sort = new ArrayList<>();
        sort.add("product_name");
        sort.add("-title");

        Pageable pageable = PageRequest.of(page
                , pageProductsSize10
                , SortUtil.generateSort(sort,"-product_id"));

        return repository.findPage(pageable, null);

    }

    /*
    * find All product by repository custom
    * param integer page , Map where clause
    * return Page ProductResponseDto
    * */
    public Page<ProductResponseDto> findAllPage(Integer page, Map<String, String> whereClause, String sort) throws IOException {

        List<String> sorts = new ArrayList<>();

        if(StringUtils.isNotBlank(sort)){
            sorts.add(sort);
        }

        Pageable pageable = PageRequest
                .of(page, pageProductsSize
                        , SortUtil.generateSort(sorts,"-product_id"));

        return buildProduct(repository.findPage(pageable, whereClause));

    }

    /*
    * Select top 20 product
    * order by create desc
    * return Page<ProductResponseDto>
    */
    public Page<ProductResponseDto> findTop20CreateDateDesc() throws IOException {

        return buildProduct(repository.findTop20CreateDateDesc());

    }

    public List<ProductResponseDto> findAllCustom() throws IOException {
        return buildProductList(repository.findAllCustom());
    }

    /*
    * find product by id
    * param id
    * return product
    * */
    public ProductResponseDto findByProductId(Integer id){

        ProductConvert productConvert = new ProductConvert();

        Optional<Products> product = repository.findById(id);

        ProductResponseDto productResponseDtos;
        if (product.isPresent()){

            Products productGet = product.get();
            productGet.setProductDetails(productDetailService.findProductDetailByProductId(productGet.getProductId()));

            productResponseDtos = productConvert.productToResponseDto(productGet,fileLocalStorageService);
           productResponseDtos.setProductDetailRepositoryDtos(ProductConvert.getInstance().buildProductDetails(productGet));
            productResponseDtos.setPriceSales(buildPriceSale(productResponseDtos));
            return  productResponseDtos;
        }

        return null;
    }

    /*
    * insert new Product to database
    * param Object product
    * if success return Object product
    * else return null
    * */
    public Products save(Products product) {
        ValidatorBean validatorBean = ValidatorUtils.validatorBean(product);
        if (!validatorBean.isSuccess()) {
            return null;
        }
        return repository.save(product);
    }

    private Double buildPriceSale(ProductResponseDto dto) {
        if(StringUtils.isNotBlank(dto.getSale_code())){
            int saleCode = dto.getSale_code().indexOf("%");
            double saleCodeDouble = 0;
            if(saleCode != -1){
                saleCodeDouble = Integer
                        .parseInt((dto.getSale_code()).replaceAll("%",""));

                saleCodeDouble = (100-saleCodeDouble)/100;

                return dto.getPrice() * saleCodeDouble;
            }
            saleCodeDouble = Integer
                    .parseInt((dto.getSale_code()).replaceAll("K",""));

            return dto.getPrice() - (saleCodeDouble*1000);
        }
        return dto.getPrice();
    }

    /*
    * build product responsive dto
    * */
    public Page<ProductResponseDto> buildProduct(Page<ProductResponseDto> dto){

        for (ProductResponseDto p :dto){

            List<ImagesResponseDto> imagesResponseDtos = imagesServiceImpl.findByProductId(p.getProductId())
                    .stream()
                    .map(i->new ImagesResponseDto(i.getImageId(),i.getProduct_id(),fileLocalStorageService.buildUrl(i.getImageName()),i.getImageType()))
                    .collect(Collectors.toList());

            p.setImageLink(imagesResponseDtos);

            p.setPriceSales(buildPriceSale(p));
        }
        return dto;
    }

    /*
     * build product responsive dto
     * return list
     * */
    public List<ProductResponseDto> buildProductList(List<ProductResponseDto> dto){

        for (ProductResponseDto p : dto){

            List<ImagesResponseDto> imagesResponseDtos = imagesServiceImpl.findByProductId(p.getProductId())
                    .stream()
                    .map(i->new ImagesResponseDto(i.getImageId(),i.getProduct_id(),fileLocalStorageService.buildUrl(i.getImageName()),i.getImageType()))
                    .collect(Collectors.toList());

            p.setImageLink(imagesResponseDtos);

            p.setPriceSales(buildPriceSale(p));
        }
        return dto;
    }
}
