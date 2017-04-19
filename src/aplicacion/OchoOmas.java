package aplicacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author 2104784
 *
 */
public class OchoOmas {
	private int[][] matrix;
	private int tamx, tamy;
	private int[] hole;

	
	/**
	 * creador de la clase logica
	 * @param x pos en x
	 * @param y pos en y
	 */
	public OchoOmas(int x, int y) {
		reset(x, y);
	}


	/**
	 * devulevve la matriz unicamente para pruebas
	 * @return
	 */
	public int[][] getMatrix() {
		return matrix;
	}


	/**
	 * me da un arreglo de numeros aleatorios desde 0 a x*y
	 * @param xSize2 tamanno de matriz en x
	 * @param ySize2 tamanno de matriz en y
	 * @return
	 */
	private ArrayList<Integer> getRandomNumbers(int xSize2, int ySize2) {
		ArrayList<Integer> res = new ArrayList<>();
		Random inte = new Random();
		while (res.size() != (xSize2 * ySize2)) {
			int a = inte.nextInt((xSize2 * ySize2));
			if (res.indexOf(a) < 0) {
				res.add(a);
			}
		}
		return res;
	}


	/**
	 * cambia de orden el numero i si es posible
	 * @param i el numero a cambiar
	 * @return retorna si es posible cambiar 
	 */
	public boolean changeOrder(int i) {
		int[] index = getIndex(i, matrix);
		if (Math.abs(Math.abs(index[0] - hole[0]) + Math.abs(index[1] - hole[1])) == 1) {
			matrix[hole[1]][hole[0]] = i;
			hole = copy(index);
			matrix[index[1]][index[0]] = 0;
			return true;
		}
		return false;
	}


	/**
	 * copia un arreglo
	 * @param a el arreglo a copiar
	 * @return el areeglo copaido
	 */
	private int[] copy(int[] a) {
		int[] temp = new int[a.length];
		for (int x = 0; x < a.length; x++)
			temp[x] = a[x];
		return temp;
	}

	/**
	 * me da la posicion y,x de un numero en la matriz
	 * @param k el numeor a buscar
	 * @param ma la matriz
	 * @return las posicion
	 */
	private int[] getIndex(int k, int[][] ma) {
		int[] res = new int[2];
		for (int i = 0; i < tamy; i++) {
			for (int j = 0; j < tamx; j++) {
				if (ma[i][j] == k)
					return res = new int[] { j, i };
			}
		}
		return res;
	}


	/**
	 * revisa si almenos dos numeros estan ordenados dentro de la matriz
	 * @param numbers los numeros a rrevisar
	 * @return un booleano que inidca si esta ordenado
	 */
	private boolean isAlmostTwoOrdered(ArrayList<Integer> numbers) {
		int k = 1;
		int count = 0;
		for (int i : numbers) {
			if (k == i)
				count++;
			k++;
		}
		count = numbers.get(numbers.size() - 1) == 0 ? count++ : count;
		return count >= 2;
	}


	/**
	 * inicializa la matriz con unos tamaños x y 
	 * @param x el tamaño en x 
	 * @param y el tamaño en y
	 */
	public void reset(int x, int y) {
		tamx = x;
		tamy = y;
		matrix = new int[tamy][tamx];
		ArrayList<Integer> temporalArray = getRandomNumbers(tamy, tamx);
		while (!isAlmostTwoOrdered(temporalArray))
			temporalArray = getRandomNumbers(tamy, tamx);
		temporalArray.add(0);
		int k = 0;
		for (int i = 0; i < tamy; i++) {
			for (int j = 0; j < tamx; j++) {
				matrix[i][j] = temporalArray.get(k++);
			}
		}
		hole = getIndex(0, matrix);
	}


	/**
	 * me da el numero en la posicion pedida
	 * @param y la pos en y
	 * @param x la pos en x
	 * @return el numero...
	 */
	public int getNumber(int y, int x) {
		return matrix[y][x];
	}


	/**
	 * dice si un numero esta en la posuicion correcta
	 * @param a el numeor a consulotar
	 * @return si esta ordenado o no 
	 */
	public boolean isSolved(int a) {
		int k = 1;
		int temp[][] = new int[tamy][tamx];
		for (int i = 0; i < tamy; i++) {
			for (int j = 0; j < tamx; j++) {
				temp[i][j] = k++;
			}
		}
		temp[tamy - 1][tamx - 1] = 0;
		return Arrays.equals(getIndex(a, temp), getIndex(a, matrix));
	}

}
