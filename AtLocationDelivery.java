package eShopSimulator;

public class AtLocationDelivery implements DeliveryStrategy {

	@Override
	public void deliver() {
		 System.out.println("Delivering to a specified location.");
	}

}
