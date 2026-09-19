package miniguiproject1;

public class Seden extends vehicle {

	public Seden(String vid, String brand, String model, Integer year, String ftype, String transmission, Double price,
			Boolean available, String vtype) {
		super(vid, brand, model, year, ftype, transmission, price, available, vtype);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculateRent(int days) {
		return getPrice() * days;
	}
	
	

}
