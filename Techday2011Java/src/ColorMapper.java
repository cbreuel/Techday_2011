import java.util.HashMap;
import java.util.Map;


public class ColorMapper {

	private static String[] COLORS = {"white", "blue"};
	
	public static Map<String, String> mapLineColors(String[] values) {
		Map<String, String> retVal = new HashMap<String, String>();
		int i = 0;
		for (String value: values) {
			retVal.put(value, COLORS[i % COLORS.length]);
			i++;
		}
		return retVal;
	}
	
	public static void main(String[] args) {
		
		String[] values = {"Clojure", "Java", "Scala", "Visual Basic"};
		
		System.out.println(mapLineColors(values));
	}
	
}
