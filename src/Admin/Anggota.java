/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Fauzanlh
 */
public class Anggota {

    //BERES
    public String koneksi() {
        String user = "root";
        String pwd = "";
        String host = "localhost";
        String db = "db_ta_pbo_perpustakaan";

        String urlValue = "jdbc:mysql://" + host + "/" + db + "?user=" + user + "&password=" + pwd;
        return urlValue;
    }

    public void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println("" + E);
        }
    }

    public void tampilAnggota() {
        clear();
        HomeAdmin ha = new HomeAdmin();
        Scanner scan = new Scanner(System.in);
        String Tahan;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM T_ANGGOTA";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("+=======================================================================================================================+");
            System.out.println("|                                               DATA ANGGOTA PERPUSTAKAAN                                               |");
            String tbl = "| %-14s | %-32s | %-43s | %-19s |";
            System.out.println("+================+==================================+=============================================+=====================+");
            System.out.println("|  KODE ANGGOTA  |           NAMA ANGGOTA           |                   E-MAIL                    |        STATUS       |");
            System.out.println("+================+==================================+=============================================+=====================+");
            while (rs.next()) {
                int Kode = rs.getInt("KDANGGOTA");
                String Nama = rs.getString("NAMA");
                String Email = rs.getString("EMAIL");
                String Status = rs.getString("STATUS");
                System.out.format(tbl, Kode, Nama, Email, Status);
                System.out.println("");
            }
            System.out.println("+================+==================================+=============================================+=====================+");
            System.out.println("|                                     TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                         |");
            System.out.println("+=======================================================================================================================+");
            Tahan = scan.nextLine();
            ha.MenuUtamaAnggota();
        } catch (SQLException e) {
            System.out.println("ERROR : " + e);
            System.out.println("+=======================================================================================================================+");
            Tahan = scan.nextLine();
            ha.MenuUtamaAnggota();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void tambahAnggota() {
        clear();
        HomeAdmin ha = new HomeAdmin();
        Scanner key = new Scanner(System.in);
        String Tahan;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(koneksi());
            System.out.println("=====================================================================================");
            System.out.println("|                                  TAMBAH DATA ANGGOTA                              |");
            System.out.println("=====================================================================================");
            System.out.print("| MASUKKAN KODE ANGGOTA                : ");
            String k1 = key.nextLine().toUpperCase();
            System.out.print("| MASUKKAN NAMA ANGGOTA                : ");
            String k2 = key.nextLine().toUpperCase();
            System.out.print("| MASUKKAN E-MAIL                      : ");
            String k4 = key.nextLine().toUpperCase();
            System.out.print("| MASUKKAN [DOSEN/D3/S1/PASCA SARJANA] : ");
            String k5 = key.nextLine().toUpperCase();
            String Status = null;
            if (k5.equals("DOSEN") || k5.equals("PASCA SARJANA")) {
                Status = "KHUSUS";
            } else if (k5.equals("D3") || k5.equals("S1")) {
                Status = "BIASA";
            } else {
                k5 = null;
                Status = "BIASA";
            }
            PreparedStatement pStatement = null;
            String sql = "INSERT INTO `t_ANGGOTA`(KDANGGOTA,`NAMA`,EMAIL,`STATUS`,TINGKAT)" + "VALUES('" + k1 + "','" + k2 + "','" + k4 + "','" + Status + "','" + k5 + "');";
            pStatement = conn.prepareStatement(sql);
            int intBaris = pStatement.executeUpdate();
            if (intBaris > 0) {
                System.out.println("=====================================================================================");
                System.out.println("|                       PENAMBAHAN DATA BERHASIL DILAKUKAN                          |");
                System.out.println("|                   TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                       |");
                System.out.println("=====================================================================================");
            } else {
                System.out.println("=====================================================================================");
                System.out.println("|              PENAMBAHAN DATA GAGAL, CEK KEMBALI DATA YANG DIMASUKKAN!             |");
                System.out.println("|                     TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                     |");
                System.out.println("=====================================================================================");
            }
            Tahan = key.nextLine();
            ha.MenuUtamaAdmin();

            pStatement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("=====================================================================================");
            System.out.println("ERROR : " + e);
            Tahan = key.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void ubahStatus() {
        clear();
        HomeAdmin ha = new HomeAdmin();
        Scanner scan = new Scanner(System.in);
        String Tahan;
        int KodeAnggota = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();
            System.out.println("==================================================");
            System.out.println("|                   CARI ANGGOTA                 |");
            System.out.println("==================================================");
            System.out.print("| MASUKKAN KODE ANGGOTA : ");
            String CariAnggota = scan.nextLine().toUpperCase();
            clear();
            String sql1 = "SELECT * FROM T_ANGGOTA WHERE KDANGGOTA LIKE '%" + CariAnggota + "%'";
            ResultSet rs = stmt.executeQuery(sql1);
            System.out.println("+=======================================================================================================================+");
            System.out.println("|                                               DATA ANGGOTA PERPUSTAKAAN                                               |");
            String tbl = "| %-14s | %-32s | %-43s | %-19s |";
            System.out.println("+================+==================================+=============================================+=====================+");
            System.out.println("|  KODE ANGGOTA  |           NAMA ANGGOTA           |                   E-MAIL                    |        STATUS       |");
            System.out.println("+================+==================================+=============================================+=====================+");

            while (rs.next()) {
                KodeAnggota = rs.getInt("KDANGGOTA");
                String Nama = rs.getString("NAMA");
                String Email = rs.getString("EMAIL");
                String Status = rs.getString("STATUS");
                System.out.format(tbl, KodeAnggota, Nama, Email, Status);
                System.out.println("");
            }
            System.out.println("+================+==================================+=============================================+=====================+");
            String Pilih;
            do {
                System.out.print(" APAKAH ANDA AKAN MELAKUKAN UBAH STATUS KEANGGOTAAN [Y / T] : ");
                Pilih = scan.nextLine().toUpperCase();
                System.out.println("+=============================================================================================================+");
                switch (Pilih) {
                    case "Y":
                        clear();
                        System.out.println("=============================================================================");
                        System.out.println("|                          UBAH STATUS KEANGGOTAAN                          |");
                        System.out.println("=============================================================================");
                        PreparedStatement pStatement = null;
                        System.out.print("| MASUKKAN KODE ANGGOTA : ");
                        String KdAnggota = scan.nextLine().toUpperCase();
                        System.out.print("| MASUKKAN STATUS       : ");
                        String Status = scan.nextLine().toUpperCase();
                        String sql = "UPDATE t_anggota set STATUS = '" + Status + "'where KDANGGOTA='" + KdAnggota + "' ";
                        pStatement = con.prepareStatement(sql);
                        int intBaris = pStatement.executeUpdate();
                        if (intBaris > 0) {
                            System.out.println("=============================================================================");
                            System.out.println("|                   PENGUBAHAN DATA BERHASIL DILAKUKAN                      |");
                            System.out.println("|               TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                   |");
                            System.out.println("=============================================================================");
                        } else {
                            System.out.println("=============================================================================");
                            System.out.println("|          PENGUBHAN DATA GAGAL, CEK KEMBALI DATA YANG DIMASUKKAN!          |");
                            System.out.println("|                 TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                 |");
                            System.out.println("=============================================================================");
                        }
                        Tahan = scan.nextLine();
                        ha.MenuUtamaAdmin();
                        pStatement.close();
                        con.close();
                        break;
                    case "T":
                        ha.MenuUtamaAdmin();
                        break;
                    default:
                        System.out.println("MASUKKAN SALAH,ULANG");
                }
            } while (Pilih != "Y" || Pilih != "T");

        } catch (SQLException e) {
            System.out.println("=============================================================================");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }
    //BELUM BERES

    public void cariAnggota() {
        clear();
        Scanner scan = new Scanner(System.in);
        String Pilih, Pilih2;
        String Tahan;
        HomeAdmin ha = new HomeAdmin();
        int KodeAnggota = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();
            System.out.println("==================================================");
            System.out.println("|                   CARI ANGGOTA                 |");
            System.out.println("==================================================");
            System.out.print("| MASUKKAN KODE ANGGOTA : ");
            String CariAnggota = scan.nextLine().toUpperCase();
            clear();
            String sql1 = "SELECT * FROM T_ANGGOTA WHERE KDANGGOTA LIKE '%" + CariAnggota + "%'";
            ResultSet rs = stmt.executeQuery(sql1);
            System.out.println("+=======================================================================================================================+");
            System.out.println("|                                               DATA ANGGOTA PERPUSTAKAAN                                               |");
            String tbl = "| %-14s | %-32s | %-43s | %-19s |";
            System.out.println("+================+==================================+=============================================+=====================+");
            System.out.println("|  KODE ANGGOTA  |           NAMA ANGGOTA           |                   E-MAIL                    |        STATUS       |");
            System.out.println("+================+==================================+=============================================+=====================+");
            int no = 0;
            while (rs.next()) {
                KodeAnggota = rs.getInt("KDANGGOTA");
                String Nama = rs.getString("NAMA");
                String Email = rs.getString("EMAIL");
                String Status = rs.getString("STATUS");
                System.out.format(tbl, KodeAnggota, Nama, Email, Status);
                System.out.println("");
                no = no + 1;
            }
            if (no == 0) {
                System.out.println("+===========================================================================================================================================================================================================+");
                System.out.println("|                                                                                      DATA YANG DICARI TIDAK DITEMUKAN                                                                                     |");
                System.out.println("|                                                                                TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                  |");
                System.out.println("+===========================================================================================================================================================================================================+");
                Scanner tahan = new Scanner(System.in);
                Tahan = scan.nextLine();
                ha.MenuUtamaAdmin();
            }
            System.out.println("+================+==================================+=============================================+=====================+");
            System.out.print("| APAKAH ANDA MENEMUKAN DATA YANG ANDA CARI [Y/T] : ");
            do {
                Pilih = scan.nextLine().toUpperCase();
                System.out.println("+=======================================================================================================================+");
                switch (Pilih) {
                    case "Y":
                        System.out.print(" APAKAH ANDA AKAN MELAKUKAN UBAH KETERANGAN BEBAS PINJAM [Y / T] : ");
                        do {
                            Pilih = scan.nextLine().toUpperCase();
                            switch (Pilih) {
                                case "Y":
                                    ketBebasPinjam();
                                    break;
                                case "T":
                                    System.out.println("+=======================================================================================================================+");
                                    System.out.println("|                                   TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                           |");
                                    System.out.println("+=======================================================================================================================+");
                                    Tahan = scan.nextLine();
                                    ha.MenuUtamaAdmin();
                                    break;
                                default:
                                    System.out.println("MASUKKAN SALAH, ULANG ");
                                    System.out.print(" APAKAH ANDA AKAN MELAKUKAN UBAH KETERANGAN BEBAS PINJAM [Y / T] : ");
                                    break;
                            }
                        } while (Pilih != "Y" || Pilih != "T");
                        break;
                    case "T":
                        System.out.println("+=======================================================================================================================+");
                        System.out.print("| APAKAH ANDA AKAN MENCARI KODE ANGGOTA LAGI [Y/T] : ");
                        do {
                            Pilih2 = scan.nextLine().toUpperCase();
                            System.out.println("+=======================================================================================================================+");
                            switch (Pilih2) {
                                case "Y":
                                    cariAnggota();
                                case "T":
                                    ha.MenuUtamaAdmin();
                                default:
                                    System.out.println("| MASUKKAN HANYA Y ATAU T,ULANG");
                                    System.out.print("| APAKAH ANDA MENEMUKAN DATA YANG ANDA CARI [Y/T] : ");
                            }
                        } while (Pilih2 != "Y" || Pilih2 != "T");
                        System.out.println("+=======================================================================================================================+");

                    default:
                        System.out.println("| MASUKKAN HANYA Y ATAU T,ULANG");
                        System.out.println("| APAKAH ANDA MENEMUKAN DATA YANG ANDA CARI [Y/T] : ");
                }
            } while (Pilih != "Y" || Pilih != "T");
        } catch (SQLException e) {
            System.out.println("+=======================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void ketBebasPinjam() {
        clear();
        HomeAdmin ha = new HomeAdmin();
        Scanner scan = new Scanner(System.in);
        int BanyakPinjaman = 0;
        String Tahan;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();
            PreparedStatement pStatement = null;

            System.out.println("=============================================================================");
            System.out.println("|                          UBAH STATUS BEBAS PINJAM                         |");
            System.out.println("=============================================================================");
            System.out.print("| MASUKKAN KODE ANGGOTA : ");
            String KodeAnggota = scan.nextLine().toUpperCase();
            System.out.println("=============================================================================");

            String sql = "SELECT COUNT(KDPEMINJAMAN) AS BANYAKPINJAMAN FROM t_peminjaman,t_anggota WHERE t_peminjaman.status='DIPINJAM' AND t_peminjaman.`KdAnggota` = t_anggota.`KdAnggota` AND t_peminjaman.KDANGGOTA = '" + KodeAnggota + "' ";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                BanyakPinjaman = rs.getInt("BanyakPinjaman");
            }
            if (BanyakPinjaman == 0) {
                String sql2 = "UPDATE t_anggota set STATUS = 'BEBAS PINJAM'where KDANGGOTA='" + KodeAnggota + "' ";
                pStatement = con.prepareStatement(sql2);
            } else {
                System.out.println("|                      MASIH BELUM MENGEMBALIKAN BUKU                       |");
                System.out.println("|               TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                   |");
                System.out.println("=============================================================================");
                Tahan = scan.nextLine();
                ha.MenuUtamaAdmin();
            }
            int intBaris = pStatement.executeUpdate();
            if (intBaris > 0) {
                System.out.println("|                   PENGUBAHAN DATA BERHASIL DILAKUKAN                      |");
                System.out.println("|               TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                   |");
                System.out.println("=============================================================================");
            } else {
                System.out.println("|          PENGUBHAN DATA GAGAL, CEK KEMBALI DATA YANG DIMASUKKAN!          |");
                System.out.println("|                 TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                 |");
                System.out.println("=============================================================================");
            }
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
            pStatement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("=============================================================================");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }

    public void BebasPinjamPilih() {
        clear();
        Scanner scan = new Scanner(System.in);
        String Pilih;
        do {
            System.out.print("| APAKAH ANDA AKAN MENCARI KODE ANGGOT TERLEBIH DAHULU [Y / T] : ");
            Pilih = scan.nextLine().toUpperCase();
            switch (Pilih) {
                case "Y":
                    cariAnggota();
                    break;
                case "T":
                    ketBebasPinjam();
                    break;
                default:
                    System.out.println("PILIHAN HANYA Y ATAU T,ULANG!!");
                    break;
            }
        } while (Pilih != "Y" || Pilih != "T");
    }
}
