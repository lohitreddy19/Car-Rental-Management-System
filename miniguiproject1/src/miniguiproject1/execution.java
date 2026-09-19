package miniguiproject1;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class execution {

	public static void main(String[] args) throws invalidRentalException, SQLException {
		// TODO Auto-generated method stub
		
		try
		{
			RentalManager rentalmanager = new RentalManager();
			
			Rental rental = rentalmanager.createRental("r120", "c101", "v102", 4);
			
			if(rental == null)
			{
				System.out.println("Rental creation failed");
				return;
			}
			
			System.out.println("\n===== RENTAL DETAILS =====");
            System.out.println("Rental ID: " + rental.getRid());
            System.out.println("Customer: " + rental.getCustomer().getCname());
            System.out.println("Vehicle: " + rental.getVehicle().getModel());
            System.out.println("Rental Days: " + rental.getDays());
            System.out.println("Start Date: " + rental.getstartDate());
            System.out.println("Expected Return Date: " + rental.getExpectedReturnDate());
            
            System.out.println("=========return vehicle=======");
            
            boolean returned = rentalmanager.returnVehicle("r120");
            
            if(returned)
            {
            	System.out.println("vehicle returned successfully");
            }
            
            System.out.println("========payment details===========");
            
            System.out.println("Total Rent: " + rental.calculateTotalRent());
            System.out.println("Late Days: " + rental.calculateLateDays());
            System.out.println("Late Charge: " + rental.calculateLateCharge());
            System.out.println("Final Amount: " + rental.calculateFinalAmount());
		
            Payment pay = new Payment("p120", rental, rental.calculateFinalAmount(), "upi", "pending" );
            
            pay.makePayment();
            
            rentalmanager.addPayment(pay);
            
            Receipt receipt = new Receipt("rc120", pay, rental.getCustomer(), rental.getVehicle(), pay.getAmount());
            
            rentalmanager.addReceipt(receipt);
            
            System.out.println("======receipt=========");
            
            receipt.printreceipt();
		}
		catch(invalidRentalException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
