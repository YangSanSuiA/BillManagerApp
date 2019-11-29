package cn.yangsansui.controller;

import cn.yangsansui.pojo.Provider;
import cn.yangsansui.service.ProviderService;
import com.alibaba.fastjson.JSON;
import javafx.scene.chart.ValueAxis;
import org.omg.CORBA.MARSHAL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ProviderController {

    @Resource
    private ProviderService providerService;

    /*查询所有的供应商信息*/
    @ResponseBody
    @GetMapping(value = "/providers")
    public String providers(Map<String,Object> map){
        List<Provider> providerList=providerService.providerList();
        map.put("providerList",providerList);
        return JSON.toJSONString(map);
    }

    /*查询所有供应商信息*/
    @GetMapping(value = "/allProviders")
    public String allProviders(Map<String,Object> map,
                               @RequestParam(value = "providerName",required = false,defaultValue = "") String providerName){
        List<Provider> providerList=providerService.providerListByName(providerName);
        map.put("providers",providerList);
        map.put("providerName",providerName);
        return "provider/list";
    }

    /*根据编号查询供应商信息*/
    @GetMapping(value = "/providers/{pid}")
    public  String providersById(@PathVariable(value = "pid",required = false)Integer pid,
                                 Map<String,Object> map,@RequestParam(value = "type",required=false,defaultValue = "view")String type  ){
        Provider provider=providerService.providerListById(pid);
        map.put("provider",provider);
        return "provider/"+type;
    }

    /*修改供应商信息*/
    @PostMapping(value = "/updateProvider")
    public String updateProvider(Provider provider,Map<String ,Object> map){
        Integer result=providerService.updateProvider(provider);
        if(result>0){
            return "redirect:/allProviders";
        }else{
            map.put("error","修改供应商信息错误！");
            return "redirect:/providers/"+provider.getPid();
        }
    }

    /*打开新增供应商界面*/
    @GetMapping(value = "/addProvider")
    public String goProvider(){
        return "provider/add";
    }

    /*打开新增供应商界面*/
    @PostMapping(value = "/addProvider")
    public String addProvider(Provider provider,Map<String ,Object> map){
        Integer result=providerService.addProvider(provider);
        if(result>0){
            return "redirect:/allProviders";
        }else{
            map.put("error","新增供应商信息错误！");
            return "provider/add";
        }
    }

    /*删除账单信息*/
    @GetMapping(value = "/delProvider/{pid}")
    public String delBill(@PathVariable("pid")Integer pid, HttpServletResponse response){
        Integer result=providerService.delProvider(pid);
        if(result>0){
            response.setContentType("text/html;charset=utf-8");
            try {
                PrintWriter writer = response.getWriter();
                String msg = null;
                msg = "alert( '删除成功' );location.href='/allProviders'";
                writer.print("<script type='text/javascript'>" + msg + "</script>");
                writer.flush();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/allProviders";
    }



}
