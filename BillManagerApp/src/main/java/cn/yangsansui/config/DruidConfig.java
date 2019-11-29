package cn.yangsansui.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid自动配置类
 */
@Configuration
public class DruidConfig {

    /*注入属性到DataSource*/
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    @Bean
    /*配置Druid的监测后台*/
    public ServletRegistrationBean servletRegistrationBean(){
        /*请求开放所有druid开始的页面*/
        ServletRegistrationBean<StatViewServlet> statViewServletServletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String,String> initParm=new HashMap<>();
        statViewServletServletRegistrationBean.setInitParameters(initParm);
        initParm.put(StatViewServlet.PARAM_NAME_USERNAME,"root");
        initParm.put(StatViewServlet.PARAM_NAME_PASSWORD,"123");
        /*如果不写可以所有IP进行访问后台*/
        initParm.put(StatViewServlet.PARAM_NAME_ALLOW,"10.0.0.4");
        initParm.put(StatViewServlet.PARAM_NAME_DENY,"190.168.0.0");
        return statViewServletServletRegistrationBean;
    }

    /*配置Druid的过滤器*/
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean=new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new WebStatFilter());

        Map<String, String> initPrams=new HashMap<>();

        initPrams.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.css,/druid/*");
        filterRegistrationBean.setInitParameters(initPrams);

        //设置拦截请求
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

}
