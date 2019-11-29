package cn.yangsansui.service;

import cn.yangsansui.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillService {

    /**
     * 查询所有账单信息
     *
     * @return
     */
    public List<Bill> getAllBill(@Param("billName") String billName, @Param("pid") Integer pid, @Param("pay") Integer pay);

    /**
     * 根据ID查询具体的账单信息
     *
     * @param bid
     * @return
     */
    public Bill getBillById(@Param("bid") Integer bid);

    /**
     * 修改账单信息
     *
     * @param bill
     * @return
     */
    public Integer updateBillById(Bill bill);

    /**
     * 删除账单信息
     *
     * @param bid
     * @return
     */
    public Integer delBillById(Integer bid);

    /**
     * 添加账单信息
     *
     * @param bill
     * @return
     */
    public Integer addBill(Bill bill);

}
