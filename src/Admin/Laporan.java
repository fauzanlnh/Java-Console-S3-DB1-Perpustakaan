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
public class Laporan {

    public String Koneksi() {
        String User = "root";
        String Pass = "";
        String Host = "localhost";
        String DB = "db_ta_pbo_perpustakaan";
        String urlValue = "jdbc:mysql://" + Host + "/" + DB + "?user=" + User + "&password=" + Pass;
        return urlValue;
    }

    public void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println("" + E);
        }
    }

    //LAPORAN
    public void LaporanPeminjamanBulanan() {
        clear();
        Scanner scan = new Scanner(System.in);
        HomeAdmin ha = new HomeAdmin();
        int Bulan2;
        String Bulan, Tahan;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            PreparedStatement ps = null;
            Statement stmt = con.createStatement();
            System.out.println("============================================");
            System.out.println("| CARI LAPORAN BERDASARKAN BULAN DAN TAHUN |");
            System.out.println("============================================");
            System.out.print("| MASUKKAN BULAN : ");
            Bulan = scan.nextLine().toUpperCase();
            System.out.print("| MASUKKAN TAHUN : ");
            String Tahun = scan.nextLine();
            switch (Bulan) {
                case "JANUARI":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                           |");
                    Bulan = "1";
                    break;
                case "FEBRUARI":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                          |");

                    Bulan = "2";
                    break;
                case "MARET":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                             |");
                    Bulan = "3";
                    break;
                case "APRIL":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                             |");
                    Bulan = "4";
                    break;
                case "MEI":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                               |");
                    Bulan = "5";
                    break;
                case "JUNI":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                              |");
                    Bulan = "6";
                    break;
                case "JULI":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                              |");
                    Bulan = "7";
                    break;
                case "JULY":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                              |");
                    Bulan = "7";
                    break;
                case "AGUSTUS":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                           |");
                    Bulan = "8";
                    break;
                case "SEPTEMBER":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                         |");
                    Bulan = "9";
                    break;
                case "OKTOBER":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                           |");
                    Bulan = "10";
                    break;
                case "NOVEMBER":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                          |");
                    Bulan = "11";
                    break;
                case "DESEMBER":
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                          |");
                    Bulan = "12";
                    break;
                default:
                    System.out.println("ANDA SALAH MEMASUKKAN NAMA BULAN,MASUKKAN NAMA BULAN SECARA LENGKAP(JANUARI - DESEMBER)");
                    System.out.println("MAKA PENCARIAN AKAN DILAKUKAN BERDASARKAN BULAN JANUARI,TEKAN ENTER!!");
                    Tahan = scan.nextLine();
                    clear();
                    Bulan = "JANUARI";
                    System.out.println("+======================================================================================================================================+");
                    System.out.println("|                                           DATA PEMINJAMAN KOLEKSI BULAN " + Bulan + " TAHUN " + Tahun + "                                           |");
                    Bulan = "1";
                    break;
            }
            Bulan2 = Integer.parseInt(Bulan);
            String tbl = "| %-6s | %-47s | %-12s | %-18s | %-20s | %-14s |";
            System.out.println("+========+=================================================+==============+====================+======================+================+");
            System.out.println("|  KODE  |                      JUDUL                      | KODE ANGGOTA | TANGGAL PEMINJAMAN | MAX.TGL PENGEMBALIAN |     STATUS     |");
            System.out.println("+========+=================================================+==============+====================+======================+================+");
            String sql = "SELECT * FROM T_PEMINJAMAN,T_KOLEKSI WHERE MONTH(TGLPINJAM)='" + Bulan2 + "' AND YEAR(TGLPINJAM)='" + Tahun + "' AND T_PEMINJAMAN.KODE = T_KOLEKSI.KODE";
            String sql2 = "SELECT COUNT(KDPEMINJAMAN) AS 'BANYAKPEMINJAM' FROM T_PEMINJAMAN WHERE MONTH(TGLPINJAM)='" + Bulan2 + "' AND YEAR(TGLPINJAM)='" + Tahun + "'";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int KdPeminjaman = rs.getInt("KdPeminjaman");
                String Koleksi = rs.getString("Judul");
                String KdAnggota = rs.getString("KdAnggota");
                String tglPinjam = rs.getString("TglPinjam");
                String tglKembali = rs.getString("EstimasiPengembalian");
                String Status = rs.getString("Status");

                System.out.format(tbl, KdPeminjaman, Koleksi, KdAnggota, tglPinjam, tglKembali, Status);
                System.out.println("");

            }
            ResultSet rs2 = stmt.executeQuery(sql2);
            if (rs2.next()) {
                System.out.println("+========+=================================================+==============+====================+======================+================+");
                String BanyakPeminjaman = rs2.getString("BANYAKPEMINJAM");
                System.out.println("| BANYAK PINJAMAN SEBANYAK : " + BanyakPeminjaman + " PEMINJAMAN                                                                                             |");
            }
            System.out.println("+======================================================================================================================================+");
            System.out.println("|                                             TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                |");
            System.out.println("+======================================================================================================================================+");
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+======================================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void LaporanPeminjamanTahunan() {
        clear();
        Scanner scan = new Scanner(System.in);
        String Tahan;
        HomeAdmin ha = new HomeAdmin();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            PreparedStatement ps = null;
            Statement stmt = con.createStatement();
            System.out.println("======================================");
            System.out.println("| CARI LAPORAN BERDASARKAN DAN TAHUN |");
            System.out.println("======================================");
            System.out.print("| MASUKKAN TAHUN : ");
            String Tahun = scan.nextLine();
            String tbl = "| %-6s | %-47s | %-12s | %-18s | %-20s | %-14s |";
            System.out.println("+======================================================================================================================================+");
            System.out.println("|                                                  DATA PEMINJAMAN KOLEKSI TAHUN " + Tahun + "                                                 |");
            System.out.println("+========+=================================================+==============+====================+======================+================+");
            System.out.println("|  KODE  |                      JUDUL                      | KODE ANGGOTA | TANGGAL PEMINJAMAN | MAX.TGL PENGEMBALIAN |     STATUS     |");
            System.out.println("+========+=================================================+==============+====================+======================+================+");
            String sql = "SELECT * FROM T_PEMINJAMAN,T_KOLEKSI WHERE YEAR(TGLPINJAM)='" + Tahun + "' AND T_PEMINJAMAN.KODE = T_KOLEKSI.KODE";
            String sql2 = "SELECT COUNT(KDPEMINJAMAN) AS 'BANYAKPEMINJAM' FROM T_PEMINJAMAN WHERE YEAR(TGLPINJAM)='" + Tahun + "'";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int KdPeminjaman = rs.getInt("KdPeminjaman");
                String Koleksi = rs.getString("Judul");
                String KdAnggota = rs.getString("KdAnggota");
                String tglPinjam = rs.getString("TglPinjam");
                String tglKembali = rs.getString("EstimasiPengembalian");
                String Status = rs.getString("Status");

                System.out.format(tbl, KdPeminjaman, Koleksi, KdAnggota, tglPinjam, tglKembali, Status);
                System.out.println("");

            }
            ResultSet rs2 = stmt.executeQuery(sql2);
            if (rs2.next()) {
                System.out.println("+========+=================================================+==============+====================+======================+================+");
                String BanyakPeminjaman = rs2.getString("BANYAKPEMINJAM");
                System.out.println("| BANYAK PINJAMAN SEBANYAK : " + BanyakPeminjaman + " PEMINJAMAN                                                                                             |");
            }
            System.out.println("+======================================================================================================================================+");
            System.out.println("|                                             TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                |");
            System.out.println("+======================================================================================================================================+");
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+======================================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void LaporanPeminjamanBelumDikembalikanTahunan() {
        clear();
        Scanner scan = new Scanner(System.in);
        HomeAdmin ha = new HomeAdmin();
        String Tahan;
        int Bulan2;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            PreparedStatement ps = null;
            Statement stmt = con.createStatement();
            System.out.println("============================================");
            System.out.println("| CARI LAPORAN BERDASARKAN  TAHUN |");
            System.out.println("============================================");
            System.out.print("| MASUKKAN TAHUN : ");
            String Tahun = scan.nextLine();

            String tbl = "| %-15s | %-47s | %-12s | %-18s | %-20s | %-13S |";
            System.out.println("+==============================================================================================================================================+");
            System.out.println("|                                           DATA PEMINJAMAN KOLEKSI YANG BELUM DIKEMBALIKAN TAHUN " + Tahun + "                                         |");
            System.out.println("+=================+=================================================+==============+====================+======================+================");
            System.out.println("| KODE PEMINJAMAN |                      JUDUL                      | KODE ANGGOTA | TANGGAL PEMINJAMAN | TANGGAL PENGEMBALIAN | KETERLAMBATAN |");
            System.out.println("+=================+=================================================+==============+====================+======================+===============+");
            String sql = "SELECT T_PEMINJAMAN.KDPEMINJAMAN, T_KOLEKSI.JUDUL, T_PEMINJAMAN.KDANGGOTA,T_PEMINJAMAN.TGLPINJAM,T_PEMINJAMAN.EstimasiPengembalian,DATEDIFF(T_PEMINJAMAN.TglKembali,T_PEMINJAMAN.EstimasiPengembalian)  AS 'DATEDIFF'"
                    + "FROM T_PEMINJAMAN,T_KOLEKSI WHERE YEAR(TGLPINJAM)='" + Tahun + "' AND T_PEMINJAMAN.KODE = T_KOLEKSI.KODE AND T_PEMINJAMAN.STATUS = 'DIPINJAM'";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int KdPeminjaman = rs.getInt("KdPeminjaman");
                String Koleksi = rs.getString("Judul");
                String KdAnggota = rs.getString("KdAnggota");
                String tglPinjam = rs.getString("TglPinjam");
                String tglKembali = rs.getString("EstimasiPengembalian");
                int PerbedaanHari = rs.getInt("DATEDIFF");
                if (PerbedaanHari < 0) {
                    PerbedaanHari = 0;
                }
                System.out.format(tbl, KdPeminjaman, Koleksi, KdAnggota, tglPinjam, tglKembali, PerbedaanHari);
                System.out.println("");

            }
            System.out.println("+=================+=================================================+==============+====================+======================+===============+");
            System.out.println("|                                                 TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                    |");
            System.out.println("+==============================================================================================================================================+");
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+==============================================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void laporanPeminjamanKoleksiTerbanyakTahunan() {
        clear();
        Scanner scan = new Scanner(System.in);
        String Tahan;
        HomeAdmin ha = new HomeAdmin();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            Statement stmt = con.createStatement();
            System.out.println("| CARI LAPORAN BERDASARKAN BULAN DAN TAHUN |");
            System.out.print("MASUKKAN TAHUN : ");
            String Tahun = scan.nextLine();

            System.out.println("+===================================================================+");
            System.out.println("|             PEMINJAMAN KOLEKSI TERBANYAK TAHUN " + Tahun + "               |");
            System.out.println("+================+=====================================+============+");
            System.out.println("|  KODE ANGGOTA  |             NAMA ANGGOTA            |   BANYAK   |");
            System.out.println("+================+=====================================+============+");
            String tbl = "| %-14s | %-35s | %-10s |";
            String sql = "SELECT T_ANGGOTA.NAMA,T_PEMINJAMAN.KDANGGOTA,COUNT(T_PEMINJAMAN.KODE) AS 'BANYAKPEMINJAMAN' FROM T_PEMINJAMAN,T_ANGGOTA,t_koleksI "
                    + "WHERE T_PEMINJAMAN.KODE = T_KOLEKSI.KODE AND T_PEMINJAMAN.KDANGGOTA = T_ANGGOTA.KDANGGOTA  AND YEAR (TGLPINJAM)='" + Tahun + "'GROUP BY T_PEMINJAMAN.KDANGGOTA ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String KdAnggota = rs.getString("KdAnggota");
                String Nama = rs.getString("Nama");
                String Banyak = rs.getString("BANYAKPEMINJAMAN");
                System.out.format(tbl, KdAnggota, Nama, Banyak);
                System.out.println("");
            }
            System.out.println("+================+=====================================+============+");
            System.out.println("|             TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!             |");
            System.out.println("+===================================================================+");
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+===================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void laporanPengembalian() {
        clear();
        Scanner scan = new Scanner(System.in);
        HomeAdmin ha = new HomeAdmin();
        String Tahan;
        int Bulan2;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            Statement stmt = con.createStatement();
            System.out.println("| CARI LAPORAN BERDASARKAN BULAN DAN TAHUN |");
            System.out.print("MASUKKAN BULAN : ");
            String Bulan = scan.nextLine().toUpperCase();

            System.out.print("MASUKKAN TAHUN : ");
            String Tahun = scan.nextLine();
            switch (Bulan) {
                case "JANUARI":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                              |");
                    Bulan = "1";
                    break;
                case "FEBRUARI":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                             |");
                    Bulan = "2";
                    break;
                case "MARET":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                 DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                               |");
                    Bulan = "3";
                    break;
                case "APRIL":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                 DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                               |");
                    Bulan = "4";
                    break;
                case "MEI":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                 DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                                 |");
                    Bulan = "5";
                    break;
                case "JUNI":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                 DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                               |");
                    Bulan = "6";
                    break;
                case "JULI":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                 DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                               |");
                    Bulan = "7";
                    break;
                case "JULY":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                 DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                               |");
                    Bulan = "7";
                    break;
                case "AGUSTUS":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                              |");
                    Bulan = "8";
                    break;
                case "SEPTEMBER":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                            |");
                    Bulan = "9";
                    break;
                case "OKTOBER":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                              |");
                    Bulan = "10";
                    break;
                case "NOVEMBER":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                             |");
                    Bulan = "11";
                    break;
                case "DESEMBER":
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                             |");
                    Bulan = "12";
                    break;
                default:
                    System.out.println("ANDA SALAH MEMASUKKAN NAMA BULAN,MASUKKAN NAMA BULAN SECARA LENGKAP(JANUARI-DESEMBER) ATAU TULIS ANGKA BULAN (1-12)");
                    System.out.println("MAKA PENCARIAN AKAN DILAKUKAN BERDASARKAN BULAN JANUARI,TEKAN ENTER!!");
                    Tahan = scan.nextLine();
                    ha.MenuUtamaAdmin();
                    Bulan = "JANUARI";
                    System.out.println("+=================================================================================================================+");
                    System.out.println("|                                DATA PENGEMBALIAN KOLEKSI BULAN " + Bulan + "  TAHUN " + Tahun + "                              |");
                    Bulan = "1";
            }
            Bulan2 = Integer.parseInt(Bulan);
            String tbl = "| %-6s | %-47s | %-12s | %-20s | %-14s |";
            System.out.println("+========+=================================================+==============+======================+================+");
            System.out.println("|  KODE  |                      JUDUL                      | KODE ANGGOTA | TANGGAL PENGEMBALIAN |     STATUS     |");
            System.out.println("+========+=================================================+==============+======================+================+");
            String sql = "SELECT * FROM T_PEMINJAMAN,T_KOLEKSI WHERE MONTH(TGLPINJAM)='" + Bulan2 + "' AND YEAR(TGLPINJAM)='" + Tahun + "' AND T_PEMINJAMAN.KODE = T_KOLEKSI.KODE AND T_PEMINJAMAN.STATUS='DIKEMBALIKAN'";
            String sql2 = "SELECT COUNT(KDPEMINJAMAN) AS 'DIKEMBALIKAN' FROM T_PEMINJAMAN WHERE STATUS = 'DIKEMBALIKAN' AND MONTH(TGLPINJAM)='" + Bulan2 + "' AND YEAR(TGLPINJAM)='" + Tahun + "'";
            String sql3 = "SELECT COUNT(KDPEMINJAMAN) AS 'DIPINJAM' FROM T_PEMINJAMAN WHERE MONTH(TGLPINJAM)='" + Bulan2 + "' AND YEAR(TGLPINJAM)='" + Tahun + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int KdPeminjaman = rs.getInt("KdPeminjaman");
                String Koleksi = rs.getString("Judul");
                String KdAnggota = rs.getString("KdAnggota");
                String tglKembali = rs.getString("EstimasiPengembalian");
                String Status = rs.getString("Status");

                System.out.format(tbl, KdPeminjaman, Koleksi, KdAnggota, tglKembali, Status);
                System.out.println("");
            }

            ResultSet rs2 = stmt.executeQuery(sql2);
            if (rs2.next()) {
                System.out.println("+========+=================================================+==============+======================+================+");
                String DIKEMBALIKAN = rs2.getString("DIKEMBALIKAN");
                System.out.print("| BANYAK KOLEKSI YANG SUDAH DIKEMBALIKKAN : " + DIKEMBALIKAN + " DARI ");
            }
            ResultSet rs3 = stmt.executeQuery(sql3);
            if (rs3.next()) {
                String DIPINJAM = rs3.getString("DIPINJAM");
                System.out.println("" + DIPINJAM + " PEMINJAMAN                                                  |");
            }
            System.out.println("+=================================================================================================================+");
            System.out.println("|                                    TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                    |");
            System.out.println("+=================================================================================================================+");
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+=================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }
}
