package jagroop.example.microservices.limitsservice.bean;

public class LimitConfiguration {

	int maximum;
	int minmum;
	
	public LimitConfiguration(int maximum, int minmum) {
		super();
		this.maximum = maximum;
		this.minmum = minmum;
	}

	public int getMaximum() {
		return maximum;
	}

	public int getMinmum() {
		return minmum;
	}
	
	
}
