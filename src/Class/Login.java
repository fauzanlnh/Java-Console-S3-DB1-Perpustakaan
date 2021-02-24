/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Admin.HomeAdmin;
import User.HomeUser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Fauzanlh
 */
public class Login {

    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println("" + E);
        }
    }

    public String koneksi() {
        String user = "root";
        String pwd = "";
        String host = "localhost";
        String db = "db_ta_pbo_perpustakaan";

        String urlValue = "jdbc:mysql://" + host + "/" + db + "?user=" + user + "&password=" + pwd;
        return urlValue;
    }

    public void cekLogin() {
        clear();
        String UsernameLogin, PasswordLogin;
        HomeAdmin ha = new HomeAdmin();
        HomeUser hu = new HomeUser();
        String Pilih = null;
        Scanner Scan = new Scanner(System.in);
        try {
            System.out.println("USERNAME : ADMIN PASSWORD : ADMIN");
            System.out.println("USERNAME : USER  PASSWORD : USER");
            System.out.println("=======================================");
            System.out.println("|                LOGIN                |");
            System.out.println("=======================================");
            System.out.print("| MASUKKAN USERNAME : ");
            UsernameLogin = Scan.nextLine().toUpperCase();
            while (UsernameLogin.equals("")) {
                System.out.println("USERNAME TIDAK BOLEH KOSONG,MASUKKAN KEMBALI USERNAME");
                String tahan = Scan.nextLine();
                clear();
                System.out.println("=======================================");
                System.out.println("|                LOGIN                |");
                System.out.println("=======================================");
                System.out.print("| MASUKKAN USERNAME : ");
                UsernameLogin = Scan.nextLine().toUpperCase();
            }
            System.out.print("| MASUKKAN PASSWORD : ");
            PasswordLogin = Scan.nextLine().toUpperCase();
            while (PasswordLogin.equals("")) {
                System.out.println("PASSWORD TIDAK BOLEH KOSONG, MASUKKAN KEMBALI PASSWORD");
                String tahan = Scan.nextLine();
                System.out.print("| MASUKKAN PASSWORD : ");
                PasswordLogin = Scan.nextLine().toUpperCase();
            }
            System.out.println("=======================================");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM T_LOGIN WHERE Username = '" + UsernameLogin + "' AND Password = '" + PasswordLogin + "' ;";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("HakAkses").equals("ADMIN")) {
                    System.out.println("LOGIN BERHASIL, TEKAN ENTER");
                    String tahan = Scan.nextLine();
                    ha.MenuUtamaAdmin();
                }
                if (rs.getString("HakAkses").equals("USER")) {
                    System.out.println("LOGIN BERHASIL, TEKAN ENTER");
                    String tahan = Scan.nextLine();
                    hu.MenuUtamaUser();
                }
            }
            if (!rs.next()) {
                do {
                    System.out.println("USERNAME ATAU PASSWORD SALAH!!");
                    System.out.println("APAKAH ANDA AKAN MELAKUKAN LOGIN ULANG [Y/T] : ");
                    Pilih = Scan.nextLine().toUpperCase();
                    switch (Pilih) {
                        case "T":
                            clear();
                            System.exit(0);
                        case "Y":
                            clear();
                            cekLogin();
                        default:
                            System.out.println("PILIHAN HANYA Y ATAU T,ULANG!!");
                            System.out.println("APAKAH ANDA AKAN MELAKUKAN LOGIN ULANG [Y/T] : ");
                            Pilih = Scan.nextLine().toUpperCase();
                    }
                } while (Pilih != "Y" || Pilih != "T");
            }
        } catch (SQLException e) {
            System.out.println("" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }

    }
}
