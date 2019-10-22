package edu.unapec.shoppingorders.configurations;

import edu.unapec.shoppingorders.dtos.LocationDto;
import edu.unapec.shoppingorders.dtos.ProductCategoryDto;
import edu.unapec.shoppingorders.dtos.ProductDto;
import edu.unapec.shoppingorders.models.Location;
import edu.unapec.shoppingorders.models.Product;
import edu.unapec.shoppingorders.models.ProductCategory;
import edu.unapec.shoppingorders.repositories.LocationRepository;
import edu.unapec.shoppingorders.repositories.ProductCategoryRepository;
import edu.unapec.shoppingorders.repositories.ProductRepository;
import edu.unapec.shoppingorders.repositories.impl.database.LocationRepositoryDatabaseImpl;
import edu.unapec.shoppingorders.repositories.impl.database.ProductCategoryRepositoryDatabaseImpl;
import edu.unapec.shoppingorders.repositories.impl.database.ProductRepositoryDatabaseImpl;
import edu.unapec.shoppingorders.services.LocationService;
import edu.unapec.shoppingorders.services.ProductCategoryService;
import edu.unapec.shoppingorders.services.ProductService;
import edu.unapec.shoppingorders.services.impl.LocationServiceImpl;
import edu.unapec.shoppingorders.services.impl.ProductCategoryServiceImpl;
import edu.unapec.shoppingorders.services.impl.ProductServiceImpl;
import edu.unapec.shoppingorders.utils.HibernateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class SpringDIConfiguration {

    @Bean()
    public HibernateUtil hibernateUtil() {
        return new HibernateUtil();
    }

    @Bean()
    public ProductCategoryRepository productCategoryRepository() {
        return new ProductCategoryRepositoryDatabaseImpl(hibernateUtil());
    }

    @Bean()
    public LocationRepository locationRepository() {
        return new LocationRepositoryDatabaseImpl(hibernateUtil());
    }

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryDatabaseImpl(hibernateUtil());
    }

    @Bean
    public ProductCategoryService productCategoryService() {
        return new ProductCategoryServiceImpl(productCategoryRepository());
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl(productRepository());
    }

    @Bean
    public LocationService locationService() {
        return new LocationServiceImpl(locationRepository());
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        mapper.createTypeMap(ProductCategory.class, ProductCategoryDto.class);
        mapper.createTypeMap(ProductCategoryDto.class, ProductCategoryDto.class);
        mapper.createTypeMap(Location.class, LocationDto.class);
        mapper.createTypeMap(LocationDto.class, Location.class);
        mapper.createTypeMap(ProductDto.class, Product.class);
        mapper.createTypeMap(Product.class, ProductDto.class);
        return new ModelMapper();
    }
}