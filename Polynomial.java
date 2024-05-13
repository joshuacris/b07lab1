public class Polynomial {
	double [] coef;
	
	
	public Polynomial() {
		this.coef = new double[] {0};
	}
	
	public Polynomial(double [] array) {
		this.coef = new double[array.length];
		for (int i = 0; i < array.length; i++)
			this.coef[i] = array[i];
	}
	
	public Polynomial add(Polynomial p) {
		double [] results;
		if (p.coef.length <= coef.length) {
			results = new double[coef.length];
			for (int i = 0; i < p.coef.length; i++)
				results[i] = p.coef[i]+coef[i];
			for (int j = p.coef.length; j < coef.length; j++)
				results[j] = coef[j];
		} else {
			results = new double[p.coef.length];
			for (int i = 0; i < coef.length; i++)
				results[i] = p.coef[i]+coef[i];
			for (int j = coef.length; j < p.coef.length; j++)
				results[j] = p.coef[j];
		}
		
		return new Polynomial(results);
	}
	
	
	public double evaluate(double x) {
		double results = 0;
		for (int i = 0; i < coef.length; i++)
			results += coef[i]*Math.pow(x, i);
		return results;
	}
	
	public boolean hasRoot(double k) {
		return evaluate(k) == 0;
	}
	
}