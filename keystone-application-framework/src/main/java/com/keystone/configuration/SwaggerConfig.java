package com.keystone.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.keystone.KeystoneApplicationFrameworkApplication;
import com.keystone.common.AbstractSwaggerConfig;

import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@Profile("!"+ProfileConstants.SPRING_PROFILE_PRODUCTION) 
public class SwaggerConfig  extends AbstractSwaggerConfig{  
	
	/* (non-Javadoc)
	 * @see com.ccsp.common.AbstractSwaggerConfig#getBasePackageName()
	 */
	@Override
	public String getBasePackageName() {
		//return the SpringBootInitializer class name
		return KeystoneApplicationFrameworkApplication.class.getPackage().getName();
	}

	/**
	 * @return UiConfiguration
	 */
	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl", // url
				"none", // docExpansion => none | list
				"alpha", // apiSorter => alpha
				"schema", // defaultModelRendering => schema
				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, 
				false, // enableJsonEditor  => true | false
				true, // showRequestHeaders => true | false
				60000L); // requestTimeout => in milliseconds, defaults to null
							// (uses jquery xh timeout)
	}
}
