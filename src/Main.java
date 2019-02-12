import java.io.*;
import java.util.Scanner;

public class Main {
    static double[][] Mat;
    static int M, N;
    public static void main(String[] args){
        mainMenu();
    }
    
    public static void mainMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Interpolasi Polinom");
        System.out.println("3. Exit");
        System.out.print("PILIHAN: ");
        int choice = input.nextInt();
        int metode;
        
        while ((choice <1) || (choice>3)){
            System.out.print("Input salah, silakan coba lagi! PILIHAN: ");
            choice = input.nextInt();
        }
        
        if (choice == 1) {
            //Penyelesaian SPL
            System.out.println("Pilih metode penyelesaian:");
            System.out.println("1. Metode Eliminasi Gauss");
            System.out.println("2. Metode Eliminasi Gauss-Jordan");
            metode = input.nextInt();
            while ((metode <1) || (metode>2)){
                System.out.println("Input salah, silakan coba lagi");
                metode = input.nextInt();
            } 
            if (metode == 1){
                inputMatrix();//SPL dengan metode eliminasi gauss
                double[] temp = Gauss.gaussElimination(Mat);
                for (int i=0; i < temp.length; i++){
                    System.out.print(temp[i] + " ");
                }

            } else if (metode == 2){
                //SPL dengan metode eliminasi gauss-jordan
                inputMatrix();
                Gauss.gaussJordanElimination(Mat);
                
            }

        } else if (choice == 2){
            //Penyelesaian Interpolasi
            System.out.println("Pilih metode penyelesaian:");
            System.out.println("1. Metode Eliminasi Gauss");
            System.out.println("2. Metode Eliminasi Gauss-Jordan");
            metode = input.nextInt();
            while ((metode <1) || (metode>2)){
                System.out.println("Input salah, silakan coba lagi");
                metode = input.nextInt();
            }
            if (metode == 1) {
                int inputsource;
                System.out.println("Input interpolasi melalui:");
                System.out.println("1. Keyboard");
                System.out.println("2. File");
                inputsource = input.nextInt();
                System.out.print("Masukkan jumlah data: ");
                int JmlData = input.nextInt();
                
                System.out.print("Interpolasi pada x: ");
                double interpolateX = input.nextDouble();
                
                Interpolasi Baru = new Interpolasi(JmlData);//Interpolasi dengan metode eliminasi gauss
                if (inputsource == 1){
                Baru.inputKeyboard();
                } else if (inputsource == 2){
                Baru.inputFile();
                }
                Mat = Baru.makeInterpolateMatrix();
                double[] hasil = Gauss.gaussElimination(Mat);
                double sum = 0.0;
                /*for (int i=0; i < hasil.length; i++){
                    System.out.print(hasil[i] + " ");
                }*/
                for (int i = 0; i < hasil.length; i++) {
                    sum += hasil[i] * Math.pow(interpolateX, i);
                }
                System.out.println("f(" + interpolateX + ") = " + sum);
                
            } else if (metode == 2) {
                //Interpolasi dengan metode eliminasi gauss-jordan
            }

        } else {
            System.exit(1);
        }
        input.close();
    }

    private static boolean checkMatrix(double[][] A, double[] b) {
        if (A.length != b.length) { 
            return false;
        }
        for (int i = 1; i < A.length; i++) {
            if (A[i].length != A[0].length) {
                return false;
            }
        }
        return true;
    }

    public static void inputMatrix(){
        Scanner input = new Scanner(System.in);
        System.out.println("Input matriks melalui:");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        int inputSource = input.nextInt();
        while ((inputSource < 1) || (inputSource > 2)){
            System.out.println("Input salah, silakan coba lagi");
            inputSource = input.nextInt();
        } 
        if (inputSource == 1){
            //Input melalui keyboard, masukkan ke matrix.
            inputKeyboard();
            
            // double[][] A = {
            //     { 0, 1,  1, 4 },
            //     { 2, 4, -2, 2 },
            //     { 0, 3, 15, 36 }
            // };

            // double[][] gj = Gauss.gaussJordanElimination(A);

            // for (int i = 0; i < gj.length; i++) {
            //     for (int j = 0; j < gj.length; j++) {
            //         System.out.print(gj[i][j] + " ");
            //     }
            //     System.out.println();
            // }

            // GAUSS
            // Masalah dengan test5, test6, test7, test8
            // double[][] A = {
            //     {  1, -3,   1,  1 },
            //     {  2, -8,   8,  2 },
            //     { -6,  3, -15,  3 }
            // };
            // double[] b = { 4, -2, 9 };
            // if (!checkMatrix(A, b)) {
            //     System.out.println("Bentuk matriks tidak valid");            
            // } else {
            //     double[] hasil = Gauss.gaussElimination(A, b);
            //     if (hasil == null) {
            //         System.out.println("Solusi tidak ada");
            //     } else {
            //         System.out.println("Solusi: ");
            //         for (int i = 0; i < hasil.length; i++) {
            //             System.out.print(String.format("%.2f", hasil[i]) + " ");
            //         }
            //     }
            // }
        } else if (inputSource == 2){
            String fileData = inputFile();
            Mat = parseInput(fileData);
            // Matrix test = new Matrix(4, 5);
            // test.inputKeyboard();
            // test.cetakMatriks();
            // System.out.println();
            // // GaussJordan.gaussJordanElimination(test);
            // test.cetakMatriks();
            // System.out.println();
            //Baca file, masukkan ke matrix.
        } 
        input.close();
    }
    
    public static String inputFile() {
        String filename;
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = null;
        String data = "";
        System.out.println("Masukkan nama file: ");
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
                data = data + temp + "\n";
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
        return data;
    }

    public static double[][] parseInput(String input) {
        String[] parsedInput = input.split("\n");
        // for (int i = 0; i < parsedInput.length; i++) {
        // System.out.println(parsedInput[i]);
        // }

        double[][] parsedInputString = new double[parsedInput.length][parsedInput[0].split(" ").length];
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].split(" ").length; j++) {
                parsedInputString[i][j] = Double.parseDouble(parsedInput[i].split(" ")[j]);
            }
        }

        return parsedInputString;
        // for (int i = 0; i < parsedInputString.length; i++) {
        // for (int j = 0; j < parsedInputString[i].length; j++) {
        // System.out.print(parsedInputString[i][j] + " ");
        // }
        // System.out.println();
        // }
    }

    public static void inputKeyboard() {
        // Prosedur untuk menerima masukan dari Keyboard
        // Variabel untuk pembacaan
        Scanner reader = new Scanner(System.in);
        System.out.println("Masukkan jumlah baris: ");
        M = reader.nextInt();
        System.out.println("Masukkan jumlah kolom: ");
        N = reader.nextInt();
        Mat = new double[M][N];
        // Menerima koefisien matriks kiri sebesar M x N
        System.out.println("MATRIKS KIRI");
        System.out.println("Pemasukan nilai ke dalam Matriks Kiri dengan ordo " + M + " x " +N);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("Posisi(" + i + ", " + j + "): ");
                Mat[i][j] = reader.nextDouble();
            }
        }

        System.out.println();

        // Menutup variabel untuk pembacaan
        reader.close();
    }
}
