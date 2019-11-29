package cn.yangsansui.controller;

import cn.yangsansui.pojo.Bill;
import cn.yangsansui.service.BillService;
import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.RE;
import javafx.scene.chart.ValueAxis;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
public class BillController {

    @Resource
    private BillService billService;

    /*查询所有账单信息*/
    @GetMapping(value = "/bill")
    public String getAllBill(@RequestParam(value = "billName",required = false,defaultValue = "")String billName,
                             @RequestParam(value = "pid",required = false,defaultValue = "0")Integer pid,
                             @RequestParam(value = "pay",required = false,defaultValue = "2")Integer pay,
                             Map<String,Object> map){
        List<Bill> billList=billService.getAllBill(billName,pid,pay);
        map.put("billList",billList);   //封装查询结果
        map.put("billName",billName);   //回显查询条件
        return "bill/list";
    }

    /*根据指定的编号查询账单信息*/
    @GetMapping(value = "/bill/{bid}")
    public String getBillById(@PathVariable(value = "bid",required = false)Integer bid,
                              Map<String,Object> map,@RequestParam(value = "type",required = false,defaultValue = "view")String type){
        Bill bill=billService.getBillById(bid);
        map.put("bill",bill);
        return "bill/"+type;
    }

    /*修改账单信息*/
    @PostMapping(value = "/updateBill")
    public String updateBill(Bill bill){
        Integer result=billService.updateBillById(bill);
        if(result>0){
            return "redirect:/bill";
        }else{
            return "redirect:/bill/"+bill.getBid();
        }
    }

    /*删除账单信息*/
    @GetMapping(value = "/delBill/{bid}")
    public String delBill(@PathVariable("bid")Integer bid, HttpServletResponse response){
        Integer result=billService.delBillById(bid);
        if(result>0){
            response.setContentType("text/html;charset=utf-8");
            try {
                PrintWriter writer = response.getWriter();
                String msg = null;
                    msg = "alert( '删除成功' );location.href='/bill'";
                writer.print("<script type='text/javascript'>" + msg + "</script>");
                writer.flush();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/bill";
    }

    /*新增账单信息*/
    @PostMapping(value = "/bill")
    public String addBill(Bill bill,Map<String,Object> map){
        Integer result=billService.addBill(bill);
        if(result>0){
            return "redirect:/bill";
        }else{
            map.put("error","添加账单记录错误！");
            return "bill/add";
        }
    }

    /*跳转到新增界面*/
    @GetMapping(value = "/goAddBill")
    public String goAddBill(){
        return "bill/add";
    }

}
