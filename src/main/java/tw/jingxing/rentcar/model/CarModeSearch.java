package tw.jingxing.rentcar.model;

public class CarModeSearch{
	
	private int aid;
	private String aname;

	public CarModeSearch() {
	}

	public CarModeSearch(int aid, String aname) {
		this.aid = aid;
		this.aname = aname;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

}
