package ru.geekbrains.algorithms;

import java.util.Map;

public class Main {

	public static void main(String[] args) {
		String str = "kjdsla;jn vjkpanejuipnvdajnfcxzlvjnbiwapeufusdfjkl"
				+ "asd;jfkljasl;fj;ldjsakfl;jl;fjknvkjcx;nvj;nhewuroqngjvcb"
				+ "ldsfajl;jkasljfkldajskfDSFAASDFVCVEWHNKJ,,JJHGKMBl;asjdfoi"
				+ "dfsadsaffdsafdsafSA,IUOPHJKdsafsdafdsafvcgfrbfgnhhgdsfgdfv";
		System.out.println(ReverseString.reverse("safjk;sadjf"));
		Map<String, Integer> hist = CharHistogram.hist(str);
		for (String ch : hist.keySet()){
			System.out.println(ch +": " + hist.get(ch));
		}

	}

}
