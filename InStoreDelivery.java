package eShopSimulator;

public class InStoreDelivery implements DeliveryStrategy {

	@Override
	public void deliver() {
		 System.out.println("Customer picks up the order in-store.");
	}

}
