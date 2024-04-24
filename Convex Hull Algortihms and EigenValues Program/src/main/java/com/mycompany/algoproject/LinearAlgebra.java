package com.oop.laproject;

import java.util.*;

//Faaiz Kadiwal 21K-3830 Part
public class LinearAlgebra {
    private static final double e = 0.00001;

    public static void main(String[] args) {
        double[][] matrix = Utility.readMatrix();
        Utility.print(matrix);
        Algorithm algorithm = new Algorithm(e, matrix);
        long[] result = algorithm.getEigenvalue();
        Utility.sort(result);
        System.out.println("The eigenvalues are : " + Arrays.toString(result));
        EigenSpace eigenspace = new EigenSpace(matrix,result);
        double[][] basis = eigenspace.eigenSpace();
        System.out.println(result[0] + "\t" + result[1] + "\t" + result[2]);
        Utility.print(basis);
        
    }
}
//We found that implementing Jacobi Algorithm in a program was easier than method taught in class
//Syed Ammar Asdaque 21K-3923 Part
class Algorithm {

    private double e;
    private double[][] A;
    private double[][] I = {{1,0,0},{0,1,0},{0,0,1}};

    public Algorithm(double e, double[][] A) {
        this.e = e;
        this.A = A;
    }

    public long[] getEigenvalue() {
        double[][] M = A;
        double[][] eigenVectors = Utility.deepCopy(I);
        Element max = getMaxTopRight(M);

        while (Math.abs(max.val) > e) {
            double phi = getPhi(M, max.i, max.j);

            double sin = Math.sin(phi);
            double cos = Math.cos(phi);

            double[][] H = generateH(sin, cos, max.i, max.j);

            M = Utility.multiply(Utility.multiply(Utility.transpose(H), M), H);

            max = getMaxTopRight(M);
            eigenVectors = Utility.multiply(eigenVectors, H);
        }
        long[] X = {Math.round(M[0][0]),Math.round(M[1][1]),Math.round(M[2][2])};
        return X;
    }

    private double[][] generateH(double sin, double cos, int i, int j) {
        double[][] res = Utility.deepCopy(I);

        res[i][i] = cos;
        res[i][j] = -sin;
        res[j][i] = sin;
        res[j][j] = cos;

        return res;
    }

    private double getPhi(double[][] m, int i, int j) {
        double atanArgs = (double) 2 * m[i][j] / (m[i][i] - m[j][j]);
        return Math.atan(atanArgs) / 2;
    }

    private Element getMaxTopRight(double[][] m) {
        Element max = new Element(m[0][1], 0, 1);

        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (Math.abs(max.val) < Math.abs(m[i][j])) {
                    max = new Element(Math.abs(m[i][j]), i, j);
                }
            }
        }
        return max;
    }

    

}
class Utility {

    public static void print(double[][] matrix) {
        for (double[] row : matrix) {
            for (double i : row) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static double[][] transpose(double[][] m) {
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }
    //FAAIZ
    public static double[][] subtract(double[][] a, double[][] b) {
        int rows = a.length;
        int columns = a[0].length;
        double[][] result = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    public static double[][] multiply(double[][] A, double[][] B) {
        int aRows = A.length;
        int aColumns = A[0].length;
        int bColumns = B[0].length;

        double[][] C = new double[aRows][bColumns];

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
    //FAAIZ
    public static void sort(long[] A){
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A.length - i - 1; j++){
                if(A[j] < A[j+1]){
                    A[j] ^= A[j+1];
                    A[j + 1] ^= A[j];
                    A[j] ^= A[j+1];
                }
            }
        }
    }

    public static double[][] readMatrix() {
        double[][] matrix = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter matrix:");
        matrix = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter a" + i + j + " :");
                matrix[i][j] = scanner.nextDouble();
            }
        }
        
        return matrix;
    }

    public static double[][] deepCopy(double[][] original) {
        final double[][] result = new double[original.length][];

        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return result;
    }
}
class Element {

    double val;
    int i;
    int j;

    public Element(double val, int i, int j) {
        this.val = val;
        this.i = i;
        this.j = j;
    }

}
//Faaiz Kadiwal 21K-3830 Part
class EigenSpace{
    double[][] A;
    long[] eigenvalues;
    public EigenSpace(double[][] A, long[] eigenvalues) {
        this.A = A;
        this.eigenvalues = eigenvalues;
    }
    public double[][] eigenSpace(){
        int k = 0;
        double[][] R = new double[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = i + 1; j < 3; j++){
                if(eigenvalues[i] == eigenvalues[j]){
                    i++;
                    break;
                }
            }
            double[][] L = {{eigenvalues[i],0,0},{0,eigenvalues[i],0},{0,0,eigenvalues[i]}};
            double[][] M = Utility.subtract(L, A);
            M = RREF(M);
            if(M[1][1] != 0){
                if(M[2][2] != 0){
                    R[0][k] = 1;
                }
                else{
                    R[2][k] = 1;
                    R[1][k] = -1 * M[1][2];
                    R[0][k] = -1 * M[0][2];
                }
                k++;
            }else{
                R[2][k] = 1;
                R[1][k] = -1 * M[1][2];
                R[0][k] = -1 * M[0][2];
                R[1][k+1] = 1;
                R[0][k+1] = -1 * M[0][1];
                k+=2;
            }
        }
        return R;
    }
    public static double[][] RREF(double[][] matrix){
        double[][] arr = matrix;
        for(int i = 0; i < 3 ;i++){
            if(arr[i][i] == 0 && i < 2){
                icROW(arr,i);
            }
            if(arr[i][i] != 0){
                double x = arr[i][i];
                //System.out.println("1/" + x + " R" + (i+1));
                for(int j = 0; j < 3; j++){
                    arr[i][j] = (double)arr[i][j]/x; 
                }
                rowADD(arr,i);
            }else if(i < 2 && arr[i][i + 1] != 0){
                icROW(arr,i,i+1);
                double x = arr[i+1][i+1];
                //System.out.println("1/" + x + " R" + (i+2));
                for(int j = 0; j < 3; j++){
                    arr[i+1][j] /= x; 
                }
                rowADD(arr,i+1);
                icROW(arr,i,i+1);
            }
        }
        return arr;
    }
    public static void icROW(double[][] arr,int r){
        for(int i = 0; i < 2; i++){
            double temp = arr[2][i];
            arr[2][i] = arr[r][i];
            arr[r][i] = temp;
        }
    }
    public static void icROW(double[][] arr,int r,int l){
        for(int i = 0; i < 2; i++){
            double temp = arr[l][i];
            arr[l][i] = arr[r][i];
            arr[r][i] = temp;
        }
    }
    public static void rowADD(double[][] arr,int r){
        for(int i = 0; i < 3; i++){
            if(i != r){    
                double x = arr[i][r];
                //System.out.println("R " + (i+1) + " + (" + (-1.0 * x) + ") R" + (r+1));
                for(int j = 0; j < 3; j++){
                    arr[i][j] -= (x*arr[r][j]);
                }
            }
        }
    }
}