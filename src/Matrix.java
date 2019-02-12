// Filename: Matriks.java

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class Matrix {
    /// PRIVATE ATTRIBUTE
    private double[][] Mat;
    private int M, N;
    
    /// PUBLIC METHOD
    /* === Parameterized Constructor === */
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        Mat = new double[M][N];
    }
    
    /* === Getter and Setter === */
    public int getM() { return this.M; }
    public int getN() { return this.N; }
    public double getCell(int M, int N) { return this.Mat[M][N]; }
    public double[] getRow(int M) { return this.Mat[M]; }
    public void setRow(int M, double[] newRow) { this.Mat[M] = newRow; }
    public void setCell(int M, int N, double Konst) { this.Mat[M][N] = Konst; }
    
    /* === Operator Overloading === */
    public boolean isPossible(Matrix Mtx) {
        return (this.getN() == Mtx.getM());
    }
    
    public void multiplyAllWith(double C) {
        for (int i = 0; i <= this.M; i++) {
            for (int j = 0; j <= this.N; j++) {
                Mat[i][j] *= C;
            }
        }
    }
    
    public void addAtRow(int Baris, double C) {
        for (int i = 0; i <= this.N; i++) {
            Mat[Baris][i] += C;
        }
    }
    
    public void addAllWith(double C) {
        for (int i = 0; i <= this.M; i++) {
            for (int j = 0; j <= this.N; j++) {
                Mat[i][j] += C;
            }
        }
    }
    
    // Print Matrix (Dafi)
    public void printMatrix() {
        for (int i = 0; i <= this.M; i++) {
            for (int j = 0; j <= this.N; j++) {
            	String input = String.format("%.15f", this.getCell(i,j));
            	System.out.print("Matriks ["+i+"] ["+j+"] : ");
            	System.out.println(input);
            }
        }
    }
    
    // Cetak Matriks (Andre)
    public void cetakMatriks() {
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) {
                System.out.print(getCell(i, j) + " ");
            }
            System.out.println();
        }
    }

    public static String inputFile(){
        String filename;
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = null;
        String data = "";
        System.out.println("Masukkan nama file: ");
        filename = scanner.nextLine();
        try{
            reader = new BufferedReader(new FileReader(filename));
        } catch(Exception e){
            e.printStackTrace();
        }
        try{
            String temp;
            while((temp = reader.readLine()) != null){
            data = data + temp + "\n";
            }
        } catch(IOException e){
            System.out.println("File error");
            e.printStackTrace();
        }
        try{
            scanner.close();
            reader.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }

    
    /*
    public static Matrix parser(String[] inputFile){
        Matrix matrix;
        for (i = 0; i<=inputFile.length;i++){
            Double[] D = new Double[inputFile[0].length];
            for (j = 0; j<=inputFile[0].length; j++){
                D[j] = Double.parseDouble(inputFile[i].split(" "));
            }
        }
        return matrix;
    }
    */
    public void inputKeyboard() {
    // Prosedur untuk menerima masukan dari Keyboard
        // Variabel untuk pembacaan
        Scanner reader = new Scanner(System.in);

        // Menerima koefisien matriks kiri sebesar M x N
        System.out.println("MATRIKS KIRI");
        System.out.println("Pemasukan nilai ke dalam Matriks Kiri dengan ordo " + this.M + " x " + this.N);
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) {
                System.out.print("Posisi(" + i + ", " + j + "): ");
                Mat[i][j] = reader.nextDouble();
            }
        }
        
        System.out.println();
        
        // Menutup variabel untuk pembacaan
        reader.close();
    }
    
    public void copyMatrix(Matrix cMat) {
        if ((cMat.getM() == this.getM()) && (cMat.getN() == this.getN())) {
        // Hanya menyalin apabila matriks berordo sama
            for (int i = 0; i < this.M; i++) {
                for (int j = 0; j < this.M; j++) {
                    this.Mat[i][j] = cMat.getCell(i, j);
                }
            }
        }
    }
}
