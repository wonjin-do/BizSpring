
public class URL {

	public static void main(String[] args) {
		
		String regExp = "^((http|https)://)?(www.)?([a-zA-Z0-9]+)\\.[a-z]+([a-zA-z0-9.?#]+)?";

		
		String url[] 
					  = {"june"
						,"website"
						,"http:web.site"
						,"www.web.site"
						,"http://web.site"
						,"https://june.me"};
		for(String elem : url) {
			System.out.println("url 테스트 결과 ==> " + elem.matches(regExp));
		}
	}
	
	
}
