package miniguiproject1;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	
	public void writeData(Rental r) throws IOException
	{
		FileWriter fw = new FileWriter("rental_data.txt", true);
		
		fw.write("Car Rental Management System\n");
		fw.write("Rental ID: " + r.getRid() + "\n");
		fw.write("Customer: " + r.getCustomer().getCname() + "\n");
		fw.write("Vehicle: " + r.getVehicle().getModel() + "\n");
		fw.write("Rental Days: " + r.getDays() + "\n");
		fw.write("Total Rent: " + r.calculateTotalRent() + "\n");
		fw.write("-------------------------------------------------\n");
		fw.close();
		
		System.out.println(System.getProperty("user.dir"));
	}

}
