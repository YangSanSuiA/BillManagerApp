package cn.yangsansui.service.impl;

import cn.yangsansui.mapper.BillMapper;
import cn.yangsansui.pojo.Bill;
import cn.yangsansui.service.BillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Resource
    private BillMapper billMapper;

    @Override
    public List<Bill> getAllBill(String billName, Integer pid, Integer pay) {
        if (billName.length() <= 0) {
            billName = null;
        }
        return billMapper.getAllBill(billName, pid, pay);
    }

    @Override
    public Bill getBillById(Integer bid) {
        return billMapper.getBillById(bid);
    }

    @Override
    public Integer updateBillById(Bill bill) {
        return billMapper.updateBillById(bill);
    }

    @Override
    public Integer delBillById(Integer bid) {
        return billMapper.delBillById(bid);
    }

    @Override
    public Integer addBill(Bill bill) {
        return billMapper.addBill(bill);
    }
}
