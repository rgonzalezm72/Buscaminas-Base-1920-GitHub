import java.awt.EventQueue;
/**
 * Clase principal
 * 
 * @author Roberto Gonz�lez Mart�n
 *
 */
public class Principal {

	/**
	 * M�todo main
	 * @param args : Cadenas de par�metros del main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal ventana = new VentanaPrincipal();
					ventana.inicializar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
