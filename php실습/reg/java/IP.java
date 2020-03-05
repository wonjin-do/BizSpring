
public class IP {

	public static void main(String[] args) {
		
		String regExp = "^([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d?\\d|2[0-4]\\d|25[0-5])$";
		String ip[]  = {"127.0.0.1"
						,"0.0.0.0"
						,"255.255.255.255"
						,"0-0-0-0"
						,"256.0.0.1"};
		for(String elem : ip) {
			System.out.println("ip 테스트 결과 ==> " + elem.matches(regExp));
		}
	}
	
	
}
