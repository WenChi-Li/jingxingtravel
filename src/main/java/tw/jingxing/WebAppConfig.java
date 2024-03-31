package tw.jingxing;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
		registry.addResourceHandler("/royal-Doc/**").addResourceLocations("/WEB-INF/resources/royal-Doc/");
		registry.addResourceHandler("/scss/**").addResourceLocations("/WEB-INF/resources/scss/");
		registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/resources/image/");
		registry.addResourceHandler("/vendors/**").addResourceLocations("/WEB-INF/resources/vendors/");
	}

}
