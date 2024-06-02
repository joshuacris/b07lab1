import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) throws Exception{

		Polynomial test8 = new Polynomial(new double[] {1, 1, 2}, new int[] {1, 2, 5});
		Polynomial test9 = new Polynomial(new double[] {-1,-1}, new int[] {2, 1});
		Polynomial result9 = test8.add(test9);
		result9.printpoly();
		//result9.saveToFile("c.txt");
		
		System.out.println("\nnew test\n");
		
		Polynomial test10 = new Polynomial(new double[] {56, 1}, new int[] {0, 1});
		Polynomial test11 = new Polynomial(new double[] {1, -56}, new int[] {1, 0});
		Polynomial result10 = test11.multiply(test10);
		result10.printpoly();
		//result10.saveToFile("c.txt");
		
		System.out.println("\nnew test\n");
		
		Polynomial test1 = new Polynomial(new double[] {1, 1}, new int[] {1, 0});
		Polynomial test2 = test1;
		Polynomial result1 = test2.multiply(test1);
		result1.printpoly();
		System.out.println("\nnew test\n");
		result1 = result1.add(result1);
		result1.printpoly();
		
		System.out.println("\nnew test\n");
		
		//TEST WITH A FILE, NOT INCLUDED IN GIT REPO
		/*Polynomial test3 = new Polynomial();
		Polynomial test4 = new Polynomial();
		test4.saveToFile("d.txt");
		Polynomial test5 = new Polynomial(new File("text.txt"));
		test5.printpoly(); // -1.5x2 - 1x0 + 3.4x4 - 8.6 - 1x1 + 3x5
		System.out.println("\nnew test\n"); 
		Polynomial result2 = test5.multiply(test4); 
		result2.printpoly(); // nothing
		System.out.println("\nnew test\n");
		result2 = test5.add(test3); //same as test5
		result2 = result2.add(test4); //same as test5
		result2.printpoly(); //same as test5
		System.out.println("\nnew test\n");
		result2 = result2.multiply(result2);
		result2.printpoly(); //  it squared
		System.out.println("\nnew test\n");
		result2 = test5.add(test5); 
		result2.printpoly(); // double
		System.out.println("\nnew test\n");
		result2 = test5; 
		double s = result2.evaluate(2.5);
		System.out.println(s);
		System.out.println("root: " + result2.hasRoot(2.5));
		System.out.println("\nnew test\n");*/
		
		Polynomial test6 = new Polynomial(new double[] {-2.22, -0.3}, new int[] {4, 3});
		Polynomial test7 = new Polynomial(new double[] {-101, 8.0, 1}, new int[] {1, 0, 2});
		
		test6.printpoly(); //-2.22x4 - 0.3x3
		test7.printpoly(); // -101x1 + 8 + 1x2
		System.out.println("\nnew test\n");
		Polynomial result3 = test7.multiply(test6);
		//result3.saveToFile("c.txt");
		result3.printpoly();
		System.out.println("\nnew test\n");
		result3 = test6.multiply(test7);
		result3.printpoly();
		System.out.println("\nnew test\n");
		System.out.println(result3.evaluate(-23.5));
		System.out.println("\nnew test\n");
		result3 = test6.add(test7);
		result3.printpoly();
		System.out.println("\nnew test\n");
		result3 = test7.add(test6);
		result3.printpoly();
		System.out.println("\nnew test\n");
		System.out.println(result3.evaluate(23.5));
		//result3.saveToFile("b.txt");
		
		Polynomial test50 = new Polynomial(new double[] {1, 1}, new int[] {1,0});
		Polynomial test51 = new Polynomial(new double[] {-1, -1}, new int[] {0, 1});
		Polynomial result50 = test50.add(test51);
		result50.printpoly();
		System.out.println(result50.evaluate(24243.3422));
		System.out.println("root: " + result50.hasRoot(23));

	}
}