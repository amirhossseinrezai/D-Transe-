import java.util.ArrayList;
import java.util.List;


public class D_Transe {
	private static List<String> array_sigma = new ArrayList<String>();
	private static List<String> array_state = new ArrayList<String>();
	private static List<String> array_inputString = new ArrayList<String>();
	List<String> endState=new ArrayList<String>();
	private String inputString;
	private String finalState;
	private String sigma;
	private int state;

	private String[] words = {"B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private List<String> counter=new ArrayList<String>();
	public D_Transe(int state, String inputString, String sigma,String finalState) {
		this.state = state;
		this.inputString = inputString;
		this.sigma = sigma;
		this.finalState=finalState;
	}
	public String getFinalState() {
		return finalState;
	}

	public void setFinalState(String finalState) {
		this.finalState = finalState;
	}
	public String getInputString() {
		return inputString;
	}

	public void setInputString(String inputString) {
		this.inputString = inputString;
	}

	public String getSigma() {
		return sigma;
	}

	public void setSigma(String sigma) {
		this.sigma = sigma;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void scan() {
		System.out.println("These Are Our Inputs :");
		for (String input : getInputString().split(",")) {
			array_inputString.add(input);
		}

		System.out.println(array_inputString);
		for (int i = 0; i < getState(); i++) {
			array_state.add(i + "");
		}
		System.out.println("______________________________" + "\nThese Are Our States :" + "\n" + array_state);
		for (String string : getSigma().split("")) {
			array_sigma.add(string);
		}
		System.out.println("______________________________" + "\nThese Are Our Sigma :" + "\n" + array_sigma);
	}

	List<String> zero_L = new ArrayList<String>();

	public List<String> zeroLClouser() {
		System.out.println("______________________");
		zero_L.add("0");
		for (int i = 0; i < zero_L.size(); i++) {
			for (int j = 0; j < array_inputString.size(); j++) {
				if (array_inputString.get(j).contains("L")) {
					int index = array_inputString.get(j).indexOf("L");
					String str = array_inputString.get(j).substring(0, index);
					if (str.equals(zero_L.get(i))) {
						zero_L.add(array_inputString.get(j).substring(array_inputString.get(j).indexOf("L") + 1,
								array_inputString.get(j).length()));
					}
				}
			}
		}
		return zero_L;
	}
	List<String> firstOut =new ArrayList<String>();
	List<String> out_A = new ArrayList<String>();
	public List<String> move() {
		List<String> out_first = new ArrayList<String>();
		String[] sigma = getSigma().split("");
		for (int k = 0; k < sigma.length; k++) {
			for (int j = 0; j < array_inputString.size(); j++) {
				if (array_inputString.get(j).indexOf(sigma[k].toString()) != (-1)) {
					int index = array_inputString.get(j).indexOf(sigma[k].toString());
					String str = array_inputString.get(j).substring(0, index);
					for (String string : zero_L) {
						if (str.equals(string)) {
							String out = array_inputString.get(j).substring(array_inputString.get(j).indexOf(sigma[k])+1);
							//if(!out.equals("[]")) {
							if(!out_first.contains(out)) {
								out_first.add(out);
							}
							//}
						}
					}
				} else {

					continue;
				}
			}
			if(out_first.size()!=0) {
				out_A.add(out_first.toString());
				firstOut.add(sigma[k]);
				System.out.print("Move --> "+ sigma[k] + " --> " + out_first + "\n");
			}else {
				out_A.add(out_first.toString());
				firstOut.add(sigma[k]);
			}
			out_first.removeAll(out_first);
		}
		return out_A;
	}
	public List<String> l_Closure(List<String> out) {
		List<String> out_B = new ArrayList<String>();
		List<String> com = new ArrayList<String>();
		int a=0;
		for (int i = 0; i < out.size(); i++) {
			if (out.get(i).length() > 3) {
				String[] str = out.get(i).split(",");
				str[0] = str[0].substring(str[0].indexOf("[")+1);
				str[str.length - 1] = str[str.length - 1].substring(0, str[str.length - 1].length() - 1).trim();
				for (String string : str) {
					com.add(string);
				}
			} else {
				com.add(out.get(i).substring(1, out.get(i).length() - 1));
			}
			for (int k = 0; k < com.size(); k++) {
				for (int j = 0; j < array_inputString.size(); j++) {
					if (array_inputString.get(j).contains("L")) {
						int index = array_inputString.get(j).indexOf("L");
						String str = array_inputString.get(j).substring(0, index);
						if (str.equals(com.get(k))) {
							String s = array_inputString.get(j).substring(array_inputString.get(j).indexOf("L") + 1);
							if (existing(com, s)) {
								com.add(s.trim());							
							}
						}
					}
				}

			}
			counter.add(com.toString());
			out_B.add(com.toString());
			com.removeAll(com);
		}
		out.removeAll(out);
		return out_B;
	}
	public boolean existing(List<String> lst,String st) {
		if(lst.contains(st)) {
			return false;
		}else {
			return true;
		}
	}
	List<String> dfa = new ArrayList<String>();
	List<String> zero =new ArrayList<String>();
	public List<String> compare() {
		int c = 0,a=0,p=0,o=0;
		zero.add(zero_L.toString().trim());
		List<String> list=new ArrayList<String>(l_Closure(move()));
		for(int m = 0 ; m <list.size();m++) {
			if(!list.get(m).equals("[]")) {
				if(!list.get(m).equals(zero.get(0))) {
					if(finalStates(list.get(m))) {
						if(!endState.contains(words[c])) {
							endState.add(words[c]);
						}
					}
					System.out.println(words[c]+ "=" + list.get(m));
					c++;
				}
			}else {
				list.remove(list.get(m));
			}
		}
		for(int l = 0 ; l < c ; l++) {
			dfa.add("A"+firstOut.get(o)+words[l]);
			o++;
		}
		System.out.println("--------------------------");
		for(int i=0 ; i < zero.size();i++) {
			if(list.contains(zero.get(i))) {
				list.remove(zero.get(i));
			}else {
				for(int j = 0 ; j<list.size();j++) {
					zero_L.removeAll(zero_L);
					if(list.get(j).length()>3) {
						String[] s = list.get(j).split(",");
						s[0] = s[0].substring(s[0].indexOf("[")+1);
						s[s.length-1] = s[s.length-1].substring(0,s[s.length-1].length()-1);
						for (String string : s) {
							zero_L.add(string.trim());
						}
						if(!zero.contains(zero_L.toString().trim())) {
							zero.add(zero_L.toString().trim());
							System.out.println(words[a]+"-->"+zero_L.toString().trim());
							a++;
						}
						List<String> values = new ArrayList<String>(l_Closure(move()));
						
						for(int k = 0 ; k < values.size();k++) {
							if(list.contains(values.get(k))) {
								for(int n = 0; n<list.size();n++) {
									if(list.get(n).equals(values.get(k))) {
										if(finalStates(values.get(k))) {
											if(!endState.contains(words[n])) {
												endState.add(words[n]);
											}
										}
										dfa.add(words[p]+firstOut.get(o)+words[n]);
										System.out.println(words[n]+ "=" + values.get(k));
										o++;
									}
								}
							}else {
								if(values.get(k).equals("[]")) {
									dfa.add(words[p]+firstOut.get(o)+"(Trap)");
									o++;
								} 
								else {
									list.add(values.get(k));
									for(int n = 0; n<list.size();n++) { 
										if(list.get(n).equals(values.get(k))) {
											if(finalStates(values.get(k))) {
												if(!endState.contains(words[n])) { 
													endState.add(words[n]); 
												}
											}
											dfa.add(words[p]+firstOut.get(o)+words[n]); 
											System.out.println(words[n]+ "="+ values.get(k)); 
											o++; 
										}
									}
								}
							}
						}p++;
						System.out.println("--------------------------");
					}else {
						zero_L.add(list.get(j).substring(1,list.get(j).length()-1));
						if(!zero.contains(zero_L.toString().trim())) {
							zero.add(zero_L.toString().trim());
							System.out.println(words[a]+"-->"+zero_L.toString().trim());
							a++;
						}
						List<String> values = new ArrayList<String>(l_Closure(move()));
						for(int k = 0 ; k < values.size();k++) {
							if(list.contains(values.get(k))) {
								for(int n = 0; n<list.size();n++) {
									if(list.get(n).equals(values.get(k))) {
										if(finalStates(values.get(k))) {
											if(!endState.contains(words[n])) {
												endState.add(words[n]);
											}
										}
										dfa.add(words[p]+firstOut.get(o)+words[n]);
										System.out.println(words[n]+ "=" + values.get(k));
										o++;
									}
								}
							}else {
								if(values.get(k).equals("[]")) {
									dfa.add(words[p]+firstOut.get(o)+"(Trap)");
									o++;
								} 
								else {
									list.add(values.get(k));
									for(int n = 0; n<list.size();n++) { 
										if(list.get(n).equals(values.get(k))) {
											if(finalStates(values.get(k))) {
												if(!endState.contains(words[n])) { 
													endState.add(words[n]); 
												}
											}
											dfa.add(words[p]+firstOut.get(o)+words[n]); 
											System.out.println(words[n]+ "="+ values.get(k)); 
											o++; 
										}
									}
								}
							}
						}p++;
						System.out.println("--------------------------");
					}
				}
			}
		}
		for (String string : getSigma()) {
			dfa.add("(Trap)"+string+"(Trap)");
		}
		System.out.println("DFA = " + dfa);
		System.out.println("Final States = " + endState);
		return zero;
	}

	public boolean finalStates(String string) {
		String[] _final=getFinalState().split(",");
		for(int j=0;j<_final.length;j++) {
			if(string.contains(_final[j].trim())) {
				return true;
			}
		}
		return false;
	}
}