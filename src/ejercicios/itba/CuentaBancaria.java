package ejercicios.itba;

public class CuentaBancaria {
	private static final int montoInicial = 1000;

	private long monto = montoInicial;

	public long leerMonto() {
		return monto;
	}

	public void depositar(int valor) {
		monto += valor;
	}
}
