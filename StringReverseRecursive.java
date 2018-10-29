public class StringReverseRecursive {

	// 
	public StringBuilder reverse(String str, StringBuilder strB, int len) {
		if(len < 0) return strB;
		strB.append(str.charAt(len));		
		return reverse(str, strB, --len);
	}

	public static void main(String[] args) {
		StringReverseRecursive sol = new StringReverseRecursive();
		String str = "ABCDE";
		System.out.println(sol.reverse(str, new StringBuilder(), str.length()-1));
	}
}