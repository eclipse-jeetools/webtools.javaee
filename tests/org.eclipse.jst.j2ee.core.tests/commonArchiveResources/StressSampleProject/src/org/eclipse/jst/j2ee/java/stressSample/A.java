package org.eclipse.jst.j2ee.java.stressSample;

/**
 *	test class A, touches all the other classes here.
 *		plus, uses lots of array types.
 */
public class A implements IA {
	public A anA;
	public B aB;
	public static final String testString = "testing";
	public B[] bees;
	public B[][] bees_and_bees;
	// primitive array fields
	public int[] ints;
	public int[][] ints2;
	public int[][][] ints3;
	public char[] chars;
	public float[] floats;
	public boolean[] cools;
	public double[] doubles;
	public long[] longs;
	public short[] shorts;
	public byte[] bytes;
	// primitive field parms
	public void doAllPrimitives(int[] ints,int[][] ints2,int[][][] ints3,char[] chars,float[] floats,boolean[] cools,double[] doubles,long[] longs,short[] shorts,byte[] bytes) {
		return;
	}
	public C doSomething() {
		return null;
	}
	public byte[] getBytes() {
		return new byte[0];
	}
	public char[] getChar() {
		return new char[0];
	}
	public float[] getFloats() {
		return new float[0];
	}
	// primitive field return values
	public int[] getInts() {
		return new int[0];
	}
	public long[] getLongs() {
		return new long[0];
	}
	public short[] getShorts() {
		return new short[0];
	}
	public A[] returnAs(C[] cees) {
		return new A[0];
	}
}
