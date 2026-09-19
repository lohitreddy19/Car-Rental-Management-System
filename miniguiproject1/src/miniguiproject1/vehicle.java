package miniguiproject1;

public abstract class vehicle {
	
	private String vid;
	private String brand;
	private String model;
	private Integer year;
	private String ftype;
	private String transmission;
	private Double price;
	private Boolean available;
	private String vtype;
	
	public vehicle(String vid, String brand, String model, Integer year, String ftype, String transmission, Double price,
			Boolean available, String vtype) {
		this.vid = vid;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.ftype = ftype;
		this.transmission = transmission;
		this.price = price;
		this.available = available;
		this.vtype = vtype;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	public String getvtype() {
		return vtype;
	}
	
	public void setvtype(String vtype)
	{
		this.vtype = vtype;
	}
	
	public abstract double calculateRent(int days);
	
	public void displayVehicleDetails()
	{
		System.out.println("Vehicle id: " + vid);
		System.out.println("Brand: " + brand);
		System.out.println("Model: " + model);
		System.out.println("Year: " + year);
		System.out.println("Fuel Type: " + ftype);
		System.out.println("Transmission: " + transmission);
		System.out.println("Available: " + available);
		System.out.println("Vehicle type: " + vtype);
	}
	
}
