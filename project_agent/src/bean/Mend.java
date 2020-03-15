package bean;

import java.util.Date;

public class Mend {
	private int mendid;
	private String vid;
	private String problem;
	private int mstate;
	private Date mtime;

	public int getMendid() {
		return mendid;
	}
	public void setMendid(int mendid) {
		this.mendid = mendid;
	}
	public int getMstate() {
		return mstate;
	}
	public void setMstate(int mstate) {
		this.mstate = mstate;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
}

