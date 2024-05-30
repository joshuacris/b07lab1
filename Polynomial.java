import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {
	double [] coef;
	int [] exps;
	
	public Polynomial() {
		this.coef = new double[0];
		this.exps = new int[0];
	} 
	
	public Polynomial(double [] coeffs, int [] exponents) {
		if (coeffs.length != exponents.length) {
			throw new IllegalArgumentException("Coefficients and exponents arrays must have the same length");
		}
		
		this.coef = new double[coeffs.length];
		for (int i = 0; i < coeffs.length; i++)
			this.coef[i] = coeffs[i];
		
		this.exps = new int[exponents.length];
		for (int c = 0; c < exponents.length; c++)
			this.exps[c] = exponents[c];
	}
	
	public Polynomial(File text) throws Exception{
		BufferedReader input = new BufferedReader(new FileReader(text));
		String line = input.readLine();
		
		String[] arr1 = line.split("((?=[+-]+))");
		
		this.coef = new double[arr1.length];
		this.exps = new int[arr1.length];
		int index = 0;
        
        for (String b : arr1) {
        	String[] arr2 = b.split("x");
        	if (arr2.length < 1) {
        		this.coef[index] = 1;
        		this.exps[index] = 1;
        		index++;
        	} else if (arr2.length == 1) {
        		this.coef[index] = Double.parseDouble(arr2[0]);
        		this.exps[index] = 0;
        		index++;
        	} else {
        		this.coef[index] = Double.parseDouble(arr2[0]);
        		this.exps[index] = Integer.parseInt(arr2[1]);
        		index++;
        	}
        	
        }
	}
	
	public Polynomial add(Polynomial p) {
		if (p.exps.length < 1) {
			return this;
		}
		if (this.exps.length < 1) {
			return p;
		}
		int max = p.exps.length + this.exps.length;
		
		double[] r_coeff = new double[max];
		int[] r_exp = new int[max];
		
		
		if (p.exps.length < this.exps.length) {
			int index = 0;
			for (int c = 0; c < this.exps.length; c++) {
				r_coeff[c] = this.coef[c];
				r_exp[c] = this.exps[c];
				index++;
			}
			
			for (int i = 0; i < p.exps.length; i++) {
				boolean match = false;
				for (int j = 0; j < this.exps.length; j++)
					if (p.exps[i] == this.exps[j]) {
						match = true;
						r_coeff[j] += p.coef[i];
						break;
					}
				if (match == false) {
					r_coeff[index] = p.coef[i];
					r_exp[index] = p.exps[i];
					index++;
				}
			}
			
		} else {
			int index = 0;
			for (int c = 0; c < p.exps.length; c++) {
				r_coeff[c] = p.coef[c];
				r_exp[c] = p.exps[c];
				index++;
			}
			
			for (int i = 0; i < this.exps.length; i++) {
				boolean match = false;
				for (int j = 0; j < p.exps.length; j++)
					if (this.exps[i] == p.exps[j]) {
						match = true;
						r_coeff[j] += this.coef[i];
						break;
					}
				if (match == false) {
					r_coeff[index] = this.coef[i];
					r_exp[index] = this.exps[i];
					index++;
				}
			}
			
		}
		
		Polynomial result = new Polynomial(r_coeff, r_exp);
		int final_length = 0;

		double[] f1_coeff = new double[result.coef.length];
		int[] f1_exp = new int[result.coef.length];
		for (int b = 0; b < result.coef.length; b++) {
			if (result.coef[b] == 0.0) {
				continue;
			}
			f1_coeff[final_length] = result.coef[b];
			f1_exp[final_length] = result.exps[b];
			final_length++;
		}
		
		double[] f_coeff = new double[final_length];
		int[] f_exp = new int[final_length];
		
		for (int d = 0; d < final_length; d++) {
			f_coeff[d] = f1_coeff[d];
			f_exp[d] = f1_exp[d];
		}
		
		
		
		return new Polynomial(f_coeff, f_exp);
	}
	
	
	public Polynomial multiply(Polynomial p) {
		
		if (p.exps.length < 1) {
			return p;
		}
		
		if (this.exps.length < 1) {
			return this;
		}
		Polynomial result = new Polynomial();
		
		if (p.exps.length <= this.exps.length) {
			for (int i = 0; i < p.exps.length; i++) {
				double[] r_coef = new double[this.exps.length];
				int[] r_exps = new int[this.exps.length];
				
				for (int j = 0; j < this.exps.length; j++) {
					r_coef[j] = p.coef[i] * this.coef[j];
					r_exps[j] = p.exps[i] + this.exps[j];
				}
				
				Polynomial temp = new Polynomial(r_coef, r_exps);
				result = result.add(temp);
			}
			
			return result;
		} else {
			
			for (int i = 0; i < this.exps.length; i++) {
				double[] r_coef = new double[p.exps.length];
				int[] r_exps = new int[p.exps.length];
				
				for (int j = 0; j < p.exps.length; j++) {
					r_coef[j] = this.coef[i] * p.coef[j];
					r_exps[j] = this.exps[i] + p.exps[j];
				}
				
				Polynomial temp = new Polynomial(r_coef, r_exps);
				result = result.add(temp);
			}
			
			return result;
		}
	}
	
	public void saveToFile(String text) throws IOException{
		File to_write = new File(text);
		FileWriter result = new FileWriter(to_write, false);
		if (this.exps.length < 1) {
			result.flush();
			result.close();
			return;
		}
		result.write(this.coef[0] + "x" + this.exps[0]);
		for (int i = 1; i < this.exps.length; i++) {
			if (this.coef[i] < 0) {
				if (this.exps[i] < 1) {
					result.write(""+this.coef[i]);
				} else {
				result.write(this.coef[i] + "x" + this.exps[i]);
				}
			} else {
				if (this.exps[i] < 1) {
					result.write("+" + this.coef[i]);
				} else {
				result.write("+" + this.coef[i] + "x" + this.exps[i]);
				}
			}
		}
		
		result.flush();
		result.close();
	}
	
	public double evaluate(double x) {
		double results = 0;
		for (int i = 0; i < coef.length; i++)
			results += coef[i]*Math.pow(x, exps[i]);
		return results;
	}
	
	public boolean hasRoot(double k) {
		return evaluate(k) == 0;
	}
	
	//supplementary method
	
	public void printpoly() {
		for (int i = 0; i < this.exps.length; i++) {
	        System.out.println(this.coef[i]+"x^"+this.exps[i]);
	       }
	}
	
}