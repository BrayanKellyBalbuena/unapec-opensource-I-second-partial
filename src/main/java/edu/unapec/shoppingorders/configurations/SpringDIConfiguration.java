package edu.unapec.shoppingorders.configurations;

import edu.unapec.shoppingorders.dtos.*;
import edu.unapec.shoppingorders.models.*;
import edu.unapec.shoppingorders.repositories.*;
import edu.unapec.shoppingorders.repositories.impl.database.*;
import edu.unapec.shoppingorders.services.*;
import edu.unapec.shoppingorders.services.impl.*;
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
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public HibernateUtil hibernateUtil() {
        return new HibernateUtil();
    }

    @Bean()
    public ProductCategoryRepository productCategoryRepository() {
        return new ProductCategoryRepositoryDatabaseImpl(hibernateUtil());
    }

    @Bean
    public ProductCategoryService productCategoryService() {
        return new ProductCategoryServiceImpl(productCategoryRepository());
    }

    @Bean()
    public LocationRepository locationRepository() {
        return new LocationRepositoryDatabaseImpl(hibernateUtil());
    }

    @Bean
    public LocationService locationService() {
        return new LocationServiceImpl(locationRepository());
    }

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryDatabaseImpl(hibernateUtil());
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl(productRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryDatabaseImpl(hibernateUtil());
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    public ShoppingOrderRepository shoppingOrderRepository() {
        return new ShoppingOrderDatabaseRepositoryImpl(hibernateUtil());
    }

    @Bean
    public ShoppingOrderService shoppingOrderService() {
        return new ShoppingOrderServiceImpl(shoppingOrderRepository());
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        mapper.getConfiguration().setAmbiguityIgnored(true);

        mapper.createTypeMap(ProductCategory.class, ProductCategoryDto.class);
        mapper.createTypeMap(ProductCategoryDto.class, ProductCategoryDto.class);
        mapper.createTypeMap(Location.class, LocationDto.class);
        mapper.createTypeMap(LocationDto.class, Location.class);
        mapper.createTypeMap(ProductDto.class, Product.class);
        mapper.createTypeMap(UserLoginDto.class, User.class);
        mapper.createTypeMap(OrderDto.class, ShoppingOrder.class);

        mapper.typeMap(Product.class, ProductDto.class)
                .addMapping(src -> src.getProductCategory().getName(), ProductDto::setCategory);

        mapper.typeMap(ShoppingOrder.class, OrderDto.class)
                .addMapping(src -> src.getProduct().getName(), OrderDto::setProduct)
                .addMapping(src -> src.getLocation(), OrderDto::setLocation);


        mapper.createTypeMap(User.class, UserDto.class);
        mapper.createTypeMap(UserDto.class, User.class);

        return mapper;
    }
}
