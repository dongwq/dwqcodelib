package com.dongwq.lang


class BikeGroovyBean
{
	String manufacturer, model, serialNo, status;
	final Integer frame;
	double weight;
	BigDecimal cost;

	public void setCost(BigDecimal newCost)
	{
		cost = newCost.setScale(3, BigDecimal.ROUND_HALF_UP);
	}

	public String toString()
	{
		return """Bike:
			manufacturer --$manufacturer
			model --$model
			frame --$frame
			serialNo--$serialNo
			"""
	}
}