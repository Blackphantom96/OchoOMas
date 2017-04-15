package aplicacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class OchoOmas {
	private int[][] matrix;
	private int tamx,tamy;
	private int[] hole;
	
	public OchoOmas(int x, int y) {
		reset(x,y);
	}

	private ArrayList<Integer> getRandomNumbers(int xSize2, int ySize2) {
		ArrayList<Integer> res = new ArrayList<>();
		Random inte = new Random();
		while (res.size() != (xSize2 * ySize2) - 1) {
			int a = inte.nextInt((xSize2 * ySize2) - 1)+1 ;
			if (res.indexOf(a) < 0) {
				res.add(a);
			}
		}
		return res;
	}
	public boolean changeOrder(int i){
		int[] index = getIndex(i);
		if(Math.abs(Math.abs(index[0]-hole[0])+Math.abs(index[1]-hole[1])) ==1){
			matrix[hole[1]][hole[0]]=i;
			hole=copy(index);
			matrix[index[1]][index[0]]=0;
			return true;
		}
		return false;
	}

	private int[] copy(int[] a){
		int [] temp= new int[a.length];
		for (int x=0;x<a.length;x++)
	        temp[x] = a[x];
		return temp;
	}
	private int[] getIndex(int k){
		int[] res = new int[2];
		for (int i = 0; i < tamy; i++) {
			for (int j = 0; j < tamx; j++) {
				if(matrix[i][j]==k)
					return res=new int[]{j,i};
			}
		}
		return res;
	}
	
	private boolean isAlmostOneOrdered(ArrayList<Integer> numbers) {
		int j = numbers.get(0);
		for (int i : numbers) {
			if (i - 1 == j) {
				return true;
			}
			j = i;
		}
		return false;
	}
	public void reset(int x,int y){
		tamx=x;
		tamy=y;
		matrix = new int[tamy][tamx];
		hole=new int[]{tamx-1,tamy-1};
		ArrayList<Integer> temporalArray = getRandomNumbers(tamy, tamx);
		while(isAlmostOneOrdered(temporalArray))
			temporalArray=getRandomNumbers(tamy,tamx);
		temporalArray.add(0);
		int k = 0;
		for (int i = 0; i < tamy; i++) {
			for (int j = 0; j < tamx; j++) {
				matrix[i][j] = temporalArray.get(k++);
			}
		}
	}
	public int getNumber(int y, int x){
		return matrix[y][x];
	}
	public boolean isSolved(){
		int k = 1;
		int temp[][]=new int[tamy][tamx];
		for (int i = 0; i < tamy; i++) {
			for (int j = 0; j < tamx; j++) {
				temp[i][j] = k++;
			}
		}
		temp[tamy-1][tamx-1]=0;
		return Arrays.deepEquals(temp, matrix);
	}
	public void printmat(int[][] a){
		for(int[]b:a){
			System.out.println(Arrays.toString(b));
		}
	}
}
