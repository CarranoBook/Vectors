/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectors;

/**
 *
 * @author nbleier
 */
public class Vectors {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       /* double[] arr = {3., 4.};
        Vector v = new Vector(arr);
        testMagnitude(v);
        double [] arr2 = {-2.3, 32};
        Vector g = new Vector(arr2);
        testEquals(v, g);
        testDecompose(v);*/
        Vector2d test = new Vector2d(4, 3);
        double[] t = test.decomposePolar();
        System.out.println("Magnitude: " + t[0] + "\nDirection: " + t[1] + " radians");
        test = test.toUnitVector();
        t = test.decomposePolar();
        System.out.println("Magnitude: " + t[0] + "\nDirection: " + t[1] + " radians");
        
    }
    
    public static void testMagnitude(Vector v) {
        System.out.println("Vector is: " + v.toString()
                + "\nMagnitude of vector is: " + v.magnitude()
                + "\nVector has " + v.dimensions() + " dimensions.");
    }
    
    public static void testSumDifference(Vector v, Vector g) {
        System.out.println("Vectors are: " + v.toString() + " and "
                        + g.toString()
                        + "\nSum of vectors is " + v.add(g).toString()
                        + "\nDiferences of vectors are " + v.subtract(g).toString()
                        + " and " + g.subtract(v).toString()
                                                                    );                                                     
    }
    
    public static void testEquals(Vector v, Vector g) {
        System.out.println("Vectors are: " + v.toString() + " and "
                        + g.toString()
                        + "\nVectors equal? " + v.equals(g)
                        + "\nSum of vectors equal? " + v.add(g).equals(g.add(v))
                        + " and " + g.add(v).equals(v.add(g))
        );
    }
    
    public static void testDecompose(Vector v) {
        System.out.println("Vector is: " + v);
        Vector[] arr = v.decompose();
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Decomposed vector " + (i+1) + " is: " + arr[i]);
        }
    }
    

    
}
