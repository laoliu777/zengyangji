package bean;

import java.util.Date;

public class Item {
	private String type;
	private String ino;
	private int yajing;
	private String member;
	private String agent;
	private int sswitch;
	private int rent;
	private Date starttime;
	private Date endtime;
	private int buystate;
	
	public int getBuystate() {
		return buystate;
	}
	public void setBuystate(int buystate) {
		this.buystate = buystate;
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
	public int getYajing() {
		return yajing;
	}
	public void setYajing(int yajing) {
		this.yajing = yajing;
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
	public int getSswitch() {
		return sswitch;
	}
	public void setSswitch(int sswitch) {
		this.sswitch = sswitch;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
}

