import java.awt.EventQueue;
/**
 * 
 * 
 * Clase principal
 * 
 * @author Roberto Gonz�lez Mart�n
 * @version 1.0
 * @since 1.0
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
