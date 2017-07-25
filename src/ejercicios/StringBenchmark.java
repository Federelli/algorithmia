package ejercicios;

public class StringBenchmark {
	public static void main(String[] args) {
		int N = 77777777;
		long t;

		{	//Bad
			StringBuffer sb = new StringBuffer();
			t = System.currentTimeMillis();
			for (int i = N; i-- > 0;) {
				sb.append("");
			}
			System.out.println(System.currentTimeMillis() - t);
		}

		{	//Better
			StringBuilder sb = new StringBuilder();
			t = System.currentTimeMillis();
			for (int i = N; i-- > 0;) {
				sb.append("");
			}
			System.out.println(System.currentTimeMillis() - t);
		}
		
		{	//Worst
			String s = "";
			t = System.currentTimeMillis();
			for (int i = N; i-- > 0;) {
				s += "";
			}
			System.out.println(System.currentTimeMillis() - t);
		}
	}
}
