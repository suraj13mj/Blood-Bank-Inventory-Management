
public class BloodBank {
	
	private int no_of_units;
	private String bname,baddress,bcity;
	private String bphone;
	public BloodBank(String bname,String bcity, String baddress, String bphone, int no_of_units) {
		this.setBname(bname);
		this.setBcity(bcity);
		this.setBaddress(baddress);
		this.setBphone(bphone);
		this.setNo_of_units(no_of_units);
		
	}
	
	public BloodBank() {
		// TODO Auto-generated constructor stub
	}

	public String getBphone() {
		return bphone;
	}
	public void setBphone(String bphone) {
		this.bphone = bphone;
	}
	public String getBaddress() {
		return baddress;
	}
	public void setBaddress(String baddress) {
		this.baddress = baddress;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}

	public int getNo_of_units() {
		return no_of_units;
	}

	public void setNo_of_units(int no_of_units) {
		this.no_of_units = no_of_units;
	}

	public String getBcity() {
		return bcity;
	}

	public void setBcity(String bcity) {
		this.bcity = bcity;
	}
	
}
