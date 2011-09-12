import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;


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
	
	public void hofExamples() {
		
		new Thread(new Runnable() {
			public void run() {
				System.out.println("Hello, Thread");
			}
		});
		
		
		String[] dnaBases = {"AG", "GGATAC", "CTG"};
		Arrays.sort(dnaBases, new Comparator<String>() {
			public int compare(String arg0, String arg1) {
				int l1 = arg0.length(), l2 = arg1.length();
				if (l1 > l2) {
					return 1;
				} else if (l1 < l2) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		
//		JComponent cmp = new JTextArea();
//		cmp.addKeyListener(new KeyListener() {
//			public void keyTyped(KeyEvent arg0) {
//				// TODO Auto-generated method stub
//			}
//			public void keyReleased(KeyEvent arg0) {
//				// TODO Auto-generated method stub
//			}
//			public void keyPressed(KeyEvent arg0) {
//				// TODO Auto-generated method stub
//			}
//		});
		
		int[] iarray = {1, 2, 3, 4};
		int result = 0;
		for (int i: iarray) {
			result += i;
		}

	}
	
}
