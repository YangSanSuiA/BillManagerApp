package cn.yangsansui.mapper;

import cn.yangsansui.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface UserMapper {

    /**
     * 用户登录
     * @param userCode
     * @param userPassword
     * @return
     */
    public User userLogin(@Param("userCode") String userCode,@Param("userPassword")String userPassword);

    /**
     * 查询用户信息
     * @param userName 用户名称
     * @return
     */
    public List<User> allUser(String userName);

    /**
     * 添加新的用户信息
     * @param user
     * @return
     */
    public Integer addUser(User user);

    /**
     * 根据ID查询具体的用户
     * @param id
     * @return
     */
    public User userById(Integer id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public Integer updateUser(User user);

    /**
     * 删除用户ID
     * @param id
     * @return
     */
    public Integer delUser(Integer id);

    /**
     * 修改用户密码
     * @param id
     * @param newPassWord
     * @return
     */
    public Integer updatePassWord(Integer id, String newPassWord);
}
