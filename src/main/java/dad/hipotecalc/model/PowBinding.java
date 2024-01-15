package dad.hipotecalc.model;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberExpression;

public class PowBinding extends DoubleBinding {
	
	private NumberExpression base;
	private NumberExpression exponent;
	
	private PowBinding(NumberExpression base, NumberExpression exponent) {
		this.base = base;
		this.exponent = exponent;
		bind(base, exponent);
	}
	
	@Override
	protected double computeValue() {
		return Math.pow(base.doubleValue(), exponent.doubleValue());
	}
	
	public static PowBinding pow(NumberExpression base, NumberExpression exponent) {
		return new PowBinding(base, exponent);
	}

}
