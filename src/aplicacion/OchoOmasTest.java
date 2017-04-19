/**
 * 
 */
package aplicacion;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author 2104784
 *
 */
public class OchoOmasTest {

	/**
	 * Test method for {@link aplicacion.OchoOmas#changeOrder(int)}.
	 */
	@Test
	public void testChangeOrder() {
		OchoOmas test =  new OchoOmas(3, 1);
		test.changeOrder(2);
		assertArrayEquals(new int[][]{{1,0,2}},test.getMatrix());
	}

	/**
	 * Test method for {@link aplicacion.OchoOmas#getNumber(int, int)}.
	 */
	@Test
	public void testGetNumberNoDeberiaConsultar() {
		OchoOmas test =  new OchoOmas(3, 3);
		try{
			test.getNumber(3, 3);
		}
		catch(Exception e){
			assertFalse(false);
		}
	}
	/**
	 * Test method for {@link aplicacion.OchoOmas#getNumber(int, int)}.
	 */
	@Test
	public void testGetNumberDeberiaConsultar() {
		OchoOmas test =  new OchoOmas(3, 3);
		try{
			test.getNumber(2, 2);
			assertTrue(true);
		}
		catch(Exception e){
			assertFalse(true);
		}
	}
	/**
	 * Test method for {@link aplicacion.OchoOmas#isSolved(int)}.
	 */
	@Test
	public void testIsSolved() {
		int a=0;
		OchoOmas test =  new OchoOmas(2, 2);
		int[][]ma=test.getMatrix();
		if(ma[1][1]==0)
			a=0;
		if(ma[0][0]==1)
			a=1;
		if(ma[0][1]==2)
			a=2;
		assertTrue(test.isSolved(a));
		
	}

}
