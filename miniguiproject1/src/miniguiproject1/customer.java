package miniguiproject1;

public class customer {
	
	private String cid;
	private String cname;
	private String mobile;
	private String email;
	
	public customer(String cid, String cname, String mobile, String email) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.mobile = mobile;
		this.email = email;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
