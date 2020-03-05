
public class Identification {

	public static void main(String[] args) {
		
		String regExp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
		String identity[] 
					  = {"doWon"
						,"doWon123"
						,"1do"
						,"abcdefghijklmnopqrstuvwxyz"
						,"_______"
						,""};
		for(String elem : identity) {
			System.out.println("identity 테스트 결과 ==> " + elem.matches(regExp));
		}
	}
	
}
