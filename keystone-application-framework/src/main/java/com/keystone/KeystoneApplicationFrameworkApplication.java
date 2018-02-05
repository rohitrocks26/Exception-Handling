package com.keystone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.DispatcherServlet;

import com.keystone.com.rules.webservice.serviceImpl.RulesFactoryImpl;
import com.keystone.common.aspect.LoggingAspect;
import com.keystone.common.report.DownloadReport;
import com.keystone.common.report.UploadReport;
import com.keystone.common.rules.webservice.service.IRulesFactory;

@EnableAspectJAutoProxy
@SpringBootApplication
//@EnableGlobalMethodSecurity(prePostEnabled = false)
@ComponentScan
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class })
public class KeystoneApplicationFrameworkApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(KeystoneApplicationFrameworkApplication.class, args);
	}
	
	@Bean
	public LoggingAspect getAspect() {
		return new LoggingAspect();
	}

	@Bean
	public DownloadReport getDownloadReport() {
		return new DownloadReport();
	}

	@Bean
	public UploadReport getUploadReport() {
		return new UploadReport();
	}

	@Bean
	public IRulesFactory getRulesFactoryImpl() {
		return new RulesFactoryImpl();
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(KeystoneApplicationFrameworkApplication.class);
	}
	
	@Bean  
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
}
