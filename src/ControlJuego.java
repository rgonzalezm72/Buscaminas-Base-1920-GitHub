import java.util.Random;

/**
 * 
 * 
 * Clase gestora del tablero de juego. Guarda una matriz de enteros representado
 * el tablero. Si hay una mina en una posici�n guarda el n�mero -1 Si no hay una
 * mina, se guarda cu�ntas minas hay alrededor. Almacena la puntuaci�n de la
 * partida
 * 
 * @author Roberto Gonz�lez Mart�n
 * @version 1.0
 * @since 1.0
 * 
 */
public class ControlJuego {
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int[][] tablero;
	private int puntuacion;

	public ControlJuego() {
		// Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];

		// Inicializamos una nueva partida
		inicializarPartida();
	}

	/**
	 * M�todo para generar un nuevo tablero de partida:
	 * 
	 * @pre: La estructura tablero debe existir.
	 * @post: Al final el tablero se habr� inicializado con tantas minas como marque
	 *        la variable MINAS_INICIALES. El resto de posiciones que no son minas
	 *        guardan en el entero cu�ntas minas hay alrededor de la celda
	 */
	public void inicializarPartida() {

		// TODO: Repartir minas e inicializar puntuaci�n. Si hubiese un tablero
		// anterior, lo pongo todo a cero para inicializarlo.
		puntuacion = 0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = 0;
			}
		}
		for (int i = 0; i < MINAS_INICIALES; i++) {
			boolean esMina = false;
			do {
				int random1 = new Random().nextInt(LADO_TABLERO);
				int random2 = new Random().nextInt(LADO_TABLERO);
				if(tablero[random1][random2] == MINA) {
					esMina = true;
				}
				else {
					esMina = false;
					tablero[random1][random2] = MINA;
				}
			}while(esMina);
		}
		// Al final del m�todo hay que guardar el n�mero de minas para las casillas que
		// no son mina:
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA) {
					tablero[i][j] = calculoMinasAdjuntas(i, j);
				}
			}
		}
		depurarTablero();
	}

	/**
	 * C�lculo de las minas adjuntas: Para calcular el n�mero de minas tenemos que
	 * tener en cuenta que no nos salimos nunca del tablero. Por lo tanto, como
	 * mucho la i y la j valdr�n LADO_TABLERO-1. Por lo tanto, como poco la i y la j
	 * valdr�n 0.
	 * 
	 * @param i: posici�n vertical de la casilla a rellenar
	 * @param j: posici�n horizontal de la casilla a rellenar
	 * @return : El n�mero de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int i, int j) {
		int contador = 0;
		for (int k = -1; k < 2; k++) {
			for (int l = -1; l < 2; l++) {
				if(i+k< LADO_TABLERO && i+k>=0 && j+l<LADO_TABLERO && j+l>=0) {
					if(tablero[i+k][j+l]==MINA) {
						contador++;
					}
				} 
			}
		}
		return contador;
	}

	/**
	 * M�todo que nos permite
	 * 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por
	 *      el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posici�n verticalmente de la casilla a abrir
	 * @param j: posici�n horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j) {
		if (tablero[i][j] == MINA) {
			return false;
		}
		puntuacion++;
		return true;
	}

	/**
	 * M�todo que checkea si se ha terminado el juego porque se han abierto todas
	 * las casillas.
	 * 
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son
	 *         minas.
	 **/
	public boolean esFinJuego() {
		if(puntuacion==((tablero.length*tablero.length)-MINAS_INICIALES)) {
			return true;
		}
		return false;
	}

	/**
	 * M�todo que pinta por pantalla toda la informaci�n del tablero, se utiliza
	 * para depurar
	 */
	public void depurarTablero() {
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuaci�n: " + puntuacion);
	}

	/**
	 * M�todo que se utiliza para obtener las minas que hay alrededor de una celda
	 * 
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta
	 *      calcularlo, s�mplemente consultarlo
	 * @param i : posici�n vertical de la celda.
	 * @param j : posici�n horizontal de la cela.
	 * @return Un entero que representa el n�mero de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return tablero[i][j];
	}

	/**
	 * M�todo que devuelve la puntuaci�n actual
	 * 
	 * @return Un entero con la puntuaci�n actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
}
