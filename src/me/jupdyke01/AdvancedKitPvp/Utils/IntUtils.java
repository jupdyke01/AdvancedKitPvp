package me.jupdyke01.AdvancedKitPvp.Utils;

public class IntUtils {

	public static int closestNumber(int n, int m) {
		int q, n1, n2;
		q = n / m;
		n1 = m * q;

		if ((n * m) > 0)
			n2 = m * (q + 1);
		else
			n2 = m * (q - 1);

		if (Math.abs(n-n1) < Math.abs(n-n2))
			return n1;
		return n2 ;
	}
	
}