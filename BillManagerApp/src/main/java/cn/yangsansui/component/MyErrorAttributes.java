package cn.yangsansui.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

//向容器中添加此组件
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    /**
     * 自定义数据进行响应
     * @param webRequest
     * @param includeStackTrace
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String,Object> map=super.getErrorAttributes(webRequest,includeStackTrace);
        map.put("company","自定义异常发生！");
        return map;
    }
}
