package cn.yangsansui.pojo;


public class Bill {

  private long bid;
  private String billCode;
  private String billName;
  private String billCom;
  private long billNum;
  private double money;
  private long pay;
  private long pid;
  private java.sql.Timestamp createDate;

  private Provider provider;


  public long getBid() {
    return bid;
  }

  public void setBid(long bid) {
    this.bid = bid;
  }


  public String getBillCode() {
    return billCode;
  }

  public void setBillCode(String billCode) {
    this.billCode = billCode;
  }


  public String getBillName() {
    return billName;
  }

  public void setBillName(String billName) {
    this.billName = billName;
  }


  public String getBillCom() {
    return billCom;
  }

  public void setBillCom(String billCom) {
    this.billCom = billCom;
  }


  public long getBillNum() {
    return billNum;
  }

  public void setBillNum(long billNum) {
    this.billNum = billNum;
  }


  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }


  public long getPay() {
    return pay;
  }

  public void setPay(long pay) {
    this.pay = pay;
  }


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }


  public java.sql.Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(java.sql.Timestamp createDate) {
    this.createDate = createDate;
  }

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }
}
