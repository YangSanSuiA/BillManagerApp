package cn.yangsansui.controller;

import cn.yangsansui.pojo.User;
import cn.yangsansui.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    /*用户登录方法*/
    @PostMapping(value = "/userLogin")
    public String userLogin(HttpSession session, String userCode,
                           String userPassWord, Map<String,Object> map){
        logger.info("用户名："+userCode+"用户密码："+userPassWord);
        User user = userService.userLogin(userCode,userPassWord);
        if(!StringUtils.isEmpty(user)){
            session.setAttribute("CURRENT_USER",user);
            map.put("msg",null);
            return "redirect:/main.html";
        }else{
            map.put("msg","账号或者密码错误！");
            return "main/login";
        }
    }

    /*退出登录*/
    @GetMapping(value = "/logOut")
    public String logOut(HttpSession session){
        if(session.getAttribute("CURRENT_USER")!=null){
            session.removeAttribute("CURRENT_USER");
            session.invalidate();
        }
        return "redirect:/index.html";
    }

    /*查询所有用户的信息*/
    @GetMapping(value = "/users")
    public String allUser(@RequestParam(value = "userName",required = false,defaultValue = "")String userName,
                          Map<String,Object> map){
        List<User> userList=userService.allUser(userName);
        map.put("userList",userList);
        map.put("userName",userName);
        return "user/list";
    }

    /*根据编号查询用户信息*/
    @GetMapping(value = "/users/{id}")
    public String users(@PathVariable("id")Integer id,
                        Map<String,Object> map,
                        @RequestParam(value = "type",required = false,defaultValue = "view")String type){
        User user=userService.userById(id);
        map.put("user",user);
        return "user/"+type;
    }

    /*修改用户信息*/
    @PostMapping(value = "/users")
    public String updateUser(User user,Map<String ,Object> map){
        Integer result=userService.updateUser(user);
        if(result>0){
            return "redirect:/users";
        }else{
            map.put("error","修改用户信息错误！");
            return "/users/"+user.getId();
        }
    }

    /*删除用户信息*/
    @GetMapping(value = "/delUser/{id}")
    public String delUser(@PathVariable(value = "id")Integer id,
                          HttpServletResponse response){
        Integer result=userService.delUser(id);
        if(result>0){
            response.setContentType("text/html;charset=utf-8");
            try {
                PrintWriter writer = response.getWriter();
                String msg = null;
                msg = "alert( '删除成功' );location.href='/users'";
                writer.print("<script type='text/javascript'>" + msg + "</script>");
                writer.flush();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/users";
    }

    /*进入添加用户界面*/
    @GetMapping(value = "/addUser")
    public String goAddUser(){
        return "user/add";
    }

    /*添加用户信息*/
    @PostMapping(value = "/addNewUser")
    public String addUser(User user, Map<String ,Object> map){
        logger.info("进入已添加用户。。。");
        Integer result=userService.addUser(user);
        try {
            if(result>0){
                return "redirect:/users";
            }else{
                map.put("msg","添加新的用户信息错误！");
                return "user/add";
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /*进入修改密码界面*/
    @GetMapping(value = "/updatePassWord")
    public String goUpdatePassWord(){
        return "main/password";
    }

    /*修改用户密码*/
    @PostMapping(value = "/updatePassWord")
    public String updatePassWord(@RequestParam(value = "newPassWord",required = false)String newPassWord,
                                 @RequestParam(value = "id",required = false)Integer id,
                                 HttpSession session,@RequestParam(value = "oldPassWord",required = false)String oldPassWord,
                                 Map<String,Object> map){
        logger.info("修改密码。。。。");
        if(oldPassWord.equals(((User)session.getAttribute("CURRENT_USER")).getPassword())){
            Integer result=userService.updatePassWord(id,newPassWord);
            if(result>=0){
                session.removeAttribute("CURRENT_USER");
                session.invalidate();
                return "redirect:/main.html";
            }
            map.put("error","修改密码失败！");
            return "main/password";
        }
        map.put("error","旧密码和新密码匹配错误！");
        return "main/password";
    }
}
