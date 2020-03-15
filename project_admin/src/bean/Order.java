package bean;

import java.util.Date;

public class Order {
	private int oid;
	private String type;
	private String ino;
	private String member;
	private String agent;
	private Date ordertime;
	private int orderstate;
	private int renttime;
	private String kdcompany;
	private String kdno;
	private int yajing;
	private float zujing;
	private int zongjia;
	private int buystate;
	
	public int getBuystate() {
		return buystate;
	}
	public void setBuystate(int buystate) {
		this.buystate = buystate;
	}

	public int getZongjia() {
		return zongjia;
	}
	public void setZongjia(int zongjia) {
		this.zongjia = zongjia;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIno() {
		return ino;
	}
	public void setIno(String ino) {
		this.ino = ino;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public int getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(int orderstate) {
		this.orderstate = orderstate;
	}
	public int getRenttime() {
		return renttime;
	}
	public void setRenttime(int renttime) {
		this.renttime = renttime;
	}
	public String getKdcompany() {
		return kdcompany;
	}
	public void setKdcompany(String kdcompany) {
		this.kdcompany = kdcompany;
	}
	public String getKdno() {
		return kdno;
	}
	public void setKdno(String kdno) {
		this.kdno = kdno;
	}
	public int getYajing() {
		return yajing;
	}
	public void setYajing(int yajing) {
		this.yajing = yajing;
	}
	public float getZujing() {
		return zujing;
	}
	public void setZujing(float zujing) {
		this.zujing = zujing;
	}
	
	
}
