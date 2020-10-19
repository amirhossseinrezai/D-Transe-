import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static String input,sigma,finalState;
	public static int state;
	public static D_Transe dtr;
	public static void main(String[] args) {
		//0L1,0L3,1a2,3b4,2L5,4L5,5L6,5L8,6a7,8b9,7L10,9L10
		//0L1,0L7,1L2,1L4,2a3,4c5,3L6,5L6,6L1,6L7,7b8,8a9,9L10,10L11,10L13,11b12,13c14,12L15,14L15,15L10,15L16
		//0L1,0L7,1L2,1L4,2a3,4b5,3L6,5L6,6L1,6L7,7a8
		//0a1,0a2,1b3,2L3
		//0L1,1a2,0L3,2L1,2L3,3b4,4a5,5L6,6L7,6L9,7a8,9b10,8L11,10L11,11L6
		//0a1,0a2,0L3,1b3,2b3,3a0
		 input="0b1,0a1,1L1,1a3,1a2,2b2,2b3,4a0";
	     state=5;
		 sigma="ab";
		 finalState="0,3";
		 D_Transe dtr = new D_Transe(state,input,sigma,finalState);
			dtr.scan();
			//System.out.println("A=" + dtr.zeroLClouser());
			dtr.zeroLClouser();
			dtr.compare();
		}
}




