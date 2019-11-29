package cn.yangsansui.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


/**
 * 自定义区域检测器
 */
public class MyLocalResolver implements LocaleResolver {


    /**
     * 解析访问语言 国家+语言
     * @param httpServletRequest
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取需要自定义请求头
        String l=httpServletRequest.getParameter("l");

        //获取系统 默认区域信息
        Locale locale=Locale.getDefault();

        if(!StringUtils.isEmpty(l)){

            String[] split=l.split("_");
            //接收第一个参数：语言代码，国家代码
            locale =new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }

}
