package miniguiproject1;

public class Payment {
	
	private String pid;
	private Rental rental;
	private Double amount;
	private String pmethod;
	private String status;
	
	public Payment(String pid, Rental rental, Double amount, String pmethod, String status) {
		this.pid = pid;
		this.rental = rental;
		this.amount = amount;
		this.pmethod = pmethod;
		this.status = status;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPmethod() {
		return pmethod;
	}

	public void setPmethod(String pmethod) {
		this.pmethod = pmethod;
	}
	
	public String getstatus() {
		return status;
	}
	
	public void setstatus(String status) {
		this.status = status;
	}
	
	public Double gettAmount()
	{
		return rental.calculateFinalAmount();
	}
	
	public void makePayment()
	{
		status = "Successful";
		System.out.println("Payment id: " + pid);
		System.out.println("Amount: " + amount);
		System.out.println("Payment Method: " + pmethod);
		System.out.println("Payment status: " + status);
	}

}
