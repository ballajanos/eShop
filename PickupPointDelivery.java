package eShopSimulator;

public class PickupPointDelivery implements DeliveryStrategy {

	@Override
	public void deliver() {
		System.out.println("Delivering to a pickup point.");
	}

}
