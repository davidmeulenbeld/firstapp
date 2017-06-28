package nl.hu.v1wac.firstapp.servlets;

public class Calc {
	private int inp_1;
	private int inp_2;
	
	
	public Calc(int i_1, int i_2){
		inp_1 = i_1;
		inp_2 = i_2;
	}

	public int Bereken(String methode){
		int out = 0;
		
		if(methode.compareTo("min") == 0){
			out = inp_1 - inp_2;
		}
		if(methode.compareTo("sum") == 0){
			out = inp_1 + inp_2;
		}
		if(methode.compareTo("div") == 0){
			out = inp_1 / inp_2;
		}
		if(methode.compareTo("mul") == 0){
			out = inp_1 * inp_2;
		}
		
		return out;
	}
}
