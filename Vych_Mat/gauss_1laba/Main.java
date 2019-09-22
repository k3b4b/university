import java.util.Scanner;
import java.util.Arrays;

public class Main
{
    public void solve(double[][] A, double[] B, double[][]VecNev, double[][]Acopy)
    {
        int N = B.length;
        for (int k = 0; k < N; k++)
        {
            /** поиск опорного элемента **/
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
                    max = i;

            /** swap row in A matrix **/
            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;

            /** swap corresponding values in constants matrix **/
            double t = B[k];
            B[k] = B[max];
            B[max] = t;

            /** pivot within A and B **/
            for (int i = k + 1; i < N; i++)
            {
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                for (int j = k; j < N; j++)
                    A[i][j] -= factor * A[k][j];
            }
        }

        /** Выводим матрицу в ступенчатом виде **/
        printRowEchelonForm(A, B);

        /** Обратный ход **/
        double[] solution = new double[N];
        for (int i = N - 1; i >= 0; i--)
        {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }
        /** Выводим решение **/
        printSolution(solution);

        /** Вектор невязки **/
       /* VectorNevyazki(A, B, solution, VecNev, Acopy); */
    }
    /** Функция для вывода матрицы в ступенчатом виде **/
    public void printRowEchelonForm(double[][] A, double[] B)
    {
        int N = B.length;
        System.out.println("\nСтупенчатый вид : ");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.printf("%.3f ", A[i][j]);
            System.out.printf("| %.3f\n", B[i]);
        }
        System.out.println();
    }

    /**вектор невязки
    public void VectorNevyazki(double[][] A, double[] B, double[] sol, double[][]VecNev, double[][]Acopy)
    {
        int N = B.length;
        float temp=0;
        for (int i=0; i< N; i++)
        {
            for(int j=0; j<N; j++){
                temp+=(sol[j]*Acopy[i][j]);
                VecNev[i][0]=temp;
            }
            temp=0;
        }
       for(int i=0; i<N;i++){
           System.out.println(VecNev[i][0]);
       }
        for(int i=0; i<N;i++){
            System.out.println(VecNev[i][1]);
        }
    } **/




    /** функция для печати решения **/
    public void printSolution(double[] sol)
    {
        int N = sol.length;
        System.out.println("\nРешение : ");
        for (int i = 0; i < N; i++)
            System.out.printf("%.3f ", sol[i]);
        System.out.println();
    }
    /** точка входа **/
    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        Main ge = new Main();

        System.out.println("\nВведите кол-во строк");
        int N = scan.nextInt();

        double[] B = new double[N];
        double[][] A = new double[N][N];
        double[][] VecNev = new double[N][2];
        double[][] Acopy= new double[N][N];

        System.out.println("\nВведите "+ N +" КОЭФФИЦИЕНТОВ(матрица коэффициентов) ");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = scan.nextDouble();
        System.arraycopy(A, 0, Acopy, 0, A.length);
        System.out.println("\nВведите "+ N +" свободные члены");
        for (int i = 0; i < N; i++) {
            B[i] = scan.nextDouble();
            VecNev[i][1]=B[i];
        }

    /*    for (int j = 0; j < Acopy.length; j++) {
            for (int k = 0; k < Acopy[0].length; k++) {
                System.out.print(Acopy[j][k] + " ");
            }
            System.out.print("\n");
        } */

        ge.solve(A,B, VecNev, Acopy);
    }
}