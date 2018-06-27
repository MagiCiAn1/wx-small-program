import java.util.Scanner;

/**
 * Created by srg
 *
 * @date 2018/5/30
 */
public class al {
    static int n,m, tot, t = 1, a = 1, b = 1;
    static int k[][];
    static int num[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        tot = n * m;
        num = new int[n + 1][m + 1];
        while (t < tot) {
            if ((b + 1) <= m && num[a][b + 1] == 0) {
                num[a][b++] = t++;
            }
            while ((a + 1) <= n && (b - 1) >= 1 && num[a + 1][b - 1] == 0) {
                num[a++][b--] = t++;
            }
            if ((a + 1) <= n && num[a + 1][b] == 0) {
                num[a++][b] = t++;
            }
            while ((a - 1) >= 1 && (b + 1) <= m && num[a - 1][b + 1] == 0) {
                num[a--][b++] = t++;
            }
        }
        num[a][b] = t;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.printf("%5d", num[i][j]);
            }
            System.out.println();
        }
    }
}
