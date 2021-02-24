/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fauzanlh
 */
public class KoleksiUser {

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

    public void tampilKoleksi() {
        //DONE TINGGAL DESIGN
        clear();
        HomeUser ha = new HomeUser();
        Scanner scan = new Scanner(System.in);
        String Tahan;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();
            String query = "SELECT T_KOLEKSI.Kode,T_KOLEKSI.Judul, T_JENIS.Jenis, T_KOLEKSI.NamaPengarang, T_KOLEKSI.NamaPenerbit, T_KOLEKSI.TahunTerbit, T_TIPE.NamaTipe, T_KOLEKSI.NoRak, T_KOLEKSI.Status, IF(T_KOLEKSI.EstimasiPengembalian IS NULL,' ',T_KOLEKSI.EstimasiPengembalian) AS TanggalKembali "
                    + "FROM T_KOLEKSI,T_TIPE,T_JENIS WHERE T_KOLEKSI.KdTipe = T_TIPE.KdTipe AND T_KOLEKSI.KdJenis = T_JENIS.KdJenis";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("+===========================================================================================================================================================================================================+");
            System.out.println("|                                                                                     DATA KOLEKSI PERPUSTAKAAN                                                                                             |");
            String tbl = "| %-6s | %-47s | %-15s | %-33s | %-24s | %-5s | %-10s | %-8s | %-10s | %-16s |%n";
            System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
            System.out.println("|  KODE  |                      JUDUL                      |      JENIS      |             PENGARANG             |         PENERBIT         | TAHUN |    TIPE    |  NO RAK  |   STATUS   | TGL PENGEMBALIAN |");
            System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
            while (rs.next()) {
                int Kode = rs.getInt("Kode");
                String Judul = rs.getString("Judul");
                String TahunTerbit = rs.getString("TahunTerbit");
                String Jenis = rs.getString("Jenis");
                String Pengarang = rs.getString("NamaPengarang");
                String Penerbit = rs.getString("NamaPenerbit");
                String Tipe = rs.getString("NamaTipe");
                String NoRak = rs.getString("NoRak");
                String Status = rs.getString("Status");
                String Peminjaman = rs.getString("TanggalKembali");
                System.out.format(tbl, Kode, Judul, Jenis, Pengarang, Penerbit, TahunTerbit, Tipe, NoRak, Status, Peminjaman);
            }
            System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
            System.out.println("|                                                                                TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                  |");
            System.out.println("+===========================================================================================================================================================================================================+");
            Scanner tahan = new Scanner(System.in);
            if (tahan.hasNextLine()) {
                ha.MenuUtamaUser();
            }
        } catch (SQLException e) {
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaUser();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void cariKoleksiJudul() {
        clear();
        Scanner scan = new Scanner(System.in);
        HomeUser ha = new HomeUser();
        int no;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();

            System.out.print("MASUKKAN NAMA KOLEKSI : ");
            String CariJudul = scan.nextLine().toUpperCase();
            String sql1 = "SELECT T_KOLEKSI.Kode,T_KOLEKSI.Judul, T_JENIS.Jenis, T_KOLEKSI.NamaPengarang, T_KOLEKSI.NamaPenerbit, T_KOLEKSI.TahunTerbit, T_TIPE.NamaTipe, T_KOLEKSI.NoRak, T_KOLEKSI.Status, IF(T_KOLEKSI.EstimasiPengembalian IS NULL,' ',T_KOLEKSI.EstimasiPengembalian) AS TanggalKembali "
                    + "FROM T_KOLEKSI,T_JENIS,T_TIPE WHERE JUDUL LIKE'%" + CariJudul + "%' AND T_KOLEKSI.KdTipe = T_TIPE.KdTipe AND T_KOLEKSI.KdJenis = T_JENIS.KdJenis";
            ResultSet rs = stmt.executeQuery(sql1);
            String tbl = "| %-6s | %-47s | %-15s | %-33s | %-24s | %-5s | %-10s | %-8s | %-10s | %-16s |%n";
            System.out.println("+===========================================================================================================================================================================================================+");
            System.out.println("|                                                                                     DATA KOLEKSI PERPUSTAKAAN                                                                                             |");
            System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
            System.out.println("|  KODE  |                      JUDUL                      |      JENIS      |             PENGARANG             |         PENERBIT         | TAHUN |    TIPE    |  NO RAK  |   STATUS   | TGL PENGEMBALIAN |");
            System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
            no = 0;
            while (rs.next()) {
                int Kode = rs.getInt("Kode");
                String Judul = rs.getString("Judul");
                String TahunTerbit = rs.getString("TahunTerbit");
                String Jenis = rs.getString("Jenis");
                String Pengarang = rs.getString("NamaPengarang");
                String Penerbit = rs.getString("NamaPenerbit");
                String Tipe = rs.getString("NamaTipe");
                String NoRak = rs.getString("NoRak");
                String Status = rs.getString("Status");
                String Peminjaman = rs.getString("TanggalKembali");

                no = no + 1;
                System.out.format(tbl, Kode, Judul, Jenis, Pengarang, Penerbit, TahunTerbit, Tipe, NoRak, Status, Peminjaman);
            }
            if (no == 0) {
                System.out.println("+===========================================================================================================================================================================================================+");
                System.out.println("|                                                                                      DATA YANG DICARI TIDAK DITEMUKAN                                                                                     |");
                System.out.println("|                                                                                TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                  |");
                System.out.println("+===========================================================================================================================================================================================================+");
            } else {
                System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
                System.out.println("|                                                                                TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                  |");
                System.out.println("+===========================================================================================================================================================================================================+");
            }
            Scanner tahan = new Scanner(System.in);
            if (tahan.hasNextLine()) {
                ha.MenuUtamaUser();
            }
        } catch (SQLException e) {
            System.out.println("" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }

    public void cariKoleksiBdsPengarang() {
        clear();
        Scanner scan = new Scanner(System.in);
        HomeUser ha = new HomeUser();
        String Tahan;
        int no;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();
            System.out.print("MASUKKAN NAMA PENGARANG : ");
            String NamaPengarang = scan.nextLine().toUpperCase();
            String sql1 = "SELECT T_KOLEKSI.Kode,T_KOLEKSI.Judul, T_JENIS.Jenis, T_KOLEKSI.NamaPengarang, T_KOLEKSI.NamaPenerbit, T_KOLEKSI.TahunTerbit, T_TIPE.NamaTipe, T_KOLEKSI.NoRak, T_KOLEKSI.Status, IF(T_KOLEKSI.EstimasiPengembalian IS NULL,' ',T_KOLEKSI.EstimasiPengembalian) AS TanggalKembali "
                    + "FROM T_KOLEKSI,T_JENIS,T_TIPE WHERE NAMAPENGARANG LIKE'%" + NamaPengarang + "%' AND T_KOLEKSI.KdTipe = T_TIPE.KdTipe AND T_KOLEKSI.KdJenis = T_JENIS.KdJenis";
            ResultSet rs = stmt.executeQuery(sql1);
            String tbl = "| %-6s | %-47s | %-15s | %-33s | %-24s | %-5s | %-10s | %-8s | %-10s | %-16s |%n";
            System.out.println("+===========================================================================================================================================================================================================+");
            System.out.println("|                                                                                     DATA KOLEKSI PERPUSTAKAAN                                                                                             |");
            System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
            System.out.println("|  KODE  |                      JUDUL                      |      JENIS      |             PENGARANG             |         PENERBIT         | TAHUN |    TIPE    |  NO RAK  |   STATUS   | TGL PENGEMBALIAN |");
            System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
            no = 0;
            while (rs.next()) {
                int Kode = rs.getInt("Kode");
                String Judul = rs.getString("Judul");
                String TahunTerbit = rs.getString("TahunTerbit");
                String Jenis = rs.getString("Jenis");
                String Pengarang = rs.getString("NamaPengarang");
                String Penerbit = rs.getString("NamaPenerbit");
                String Tipe = rs.getString("NamaTipe");
                String NoRak = rs.getString("NoRak");
                String Status = rs.getString("Status");
                String Peminjaman = rs.getString("TanggalKembali");

                no = no + 1;
                System.out.format(tbl, Kode, Judul, Jenis, Pengarang, Penerbit, TahunTerbit, Tipe, NoRak, Status, Peminjaman);
            }
            if (no == 0) {
                System.out.println("+===========================================================================================================================================================================================================+");
                System.out.println("|                                                                                      DATA YANG DICARI TIDAK DITEMUKAN                                                                                     |");
                System.out.println("|                                                                                TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                  |");
                System.out.println("+===========================================================================================================================================================================================================+");
            } else {
                System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
                System.out.println("|                                                                                TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                  |");
                System.out.println("+===========================================================================================================================================================================================================+");
            }
            Tahan = scan.nextLine();
            ha.MenuUtamaUser();
        } catch (SQLException e) {
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaUser();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }

    public void cariKoleksiBdsPenerbit() {
        clear();
        Scanner scan = new Scanner(System.in);
        HomeUser ha = new HomeUser();
        String Tahan;
        int no;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();

            System.out.print("MASUKKAN NAMA PENERBIT : ");
            String NamaPengarang = scan.nextLine().toUpperCase();
            String sql1 = "SELECT T_KOLEKSI.Kode,T_KOLEKSI.Judul, T_JENIS.Jenis, T_KOLEKSI.NamaPengarang, T_KOLEKSI.NamaPenerbit, T_KOLEKSI.TahunTerbit, T_TIPE.NamaTipe, T_KOLEKSI.NoRak, T_KOLEKSI.Status, IF(T_KOLEKSI.EstimasiPengembalian IS NULL,' ',T_KOLEKSI.EstimasiPengembalian) AS TanggalKembali "
                    + "FROM T_KOLEKSI,T_JENIS,T_TIPE WHERE NAMAPENERBIT LIKE'%" + NamaPengarang + "%' AND T_KOLEKSI.KdTipe = T_TIPE.KdTipe AND T_KOLEKSI.KdJenis = T_JENIS.KdJenis";
            ResultSet rs = stmt.executeQuery(sql1);
            String tbl = "| %-6s | %-47s | %-15s | %-33s | %-24s | %-5s | %-10s | %-8s | %-10s | %-16s |%n";
            System.out.println("+===========================================================================================================================================================================================================+");
            System.out.println("|                                                                                     DATA KOLEKSI PERPUSTAKAAN                                                                                             |");
            System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
            System.out.println("|  KODE  |                      JUDUL                      |      JENIS      |             PENGARANG             |         PENERBIT         | TAHUN |    TIPE    |  NO RAK  |   STATUS   | TGL PENGEMBALIAN |");
            System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
            no = 0;
            while (rs.next()) {
                int Kode = rs.getInt("Kode");
                String Judul = rs.getString("Judul");
                String TahunTerbit = rs.getString("TahunTerbit");
                String Jenis = rs.getString("Jenis");
                String Pengarang = rs.getString("NamaPengarang");
                String Penerbit = rs.getString("NamaPenerbit");
                String Tipe = rs.getString("NamaTipe");
                String NoRak = rs.getString("NoRak");
                String Status = rs.getString("Status");
                String Peminjaman = rs.getString("TanggalKembali");
                no = no + 1;
                System.out.format(tbl, Kode, Judul, Jenis, Pengarang, Penerbit, TahunTerbit, Tipe, NoRak, Status, Peminjaman);
            }
            if (no == 0) {
                System.out.println("+===========================================================================================================================================================================================================+");
                System.out.println("|                                                                                      DATA YANG DICARI TIDAK DITEMUKAN                                                                                     |");
                System.out.println("|                                                                                TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                  |");
                System.out.println("+===========================================================================================================================================================================================================+");
            } else {
                System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
                System.out.println("|                                                                                TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                  |");
                System.out.println("+===========================================================================================================================================================================================================+");
            }
            Tahan = scan.nextLine();
            ha.MenuUtamaUser();
        } catch (SQLException e) {
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaUser();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void cariKoleksiBdsKategori() {
        clear();
        Scanner scan = new Scanner(System.in);
        HomeUser ha = new HomeUser();
        String Tahan;
        int no;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();

            System.out.print("MASUKKAN NAMA KATEGORi : ");
            String NamaKategori = scan.nextLine().toUpperCase();
            String sql1 = "SELECT T_KOLEKSI.Kode,T_KOLEKSI.Judul, T_JENIS.Jenis, T_KOLEKSI.NamaPengarang, T_KOLEKSI.NamaPenerbit, T_KOLEKSI.TahunTerbit, T_TIPE.NamaTipe, T_KOLEKSI.NoRak, T_KOLEKSI.Status, IF(T_KOLEKSI.EstimasiPengembalian IS NULL,' ',T_KOLEKSI.EstimasiPengembalian) AS TanggalKembali "
                    + " FROM T_KOLEKSI,T_JENIS,T_TIPE,T_KATEGORI WHERE KATEGORI LIKE'%" + NamaKategori + "%' AND T_KOLEKSI.KdTipe = T_TIPE.KdTipe AND T_KOLEKSI.KdJenis = T_JENIS.KdJenis AND T_KATEGORI.KDKATEGORI = T_KOLEKSI.KDKATEGORI";
            ResultSet rs = stmt.executeQuery(sql1);
            String tbl = "| %-6s | %-47s | %-15s | %-33s | %-24s | %-5s | %-10s | %-8s | %-10s | %-16s |%n";
            System.out.println("+===========================================================================================================================================================================================================+");
            System.out.println("|                                                                                     DATA KOLEKSI PERPUSTAKAAN                                                                                             |");
            System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
            System.out.println("|  KODE  |                      JUDUL                      |      JENIS      |             PENGARANG             |         PENERBIT         | TAHUN |    TIPE    |  NO RAK  |   STATUS   | TGL PENGEMBALIAN |");
            System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
            no = 0;
            while (rs.next()) {
                int Kode = rs.getInt("Kode");
                String Judul = rs.getString("Judul");
                String TahunTerbit = rs.getString("TahunTerbit");
                String Jenis = rs.getString("Jenis");
                String Pengarang = rs.getString("NamaPengarang");
                String Penerbit = rs.getString("NamaPenerbit");
                String Tipe = rs.getString("NamaTipe");
                String NoRak = rs.getString("NoRak");
                String Status = rs.getString("Status");
                String Peminjaman = rs.getString("TanggalKembali");
                no = no + 1;
                System.out.format(tbl, Kode, Judul, Jenis, Pengarang, Penerbit, TahunTerbit, Tipe, NoRak, Status, Peminjaman);
            }
            if (no == 0) {
                System.out.println("+===========================================================================================================================================================================================================+");
                System.out.println("|                                                                                      DATA YANG DICARI TIDAK DITEMUKAN                                                                                     |");
                System.out.println("|                                                                                TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                  |");
                System.out.println("+===========================================================================================================================================================================================================+");
            } else {
                System.out.println("+========+=================================================+=================+===================================+==========================+=======+============+==========+============+==================+");
                System.out.println("|                                                                                TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                  |");
                System.out.println("+===========================================================================================================================================================================================================+");
            }
            Tahan = scan.nextLine();
            ha.MenuUtamaUser();
        } catch (SQLException e) {
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaUser();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }
}
