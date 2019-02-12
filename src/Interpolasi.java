// Filename: Interpolasi.java

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Interpolasi {
    /// PRIVATE ATTRIBUTE
    private double[][] Mat;
    private double[] x, fx;
    private int n;              // Ordo
    
    /// PUBLIC METHOD
    /* === Parameterized Constructor === */
    public Interpolasi(int n) {
        Mat = new double[n][n + 1];
        x = new double[n];
        fx = new double[n];
        this.n = n;
    }
    
    /* === Getter and Setter === */
    public double getXAt(int idx) { return x[idx]; }
    public double getfXAt(int idx) { return fx[idx]; }
    public void setXAt(int idx, double val) { x[idx] = val; }
    public void setFxAt(int idx, double val) { fx[idx] = val; }

    /* === Function and Procedure === */    
    public void inputKeyboard() {
    // Prosedur untuk menerima masukan dari Keyboard
        // Variabel untuk pembacaan
        Scanner reader = new Scanner(System.in);
        
        for (int i = 0; i < this.n; i++) {
            System.out.print((i + 1) + ". x = ");
            x[i] = reader.nextDouble();
            System.out.print((i + 1) + ". f(x) = ");
            fx[i] = reader.nextDouble();
        }
        
        reader.close();
    }
    
    public double[][] makeInterpolateMatrix() {
        for (int i = 0; i < this.n; i++) {
            
            Mat[i][0] = 1;      // Khusus untuk pengisian matriks berpangkat nol yang menghasilkan PASTI nilai satu
            
            for (int j = 1; j < this.n; j++) {
                Mat[i][j] = Math.pow(x[i], j);
            }
            
            Mat[i][this.n] = fx[i];  // Matriks augmented terkanan diisi nilai fx ke-i
        }
        
        return Mat;
    }

    public void inputFile(){
        String filename;
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = null;
        String data = "";
        int rows = 0;
        System.out.print("Masukkan nama file: ");
        filename = scanner.nextLine();
        filename = "../test/" + filename;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String temp;
            while ((temp = reader.readLine()) != null) {
                data = data + temp + " ";
                rows = rows+1;
            }
        } catch (IOException e) {
            System.out.println("File error");
            e.printStackTrace();
        }
        try {
            scanner.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] parsedInput = data.split(" ");
        double d = 0.0;
        for (int i = 0; i < rows; i++) {
            if (i%2 == 0) {
            d = Double.parseDouble(parsedInput[i]);
            x[i] = d;
            } else {
            d = Double.parseDouble(parsedInput[i+1]);
            fx[i] = d;
            }
        }
    }
}
