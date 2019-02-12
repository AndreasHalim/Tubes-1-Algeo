// Filename: Gauss.java

public class Gauss {
  private static final double EPSILON = 1e-8;

  public static double[] gaussElimination(double[][] a){
    
    /* Permasalahan yang kelebihan SPL */
    if (a.length >= a[0].length) {

      double[][] A = new double[a.length][a[0].length];
      double[] b = new double[a.length];
      
      /* Menyalin matriks A dari matriks a yang dari pengguna */
      for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < a[0].length - 1; j++) {
          A[i][j] = a[i][j];
        }
      }

      for (int i = 0; i < a.length; i++) {
        b[i] = a[i][a.length - 1];
      }

      int numberOfColumns = A[0].length;
      int n = b.length;

      for (int p = 0; p < n; p++) {
        int max = p;
        for (int i = p + 1; i < n; i++) {
          if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
            max = i;
          }
        }

        double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
        double t = b[p]; b[p] = b[max]; b[max] = t;

        for (int i = p + 1; i < n; i++) {
          double alpha = A[i][p] / A[p][p];
          b[i] -= alpha * b[p];
          for (int j = p; j < n; j++) {
            A[i][j] -= alpha * A[p][j];
          } 
        }
      }

      double[] x = new double[A[0].length];

      for (int i = n - 1; i >= 0; i--) {
        double sum = 0.0;
        for (int j = i + 1; j < n; j++) {
          sum += A[i][j] * x[j];
        }

        if (Math.abs(A[i][i]) > EPSILON) {
          x[i] = (b[i] - sum) / A[i][i];
        } else if (Math.abs(A[i][numberOfColumns - 1] - sum) > EPSILON) {
          
          return null;
        }
      }

      for (int i = A[0].length; i < A.length; i++) {
        double sum = 0.0;
        for (int j = 0; j < A[0].length; j++) {
          sum += A[i][j] * x[j];
        }
        if (Math.abs(A[i][A[0].length - 1] - sum) > EPSILON)
          return null;
      }
      
      return x;
    
    } else if (a.length == a[0].length - 1) {
      /* Permasalahan SPL persegi */
      double[][] A = new double[a.length][a[0].length];
      double[] b = new double[a.length];
      
      /* Menyalin matriks A dari matriks a yang dari pengguna */
      for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < a[0].length - 1; j++) {
          A[i][j] = a[i][j];
        }
      }

      for (int i = 0; i < a.length; i++) {
        b[i] = a[i][a.length];
      }

      int numberOfColumns = A[0].length;
      int n = b.length;

      for (int p = 0; p < n; p++) {
        int max = p;
        for (int i = p + 1; i < n; i++) {
          if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
            max = i;
          }
        }

        double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
        double t = b[p]; b[p] = b[max]; b[max] = t;

        for (int i = p + 1; i < n; i++) {
          double alpha = A[i][p] / A[p][p];
          b[i] -= alpha * b[p];
          for (int j = p; j < n; j++) {
            A[i][j] -= alpha * A[p][j];
          } 
        }
      }

      double[] x = new double[A[0].length];

      for (int i = n - 1; i >= 0; i--) {
        double sum = 0.0;
        for (int j = i + 1; j < n; j++) {
          sum += A[i][j] * x[j];
        }

        if (Math.abs(A[i][i]) > EPSILON) {
          x[i] = (b[i] - sum) / A[i][i];
        } else if (Math.abs(A[i][numberOfColumns - 1] - sum) > EPSILON) {
          
          return null;
        }
      }

      for (int i = A[0].length; i < A.length; i++) {
        double sum = 0.0;
        for (int j = 0; j < A[0].length; j++) {
          sum += A[i][j] * x[j];
        }
        if (Math.abs(A[i][A[0].length - 1] - sum) > EPSILON)
          return null;
      }
      
      return x;

    } else {
      /* Permasalahan kekurangan SPL */
      double[][] A = new double[a.length][a[0].length];
      double[] b = new double[a.length];
      
      /* Menyalin matriks A dari matriks a yang dari pengguna */
      for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < a[0].length - 1; j++) {
          A[i][j] = a[i][j];
        }
      }

      for (int i = 0; i < a.length; i++) {
        b[i] = a[i][a.length];
      }

      int numberOfColumns = A[0].length;
      int n = b.length;

      for (int p = 0; p < n; p++) {
        int max = p;
        for (int i = p + 1; i < n; i++) {
          if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
            max = i;
          }
        }

        double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
        double t = b[p]; b[p] = b[max]; b[max] = t;

        for (int i = p + 1; i < n; i++) {
          double alpha = A[i][p] / A[p][p];
          b[i] -= alpha * b[p];
          for (int j = p; j < n; j++) {
            A[i][j] -= alpha * A[p][j];
          } 
        }
      }

      double[] x = new double[A[0].length];

      for (int i = n - 1; i >= 0; i--) {
        double sum = 0.0;
        for (int j = i + 1; j < n; j++) {
          sum += A[i][j] * x[j];
        }

        if (Math.abs(A[i][i]) > EPSILON) {
          x[i] = (b[i] - sum) / A[i][i];
        } else if (Math.abs(A[i][numberOfColumns - 1] - sum) > EPSILON) {
          
          return null;
        }
      }

      for (int i = A[0].length; i < A.length; i++) {
        double sum = 0.0;
        for (int j = 0; j < A[0].length; j++) {
          sum += A[i][j] * x[j];
        }
        if (Math.abs(A[i][A[0].length - 1] - sum) > EPSILON)
          return null;
      }
      
      return x;
    }
  }

  public static double[][] gaussJordanElimination(double[][] augmentedMatrix) {
    double[][] a = augmentedMatrix;
    // double[] b = new double[a.length]; 

    // for (int i = 0; i < a.length; i++) {
    //   b[i] = a[i][a.length];
    // }

    int e = 1;
    int n = a.length;

    for (int j = 0; j < n; j++) {
      int p = j;
      double max = Math.abs(a[p][0]);

      for (int i = j + 1; i < n; i++) {
        if (Math.abs(a[i][j]) > max) {
          p = i;
        }
      }

      if (p > j) {
        for (int i = 0; i < a[1].length; i++) {
          double temp = a[j][i];
          a[j][i] = a[p][i];
          a[p][i] = temp;
        }
      }

      double divisor = a[j][j];
      for (int i = 0; i < a[j].length; i++) {
        a[j][i] = a[j][i] / divisor;
      }

      for (int i = 0; i < a.length; i++) {
        if (i != j) {
          double multiple = a[i][j];
          for (int x = 0; x < a[1].length; x++) {
            a[i][x] = (a[i][x] - a[i][j] * a[j][x]);
          }
        }
      }
    }

    return a;
    // double[][] A = new double[a.length][a[0].length - 1];

    // for (int i = 0; i < a.length; i++) {
    //   for (int j = 0; j < a[0].length - 1; j++) {
    //     A[i][j] = a[i][j];
    //   }
    // }


    // double[] x = new double[A[0].length];

    // for (int i = n - 1; i >= 0; i--) {
    //   double sum = 0.0;
    //   for (int j = i + 1; j < n; j++) {
    //     sum += A[i][j] * x[j];
    //   }

    //   if (Math.abs(A[i][i]) > EPSILON) {
    //     x[i] = (b[i] - sum) / A[i][i];
    //   } else if (Math.abs(A[i][A[0].length - 1] - sum) > EPSILON) {
    //     return null;
    //   }
    // }

    // for (int i = A[0].length; i < A.length; i++) {
    //   double sum = 0.0;
    //   for (int j = 0; j < A[0].length; j++) {
    //     sum += A[i][j] * x[j];
    //   }
    //   if (Math.abs(A[i][a[0].length - 1] - sum) > EPSILON) {
    //     return null;
    //   }
    // }

    // return x;
  }
}
