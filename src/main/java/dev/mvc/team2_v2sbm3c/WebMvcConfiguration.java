package dev.mvc.team2_v2sbm3c;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.mvc.fboard.Fboard;
import dev.mvc.tool.Tool;

public class WebMvcConfiguration implements WebMvcConfigurer{
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Windows: path = "C:/kd/deploy/team2_v2sbm3c/fboard/storage";
        // ▶ file:///C:/kd/deploy/team2_v2sbm3c/fboard/storage
      
        // Ubuntu: path = "/home/ubuntu/deploy/team2_v2sbm3c/fboard/storage";
        // ▶ file:////home/ubuntu/deploy/team2_v2sbm3c/fboard/storage
      
        // JSP 인식되는 경로: http://localhost:9093/fboard/storage";
        registry.addResourceHandler("/fboard/storage/**").addResourceLocations("file:///" +  Fboard.getUploadDir());
        
     // JSP 인식되는 경로: http://localhost:9093/attachfile/storage";
        // registry.addResourceHandler("/fboard/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/attachfile/storage/");
        
        // JSP 인식되는 경로: http://localhost:9093/member/storage";
        // registry.addResourceHandler("/fboard/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/member/storage/");

    }

}
