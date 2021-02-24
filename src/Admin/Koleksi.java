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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Fauzanlh
 */
public class Koleksi {

    HomeAdmin h = new HomeAdmin();

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

    public void inputKoleksi() {
        //DONE
        clear();
        HomeAdmin ha = new HomeAdmin();
        int norak = 0, kode = 0;
        String Tahan;
        Scanner key = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(koneksi());
            Statement stmt = conn.createStatement();
            System.out.println("+=====================================================================================================================+");
            System.out.println("|                                              TAMBAH DATA KOLEKSI                                                    |");
            System.out.println("+=================+======================+========================+===================================================+");
            System.out.println("|       TIPE      |          JENIS       |        TERBITAN        |                       KATEGORI                    |");
            System.out.println("+=================+======================+========================+===================================================+");
            System.out.println("| 1. SIRKULASI    | 1. BUKU              | 1. DALAM NEGERI        | 1. PENGETAHUAN UMUM 7.  HUKUM      13. MANAJEMEN  |");
            System.out.println("| 2. REFERENSI    | 2. CD / AUDIO VISUAL | 2. DALAM NEGERI LANGKA | 2. KOMPUTER         8.  PENDIDIKAN 14. DESAIN     |");
            System.out.println("| 3. AUDIO VISUAL | 3. BERKALA / SERIAL  | 3. LUAR NEGERI         | 3. FILSAFAT         9.  BAHASA     15. ARSITEKTUR |");
            System.out.println("|                 | 4. KARTOGRAFIS       |                        | 4. AGAMA            10. MATEMATIKA 16. KESASTRAAN |");
            System.out.println("|                 |                      |                        | 5. SOSIAL           11. SCIENCE    17. GEOGRAFI   |");
            System.out.println("|                 |                      |                        | 6. EKONOMI          12. AKUNTASI   18. SEJARAH    |");
            System.out.println("+=================+======================+========================+===================================================+");
            String getKode = "SELECT * FROM T_KOLEKSI";
            ResultSet rs = stmt.executeQuery(getKode);
            while (rs.next()) {
                kode = rs.getInt("Kode");
            }
            kode = kode + 1;
            System.out.print("| MASUKKAN JUDUL BUKU            : ");
            String judul = key.nextLine().toUpperCase();
            System.out.print("| MASUKKAN NAMA PENGARANG        : ");
            String pengarang = key.nextLine().toUpperCase();
            System.out.print("| MASUKKAN NAMA PENERBIT         : ");
            String penerbit = key.nextLine().toUpperCase();
            System.out.print("| MASUKKAN TAHUN PENERBIT        : ");
            String thn = key.nextLine().toUpperCase();

            System.out.print("| MASUKKAN KODE TIPE             : ");
            String kdtipe = key.nextLine().toUpperCase();
            do {
                if (!kdtipe.matches("[1-3]+")) {
                    System.out.println("MASUKKAN HANYA ANGKA,ATAU HANYA DARI 1 SAMPAI 3");
                    System.out.print("| MASUKKAN KODE TIPE             : ");
                    kdtipe = key.nextLine().toUpperCase();
                }
            } while (!kdtipe.matches("[1-3]+"));

            System.out.print("| MASUKKAN KODE JENIS            : ");
            String kdjenis = key.nextLine().toUpperCase();
            do {
                if (!kdjenis.matches("[1-4]+")) {
                    System.out.println("MASUKKAN HANYA ANGKA,ATAU HANYA DARI 1 SAMPAI 4");
                    System.out.print("| MASUKKAN KODE JENIS            : ");
                    kdjenis = key.nextLine().toUpperCase();
                }
            } while (!kdjenis.matches("[1-4]+"));

            System.out.print("| MASUKKAN KODE TERBITAN         : ");
            String kdterbitan = key.nextLine().toUpperCase();
            do {
                if (!kdterbitan.matches("[1-3]+")) {
                    System.out.println("| MASUKKAN HANYA ANGKA,ATAU HANYA DARI 1 SAMPAI 3");
                    System.out.print("| MASUKKAN KODE TERBITAN         : ");
                    kdterbitan = key.nextLine().toUpperCase();
                }
            } while (!kdterbitan.matches("[1-3]+"));

            int kdkategori;
            String Kategori = null;
            System.out.print("| MASUKKAN KODE KATEGORI         : ");
            Kategori = key.nextLine().toUpperCase();
            do {
                if (!kdterbitan.matches("[1-9]+")) {
                    System.out.println("| MASUKKAN HANYA ANGKA,ATAU HANYA DARI 1 SAMPAI 18");
                    System.out.print("| MASUKKAN KODE KATEGORI         : ");
                    Kategori = key.nextLine().toUpperCase();
                }
                kdkategori = Integer.parseInt(Kategori);
                if (kdkategori <= 0 || kdkategori > 18) {
                    System.out.println("MASUKKAN HANYA ANGKA,ATAU HANYA DARI 1 SAMPAI 18");
                    System.out.print("| MASUKKAN KODE KATEGORI         : ");
                    Kategori = key.nextLine().toUpperCase();
                    kdkategori = Integer.parseInt(Kategori);
                }
            } while (kdkategori <= 0 && kdkategori > 18);
            if (kdkategori >= 1 && kdkategori <= 2) {
                norak = 1;
            } else if (kdkategori >= 3 && kdkategori <= 6) {
                norak = 2;
            } else if (kdkategori >= 7 && kdkategori <= 9) {
                norak = 3;
            } else if (kdkategori >= 10 && kdkategori <= 13) {
                norak = 4;
            } else if (kdkategori >= 14 && kdkategori <= 8) {
                norak = 5;
            }

            String Harga;
            System.out.print("| MASUKKAN HARGA                 : ");
            Harga = key.nextLine().toUpperCase();
            PreparedStatement pStatement = null;
            String sql = "INSERT INTO T_KOLEKSI (KODE, JUDUL, NAMAPENGARANG, NAMAPENERBIT, TAHUNTERBIT, KDJENIS, KDTIPE, LOKASI, STATUS, KDKATEGORI, KDTERBITAN,NORAK,HARGA) "
                    + "VALUES( '" + kode + "', '" + judul + "', '" + pengarang + "', '" + penerbit + "', '" + thn + "', '" + kdjenis + "' ,'" + kdtipe + "' ,'PERPUSTAKAAN UNIKOM', 'TERSEDIA', '" + kdkategori + "', '" + kdterbitan + "', '" + norak + "','" + Harga + "');";
            pStatement = conn.prepareStatement(sql);
            int intBaris = pStatement.executeUpdate();
            if (intBaris > 0) {
                System.out.println("+=====================================================================================================================+");
                System.out.println("|                                          PENAMBAHAN DATA BERHASIL DILAKUKAN                                         |");
                System.out.println("|                                       TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                     |");
                System.out.println("+=====================================================================================================================+");

            } else {
                System.out.println("+=====================================================================================================================+");
                System.out.println("|                                     PENAMBAHAN DATA GAGAL, CEK KEMBALI DATA YANG DIMASUKKAN!                        |");
                System.out.println("|                                          TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                  |");
                System.out.println("+=====================================================================================================================+");
            }
            Tahan = key.nextLine();
            ha.MenuUtamaAdmin();
            pStatement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("+=====================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = key.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }

    public void tampilKoleksi() {
        //DONE TINGGAL DESIGN
        clear();
        HomeAdmin ha = new HomeAdmin();
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
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+===========================================================================================================================================================================================================+");

            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }

    public void updateKoleksi() {
        clear();
        HomeAdmin ha = new HomeAdmin();
        Scanner scan = new Scanner(System.in);
        String Tahan;
        int norak = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();
            System.out.println("==================================================");
            System.out.println("|                   CARI KOLEKSI                 |");
            System.out.println("==================================================");
            System.out.print("| MASUKKAN NAMA KOLEKSI : ");
            String CariJudul = scan.nextLine().toUpperCase();
            String sql1 = "SELECT T_KOLEKSI.Kode,T_KOLEKSI.Judul, T_JENIS.Jenis, T_KOLEKSI.NamaPengarang, T_KOLEKSI.NamaPenerbit, T_KOLEKSI.TahunTerbit, T_TIPE.NamaTipe, T_KOLEKSI.NoRak, T_KOLEKSI.Status, IF(T_KOLEKSI.EstimasiPengembalian IS NULL,' ',T_KOLEKSI.EstimasiPengembalian) AS TanggalKembali "
                    + " FROM T_KOLEKSI,T_JENIS,T_TIPE WHERE JUDUL LIKE'%" + CariJudul + "%' AND T_KOLEKSI.KdTipe = T_TIPE.KdTipe AND T_KOLEKSI.KdJenis = T_JENIS.KdJenis";
            clear();

            ResultSet rs = stmt.executeQuery(sql1);
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
            String Pilih;
            do {
                System.out.print("| APAKAH ANDA AKAN MELAKUKAN UBAH DATA KOLEKSI [Y/T] : ");
                Pilih = scan.nextLine().toUpperCase();
                switch (Pilih) {
                    case "Y":
                        System.out.println("+===========================================================================================================================================================================================================+");
                        System.out.println("");
                        System.out.println("+=====================================================================================================================+");
                        System.out.println("|                                                 UBAH DATA KOLEKSI                                                   |");
                        System.out.println("==================+======================+========================+===================================================+");
                        System.out.println("|       TIPE      |          JENIS       |        TERBITAN        |                       KATEGORI                    |");
                        System.out.println("==================+======================+========================+===================================================+");
                        System.out.println("| 1. SIRKULASI    | 1. BUKU              | 1. DALAM NEGERI        | 1. PENGETAHUAN UMUM 7.  HUKUM      13. MANAJEMEN  |");
                        System.out.println("| 2. REFERENSI    | 2. CD / AUDIO VISUAL | 2. DALAM NEGERI LANGKA | 2. KOMPUTER         8.  PENDIDIKAN 14. DESAIN     |");
                        System.out.println("| 3. AUDIO VISUAL | 3. BERKALA / SERIAL  | 3. LUAR NEGERI         | 3. FILSAFAT         9.  BAHASA     15. ARSITEKTUR |");
                        System.out.println("|                 | 4. KARTOGRAFIS       |                        | 4. AGAMA            10. MATEMATIKA 16. KESASTRAAN |");
                        System.out.println("|                 | 4. KARTOGRAFIS       |                        | 5. SOSIAL           11. SCIENCE    17. GEOGRAFI   |");
                        System.out.println("|                 | 4. KARTOGRAFIS       |                        | 6. EKONOMI          12. AKUNTASI   18. SEJARAH    |");
                        System.out.println("==================+======================+========================+===================================================+");
                        PreparedStatement pStatement = null;
                        System.out.print("| MASUKKAN KODE YANG AKAN DIUBAH : ");
                        String kode = scan.nextLine().toUpperCase();
                        System.out.print("| MASUKKAN JUDUL BUKU            : ");
                        String judul = scan.nextLine().toUpperCase();
                        System.out.print("| MASUKKAN NAMA PENGARANG        : ");
                        String pengarang = scan.nextLine().toUpperCase();
                        System.out.print("| MASUKKAN NAMA PENERBIT         : ");
                        String penerbit = scan.nextLine().toUpperCase();
                        System.out.print("| MASUKKAN TAHUN PENERBIT        : ");
                        String thn = scan.nextLine().toUpperCase();
                        System.out.print("| MASUKKAN KODE TIPE             : ");
                        String kdtipe = scan.nextLine().toUpperCase();
                        do {
                            if (!kdtipe.matches("[1-3]+")) {
                                System.out.println("MASUKKAN HANYA ANGKA,ATAU HANYA DARI 1 SAMPAI 3");
                                System.out.print("| MASUKKAN KODE TIPE             : ");
                                kdtipe = scan.nextLine().toUpperCase();
                            }
                        } while (!kdtipe.matches("[1-3]+"));

                        System.out.print("| MASUKKAN KODE JENIS            : ");
                        String kdjenis = scan.nextLine().toUpperCase();
                        do {
                            if (!kdjenis.matches("[1-4]+")) {
                                System.out.println("MASUKKAN HANYA ANGKA,ATAU HANYA DARI 1 SAMPAI 4");
                                System.out.print("| MASUKKAN KODE JENIS            : ");
                                kdjenis = scan.nextLine().toUpperCase();
                            }
                        } while (!kdjenis.matches("[1-4]+"));

                        System.out.print("| MASUKKAN KODE TERBITAN         : ");
                        String kdterbitan = scan.nextLine().toUpperCase();
                        do {
                            if (!kdterbitan.matches("[1-3]+")) {
                                System.out.println("| MASUKKAN HANYA ANGKA,ATAU HANYA DARI 1 SAMPAI 3");
                                System.out.print("| MASUKKAN KODE TERBITAN         : ");
                                kdterbitan = scan.nextLine().toUpperCase();
                            }
                        } while (!kdterbitan.matches("[1-3]+"));
                        int kdkategori;
                        do {
                            System.out.print("| MASUKKAN KODE KATEGORI         : ");
                            String Kategori = scan.nextLine().toUpperCase();
                            kdkategori = Integer.parseInt(Kategori);
                            if (kdkategori <= 0 || kdkategori > 18) {
                                System.out.println("MASUKKAN HANYA ANGKA,ATAU HANYA DARI 1 SAMPAI 18");
                                System.out.print("| MASUKKAN KODE KATEGORI         : ");
                                Kategori = scan.nextLine().toUpperCase();
                                kdkategori = Integer.parseInt(Kategori);
                            }
                        } while (kdkategori <= 0 && kdkategori > 18);

                        if (kdkategori >= 1 && kdkategori <= 2) {
                            norak = 1;
                        } else if (kdkategori >= 3 && kdkategori <= 6) {
                            norak = 2;
                        } else if (kdkategori >= 7 && kdkategori <= 9) {
                            norak = 3;
                        } else if (kdkategori >= 10 && kdkategori <= 13) {
                            norak = 4;
                        } else if (kdkategori >= 14 && kdkategori <= 8) {
                            norak = 5;
                        }
                        String sql = "UPDATE t_koleksi set Judul='" + judul + "' , NamaPengarang='" + pengarang + "' , NamaPenerbit='" + penerbit + "' , TahunTerbit='" + thn + "' , NoRak='" + norak + "'"
                                + ", KdJenis='" + kdjenis + "' , KdTipe='" + kdtipe + "' , KdKategori='" + kdkategori + "',KdTerbitan='" + kdterbitan + "' where Kode='" + kode + "' ";
                        pStatement = con.prepareStatement(sql);
                        int intBaris = pStatement.executeUpdate();
                        if (intBaris > 0) {
                            System.out.println("+===========================================================================================================================================================================================================+");
                            System.out.println("|                                                                             PENGUBAHAN DATA BERHASIL DILAKUKAN                                                                                            |");
                            System.out.println("|                                                                           TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                       |");
                            System.out.println("+===========================================================================================================================================================================================================+");
                        } else {
                            System.out.println("+===========================================================================================================================================================================================================+");
                            System.out.println("|                                                                      PENGUBAHAN DATA GAGAL, CEK KEMBALI DATA YANG DIMASUKKAN!                                                                             |");
                            System.out.println("|                                                                           TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                       |");
                            System.out.println("+===========================================================================================================================================================================================================+");
                        }
                        Tahan = scan.nextLine();
                        ha.MenuUtamaAdmin();
                        pStatement.close();
                        con.close();
                        break;
                    case "T":
                        System.out.println("+===========================================================================================================================================================================================================+");
                        System.out.println("|                                                                           TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                                                       |");
                        System.out.println("+===========================================================================================================================================================================================================+");
                        Tahan = scan.nextLine();
                        ha.MenuUtamaAdmin();
                        break;
                    default:
                        System.out.println("| MASUKKAN HANYA Y ATAU T, ULANG!!");
                        break;
                }
            } while (Pilih == "Y" || Pilih == "T");

        } catch (SQLException e) {
            System.out.println("+===========================================================================================================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }

    public void cariKoleksiJudul() {
        clear();
        Scanner scan = new Scanner(System.in);
        HomeAdmin ha = new HomeAdmin();
        int no;
        String Tahan;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(koneksi());
            Statement stmt = con.createStatement();

            System.out.print("| MASUKKAN NAMA KOLEKSI : ");
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
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+===========================================================================================================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }

    public void cariKoleksiBdsPengarang() {
        clear();
        Scanner scan = new Scanner(System.in);
        HomeAdmin ha = new HomeAdmin();
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
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+===========================================================================================================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }

    public void cariKoleksiBdsPenerbit() {
        clear();
        Scanner scan = new Scanner(System.in);
        HomeAdmin ha = new HomeAdmin();
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
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+===========================================================================================================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void cariKoleksiBdsKategori() {
        clear();
        Scanner scan = new Scanner(System.in);
        HomeAdmin ha = new HomeAdmin();
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
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+===========================================================================================================================================================================================================+");
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
