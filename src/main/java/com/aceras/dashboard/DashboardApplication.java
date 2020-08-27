package com.aceras.dashboard;

import com.aceras.dashboard.model.Product;
import com.aceras.dashboard.model.Role;
import com.aceras.dashboard.model.RoleName;
import com.aceras.dashboard.repository.ProductRepository;
import com.aceras.dashboard.repository.RoleRepository;
import com.aceras.dashboard.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		DashboardApplication.class,
		Jsr310JpaConverters.class
})
public class DashboardApplication {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RoleRepository roleRepository;

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}

    @Bean
    CommandLineRunner runner() {
        return args -> {
            roleRepository.save(new Role(RoleName.ROLE_USER));
            roleRepository.save(new Role(RoleName.ROLE_ADMIN));

//            Save demo data after start (only run first time)
            productRepository.deleteAll();
            productRepository.save(new Product("IP1","Iphone 1","Iphone generation 1st",AppConstants.PRODUCT_STATUS_AVAILABLE, AppConstants.PRODUCT_TYPE_OLD_FASHION));
            productRepository.save(new Product("IP2","Iphone 2","Iphone generation 2nd",AppConstants.PRODUCT_STATUS_STOPPED, AppConstants.PRODUCT_TYPE_OUT_OF_STOCK));
            productRepository.save(new Product("IP3","Iphone 3","Iphone generation 3rd",AppConstants.PRODUCT_STATUS_AVAILABLE, AppConstants.PRODUCT_TYPE_OLD_FASHION));
            productRepository.save(new Product("IP4","Iphone 4","Iphone generation 4th",AppConstants.PRODUCT_STATUS_AVAILABLE, AppConstants.PRODUCT_TYPE_OLD_FASHION));
            productRepository.save(new Product("IP5","Iphone 5","Iphone generation 5th",AppConstants.PRODUCT_STATUS_STOPPED, AppConstants.PRODUCT_TYPE_OUT_OF_STOCK));
            productRepository.save(new Product("IP6","Iphone 6","Iphone generation 6th",AppConstants.PRODUCT_STATUS_AVAILABLE, AppConstants.PRODUCT_TYPE_NEW_ARRIVAL));
            productRepository.save(new Product("IP7","Iphone 7","Iphone generation 7th",AppConstants.PRODUCT_STATUS_AVAILABLE, AppConstants.PRODUCT_TYPE_NEW_ARRIVAL));
            productRepository.save(new Product("SS_G14351","Samsung S1","Samsung generation 1th",AppConstants.PRODUCT_STATUS_AVAILABLE, AppConstants.PRODUCT_TYPE_OLD_FASHION));
            productRepository.save(new Product("SS_G14352","Samsung S2","Samsung generation 2th",AppConstants.PRODUCT_STATUS_STOPPED, AppConstants.PRODUCT_TYPE_OUT_OF_STOCK));
            productRepository.save(new Product("SS_G14353","Samsung S3","Samsung generation 3th",AppConstants.PRODUCT_STATUS_AVAILABLE, AppConstants.PRODUCT_TYPE_OUT_OF_STOCK));
            productRepository.save(new Product("SS_G14354","Samsung S4","Samsung generation 4th",AppConstants.PRODUCT_STATUS_STOPPED, AppConstants.PRODUCT_TYPE_OLD_FASHION));
            productRepository.save(new Product("SS_G14355","Samsung S5","Samsung generation 5th",AppConstants.PRODUCT_STATUS_STOPPED, AppConstants.PRODUCT_TYPE_OLD_FASHION));
            productRepository.save(new Product("SS_G14356","Samsung S6","Samsung generation 6th",AppConstants.PRODUCT_STATUS_AVAILABLE, AppConstants.PRODUCT_TYPE_NEW_ARRIVAL));
            productRepository.save(new Product("SS_G14357","Samsung S7","Samsung generation 7th",AppConstants.PRODUCT_STATUS_AVAILABLE, AppConstants.PRODUCT_TYPE_NEW_ARRIVAL));
            productRepository.save(new Product("SS_G14358","Samsung S8","Samsung generation 8th",AppConstants.PRODUCT_STATUS_AVAILABLE, AppConstants.PRODUCT_TYPE_NEW_ARRIVAL));
        };
    }
}
