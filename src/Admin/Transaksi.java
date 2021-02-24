/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Fauzanlh
 */
public class Transaksi {

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

    public void tampilPeminjaman() {
        clear();
        HomeAdmin ha = new HomeAdmin();
        String Tahan;
        Scanner scan = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            Statement stmt = con.createStatement();
            String sql = "SELECT T_PEMINJAMAN.KdPeminjaman, T_PEMINJAMAN.EstimasiPengembalian, T_KOLEKSI.Judul, T_PEMINJAMAN.KdAnggota, T_PEMINJAMAN.TglPinjam, T_PEMINJAMAN.EstimasiPengembalian, IF(T_PEMINJAMAN.TglKembali IS NULL, ' ',T_PEMINJAMAN.TglKembali) AS TanggalKembali, IF(T_PEMINJAMAN.Denda IS NULL,' ',T_PEMINJAMAN.Denda) AS Denda "
                    + "FROM T_PEMINJAMAN, T_KOLEKSI WHERE T_PEMINJAMAN.KODE = T_KOLEKSI.KODE";
            String tbl = "| %-6s | %-47s | %-12s | %-20s | %-20s | %-13s | %-11s |";
            System.out.println("+=====================================================================================================================================================+");
            System.out.println("|                                                       DATA PEMINJAMAN KOLEKSI                                                                       |");
            System.out.println("+========+=================================================+==============+======================+======================+===============+=============+");
            System.out.println("|  KODE  |                      JUDUL                      | KODE ANGGOTA |  TANGGAL PEMINJAMAN  | TANGGAL PENGEMBALIAN |    KEMBALI    |    DENDA    |");
            System.out.println("+========+=================================================+==============+======================+======================+===============+=============+");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int KdPeminjaman = rs.getInt("KdPeminjaman");
                String Koleksi = rs.getString("Judul");
                String KdAnggota = rs.getString("KdAnggota");
                String tglPinjam = rs.getString("TglPinjam");
                String esttglKembali = rs.getString("EstimasiPengembalian");
                String tglKembali = rs.getString("TanggalKembali");
                String Denda = rs.getString("Denda");

                System.out.format(tbl, KdPeminjaman, Koleksi, KdAnggota, tglPinjam, esttglKembali, tglKembali, Denda);
                System.out.println("");
            }
            System.out.println("+========+=================================================+==============+======================+======================+===============+=============+");
            System.out.println("|                                                  TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!                                                          |");
            System.out.println("+=====================================================================================================================================================+");
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+=====================================================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void inputPeminjaman() {
        clear();
        LocalDate Tanggal = java.time.LocalDate.now();
        LocalDate Estimasi = Tanggal.plusDays(7);
        String KdBuku, KdAnggota;
        Scanner scan = new Scanner(System.in);
        HomeAdmin ha = new HomeAdmin();
        int TotalPeminjaman = 0;
        String Tahan;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            PreparedStatement ps = null;
            Statement stmt = con.createStatement();
            System.out.println("===================================================");
            System.out.println("|                 PEMINJAMAN KOLEKSI              |");
            System.out.println("===================================================");
            System.out.print("| MASUKKAN KODE ANGGOTA : ");
            KdAnggota = scan.nextLine().toUpperCase();
            System.out.print("| MASUKKAN KODE BUKU    : ");
            KdBuku = scan.nextLine().toUpperCase();
            System.out.println("| TANGGAL PINJAM        : " + Tanggal + "             |");
            String HitungPeminjaman = "SELECT COUNT(KdPeminjaman) AS TOTAL FROM t_peminjaman,T_ANGGOTA WHERE T_PEMINJAMAN.KdAnggota='" + KdAnggota + "' AND t_peminjaman.`KdAnggota` = t_anggota.`KdAnggota` AND T_PEMINJAMAN.STATUS='DIPINJAM'";
            ResultSet rs1 = stmt.executeQuery(HitungPeminjaman);
            if (rs1.next()) {
                TotalPeminjaman = rs1.getInt("TOTAL");
            }
            String sql = "INSERT INTO T_PEMINJAMAN(KODE,KDANGGOTA,TGLPINJAM,ESTIMASIPENGEMBALIAN,STATUS) VALUES ('" + KdBuku + "','" + KdAnggota + "','" + Tanggal + "','" + Estimasi + "','DIPINJAM')";
            String sql2 = "UPDATE t_koleksi SET STATUS = 'DIPINJAM', EstimasiPengembalian='" + Estimasi + "' WHERE Kode='" + KdBuku + "'";

            String CekStatusPeminjam = "SELECT * FROM T_ANGGOTA WHERE KdAnggota = '" + KdAnggota + "'";
            ResultSet rs = stmt.executeQuery(CekStatusPeminjam);
            if (rs.next()) {
                if (rs.getString("Status").equals("KHUSUS")) {
                    if (TotalPeminjaman < 5) {
                        ps = con.prepareStatement(sql);
                    } else {
                        System.out.println("==================================================");
                        System.out.println("|            MAAF, MAX PEMINJAMAN HANYA 5        |");
                        System.out.println("|     TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!  |");
                        System.out.println("==================================================");
                        Tahan = scan.nextLine();
                        ha.MenuUtamaAdmin();
                    }
                } else if (rs.getString("Status").equals("BIASA")) {
                    if (TotalPeminjaman >= 3) {
                        System.out.println("==================================================");
                        System.out.println("|            MAAF, MAX PEMINJAMAN HANYA 3        |");
                        System.out.println("|     TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!  |");
                        System.out.println("==================================================");
                        Tahan = scan.nextLine();
                        ha.MenuUtamaAdmin();
                    } else if (TotalPeminjaman < 3) {
                        ps = con.prepareStatement(sql);
                    }
                } else if (rs.getString("Status").equals("BEBAS PINJAM")) {
                    System.out.println("==================================================");
                    System.out.println("|            STATUS ANDA BEBAS PINJAM            |");
                    System.out.println("|           UBAH STATUS TERLEBIH DAHULU          |");
                    System.out.println("|     TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!  |");
                    System.out.println("==================================================");
                    Tahan = scan.nextLine();
                    ha.MenuUtamaAdmin();
                }
            }
            int intBaris = ps.executeUpdate();
            if (intBaris > 0) {
                ps = con.prepareStatement(sql2);
                ps.executeUpdate();
                ps.close();
                con.close();
                System.out.println("==================================================");
                System.out.println("|        PENAMBAHAN DATA BERHASIL DILAKUKAN      |");
                System.out.println("|     TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!  |");
                System.out.println("==================================================");
                Tahan = scan.nextLine();
                ha.MenuUtamaAdmin();
            } else {
                System.out.println("==================================================");
                System.out.println("| PENAMBAHAN DATA GAGAL,CEK DATA YANG DIMASUKKAN |");
                System.out.println("|     TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!  |");
                System.out.println("==================================================");
                Tahan = scan.nextLine();
                ha.MenuUtamaAdmin();
            }
        } catch (SQLException e) {
            System.out.println("==================================================");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    //INPUT PENGEMBALIAN
    public void cariAnggota() {
        clear();
        Scanner scan = new Scanner(System.in);
        String Pilih, Tahan;
        HomeAdmin ha = new HomeAdmin();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            Statement stmt = con.createStatement();
            System.out.println("==================================================");
            System.out.println("| CARI ANGGOTA YANG BELUM MENGEMBALIKAN KOLEKSI  |");
            System.out.println("==================================================");
            System.out.print("| MASUKKAN KODE ANGGOTA: ");
            String CariAnggota = scan.nextLine().toUpperCase();
            System.out.println("==================================================");
            clear();
            String sql = "SELECT * FROM T_PEMINJAMAN,T_KOLEKSI,T_ANGGOTA WHERE T_PEMINJAMAN.KDANGGOTA LIKE '%" + CariAnggota + "%' AND T_PEMINJAMAN.STATUS='DIPINJAM' AND T_PEMINJAMAN.KODE = T_KOLEKSI.Kode AND T_PEMINJAMAN.KDANGGOTA = T_ANGGOTA.KDANGGOTA";
            String tbl = "| %-6s | %-47s | %-12s | %-18s |";
            System.out.println("");
            System.out.println("+==============================================================================================+");
            System.out.println("|              DATA PEMINJAMAN BUKU YANG BELUM DIKEMBALIKAN              |");
            System.out.println("+========+=================================================+==============+====================+");
            System.out.println("|  KODE  |                      JUDUL                      | KODE ANGGOTA | TANGGAL PEMINJAMAN |");
            System.out.println("+========+=================================================+==============+====================+");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String KdPeminjaman = rs.getString("KdPeminjaman");
                String KdAnggota = rs.getString("KdAnggota");
                String Judul = rs.getString("Judul");
                String tglPinjam = rs.getString("TglPinjam");
                System.out.format(tbl, KdPeminjaman, Judul, KdAnggota, tglPinjam);
                System.out.println("");
            }
            System.out.println("+========+=================================================+==============+====================+");
            System.out.println(" APAKAH ANDA AKAN MELAKUKAN INPUT PENGEMBALIAN KOLEKSI [Y / T] :");
            do {
                Pilih = scan.nextLine().toUpperCase();
                switch (Pilih) {
                    case "Y":
                        simpanSQLPengembalian();
                        break;
                    case "T":
                        ha.MenuUtamaAdmin();
                        break;
                    default:
                        System.out.println("PILIHAN HANYA Y ATAU T, ULANG!!");
                        System.out.println(" APAKAH ANDA AKAN MELAKUKAN INPUT PENGEMBALIAN KOLEKSI [Y / T] :");
                        break;
                }
            } while (Pilih != "Y" || Pilih != "T");
        } catch (SQLException e) {
            System.out.println("+==============================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void simpanSQLPengembalian() {
        clear();
        Scanner scan = new Scanner(System.in);
        LocalDate Tanggal = LocalDate.now();
        HomeAdmin ha = new HomeAdmin();
        String Tahan;
        int Denda;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            PreparedStatement ps = null;
            Statement stmt = con.createStatement();
            System.out.println("===================================================");
            System.out.println("|               PENGEMBALIAN KOLEKSI              |");
            System.out.println("===================================================");
            System.out.print("| MASUKKAN KODE PEMINJAMAN : ");
            String Kode = scan.nextLine().toUpperCase();
            System.out.println("| TANGGAL PENGEMBALIAN     : " + Tanggal);

            String sql = "UPDATE T_PEMINJAMAN SET TglKembali = '" + Tanggal + "', STATUS='DIKEMBALIKAN' where KDPEMINJAMAN='" + Kode + "'";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            int Baris2 = ps.executeUpdate();
            String sql4 = "UPDATE t_peminjaman,t_koleksi SET t_koleksi.STATUS='TERSEDIA', t_koleksi.EstimasiPengembalian=(NULL)"
                    + "WHERE T_peminjaman.KdPeminjaman='" + Kode + "' AND t_peminjaman.Kode = t_koleksi.Kode";
            ps = con.prepareStatement(sql4);
            int Baris = ps.executeUpdate();

            String sql1 = "SELECT DATEDIFF(TglKembali,EstimasiPengembalian)  AS 'DATEDIFF'FROM t_peminjaman where KDPEMINJAMAN='" + Kode + "'";
            ResultSet rs = stmt.executeQuery(sql1);
            while (rs.next()) {
                int PerbedaanHari = rs.getInt("DATEDIFF");
                if (PerbedaanHari < 0) {
                    Denda = 0;
                } else {
                    Denda = PerbedaanHari * 2000;
                }
                String sql2 = "UPDATE T_PEMINJAMAN SET DENDA = '" + Denda + "' where KDPEMINJAMAN = '" + Kode + "'";
                ps = con.prepareStatement(sql2);
            }
            if (Baris > 0 && Baris2 > 0) {
                System.out.println("===================================================");
                System.out.println("|        PENAMBAHAN DATA BERHASIL DILAKUKAN       |");
                System.out.println("|     TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!   |");
                System.out.println("===================================================");
            } else {
                System.out.println("===================================================");
                System.out.println("|  PENAMBAHAN DATA GAGAL,CEK DATA YANG DIMASUKKAN |");
                System.out.println("|      TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!  |");
                System.out.println("===================================================");
            }
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (SQLException e) {
            System.out.println("+==============================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }

    public void simpanPengembalian() {
        String pilih1, pilih;
        clear();
        HomeAdmin ha = new HomeAdmin();
        Scanner scan = new Scanner(System.in);
        System.out.print("APAKAH ANDA AKAN MENCARI DATA PEMINJAMAN TERLEBIH DAHULU UNTUK MENGETAHUI KODE PEMINJAMAM [Y/T] : ");
        do {
            pilih1 = scan.nextLine().toUpperCase();
            switch (pilih1) {
                case "Y":
                    cariAnggota();
                    break;
                case "T":
                    System.out.print("| APAKAH ANDA AKAN MELAKUKAN INPUT PENGEMBALIAN KOLEKSI [Y/T] : ");
                    do {
                        pilih = scan.nextLine().toUpperCase();
                        switch (pilih) {
                            case "Y":
                                simpanSQLPengembalian();
                                break;
                            case "T":
                                ha.MenuUtamaAdmin();
                                break;
                            default:
                                System.out.println("PILIHAN HANYA Y ATAU T,ULANGI!!");
                                System.out.println("APAKAH ANDA AKAN MELAKUKAN INPUT PENGEMBALIAN KOLEKSI [Y/T] : ");
                                break;
                        }
                    } while (pilih != "Y" || pilih != "T");
                    break;
                default:
                    System.out.println("PILIHAN HANYA Y ATAU T,ULANGI!!");
                    System.out.println("APAKAH ANDA AKAN MENCARI DATA PEMINJAMAN TERLEBIH DAHULU UNTUK MENGETAHUI KODE PEMINJAMAM [Y/T] : ");
            }
        } while (pilih1 != "Y" || pilih1 != "T");
    }

    //INPUT KOLEKSI RUSAK
    //BAYAR DENDA
    public void MencariKodePeminjaman() {
        clear();
        HomeAdmin ha = new HomeAdmin();
        Scanner scan = new Scanner(System.in);
        String CariKodeAnggota, Pilih, Tahan;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            Statement stmt = con.createStatement();
            System.out.print("| MASUKKAN KODE ANGGOTA : ");
            CariKodeAnggota = scan.nextLine().toUpperCase();
            String sql = "SELECT T_PEMINJAMAN.KdPeminjaman, T_PEMINJAMAN.EstimasiPengembalian, T_KOLEKSI.Judul, T_PEMINJAMAN.KdAnggota, T_PEMINJAMAN.TglPinjam, T_PEMINJAMAN.EstimasiPengembalian, IF(T_PEMINJAMAN.TglKembali IS NULL, ' ',T_PEMINJAMAN.TglKembali) AS TanggalKembali, IF(T_PEMINJAMAN.Denda IS NULL,' ',T_PEMINJAMAN.Denda) AS Denda "
                    + "FROM T_PEMINJAMAN, T_KOLEKSI WHERE T_PEMINJAMAN.KODE = T_KOLEKSI.KODE AND T_PEMINJAMAN.KDANGGOTA = '" + CariKodeAnggota + "' AND T_PEMINJAMAN.STATUS = 'DIPINJAM'";
            String tbl = "| %-6s | %-47s | %-12s | %-20s | %-20s | %-13s | %-11s |";
            System.out.println("+=====================================================================================================================================================+");
            System.out.println("|                                                       DATA PEMINJAMAN KOLEKSI                                                                       |");
            System.out.println("+========+=================================================+==============+======================+======================+===============+=============+");
            System.out.println("|  KODE  |                      JUDUL                      | KODE ANGGOTA |  TANGGAL PEMINJAMAN  | TANGGAL PENGEMBALIAN |    KEMBALI    |    DENDA    |");
            System.out.println("+========+=================================================+==============+======================+======================+===============+=============+");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int KdPeminjaman = rs.getInt("KdPeminjaman");
                String Koleksi = rs.getString("Judul");
                String KdAnggota = rs.getString("KdAnggota");
                String tglPinjam = rs.getString("TglPinjam");
                String esttglKembali = rs.getString("EstimasiPengembalian");
                String tglKembali = rs.getString("TanggalKembali");
                String Denda = rs.getString("Denda");
                System.out.format(tbl, KdPeminjaman, Koleksi, KdAnggota, tglPinjam, esttglKembali, tglKembali, Denda);
                System.out.println("");
            }
            System.out.println("+========+=================================================+==============+======================+======================+===============+=============+");
            System.out.print(" APAKAH ANDA AKAN MELAKUKAN INPUT PENGGANTIAN KOLEKSI DENGAN DENDA [Y / T] : ");
            do {
                Pilih = scan.nextLine().toUpperCase();
                switch (Pilih) {
                    case "Y":
                        simpanBayarDenda();
                        Tahan = scan.nextLine();
                        break;
                    case "T":
                        ha.MenuUtamaAdmin();
                        break;
                    default:
                        System.out.println("MASUKKAN SALAH, MASUKKAN KEMBALI [Y/T]: ");
                        break;
                }
            } while (Pilih != "Y" || Pilih != "T");
        } catch (SQLException e) {
            System.out.println("+==============================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void simpanBayarDenda() {
        clear();
        LocalDate Tanggal = java.time.LocalDate.now();
        HomeAdmin ha = new HomeAdmin();
        Scanner scan = new Scanner(System.in);
        String KdPeminjaman, Tahan;
        int Denda = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            PreparedStatement ps = null;
            Statement stmt = con.createStatement();

            System.out.print("MASUKKAN KODE PEMINJAMAN : ");
            KdPeminjaman = scan.nextLine().toUpperCase();
            clear();
            String sql = "SELECT * FROM T_PEMINJAMAN,T_ANGGOTA,T_KOLEKSI,T_TERBITAN WHERE T_PEMINJAMAN.KDPEMINJAMAN =  '" + KdPeminjaman + "' AND T_PEMINJAMAN.STATUS ='DIPINJAM' "
                    + "AND T_PEMINJAMAN.STATUS = 'DIPINJAM' AND T_PEMINJAMAN.KDANGGOTA = T_ANGGOTA.KDANGGOTA "
                    + "AND T_PEMINJAMAN.KODE = T_KOLEKSI.KODE AND T_KOLEKSI.KDTERBITAN = T_TERBITAN.KDTERBITAN";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                KdPeminjaman = rs.getString("KdPeminjaman");
                String Terbitan = rs.getString("Terbitan");
                String KodeKoleksi = rs.getString("Kode");
                int Harga = rs.getInt("Harga");
                System.out.println("====================================================");
                System.out.println("|             PENGGANTIAN KOLEKSI DENDA            |");
                System.out.println("====================================================");
                System.out.println("| MASUKKAN KODE PEMINJAMAN : " + KdPeminjaman + "      ");
                System.out.println("| TANGGAL PEMBAYARAN DENDA : " + Tanggal + "      ");
                System.out.println("| TERBITAN                 : " + Terbitan + "      ");
                System.out.println("====================================================");
                if (Terbitan.equals("LUAR NEGERI")) {
                    Denda = Harga * 3;
                } else if (Terbitan.equals("DALAM NEGERI LANGKA")) {
                    Denda = Harga * 2;
                } else if (Terbitan.equals("DALAM NEGERI")) {
                    Denda = (Harga) * 1;
                }
                System.out.println("| MAKA DENDA YANG HARUS DIBAYAR : " + Denda + "");
                System.out.println("====================================================");
                String pilih;
                do {
                    System.out.println("| APAKAH ANDA YAKIN DENGA DATA DIATAS [Y / T] : ");
                    pilih = scan.nextLine().toUpperCase();
                    switch (pilih) {
                        case "Y":
                            String sqlUpdatePeminjaman = "UPDATE T_PEMINJAMAN SET STATUS ='HILANG', TGLKEMBALI = '" + Tanggal + "' ,DENDA = '" + Denda + "' WHERE T_PEMINJAMAN.KDPEMINJAMAN = '" + KdPeminjaman + "'";
                            ps = con.prepareStatement(sqlUpdatePeminjaman);
                            int BarisPeminjaman = ps.executeUpdate();
                            String sqlUpdateKoleksi = "UPDATE T_KOLEKSI SET STATUS = 'HILANG', ESTIMASIPENGEMBALIAN = NULL WHERE KODE = '" + KodeKoleksi + "'";
                            ps = con.prepareStatement(sqlUpdateKoleksi);
                            int BarisKoleksi = ps.executeUpdate();
                            String sqlInput = "INSERT INTO T_KEHILANGANBAYAR(TGLPENGGANTIAN, BAYAR, KDPEMINJAMAN) VALUES('" + Tanggal + "', " + Denda + ", '" + KdPeminjaman + "')";
                            ps = con.prepareStatement(sqlInput);
                            int BarisInput = ps.executeUpdate();
                            if (BarisPeminjaman > 0 && BarisKoleksi > 0 && BarisInput > 0) {
                                System.out.println("===================================================");
                                System.out.println("|        PENAMBAHAN DATA BERHASIL DILAKUKAN       |");
                                System.out.println("|     TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!   |");
                                System.out.println("===================================================");
                                Tahan = scan.nextLine();
                                ha.MenuUtamaAdmin();
                            }
                            break;
                        case "T":
                            System.out.println("TEKAN ENTER UNTUK KEMBALI KE MENU KOLEKSI");
                            Tahan = scan.nextLine();
                            ha.MenuUtamaAdmin();
                            break;
                        default:
                            System.out.println("DATA YANG DIMASUKKAN SALAH, ULANG!!");
                    }
                } while (pilih != "Y" || pilih != "T");

            } else if (!rs.next()) {
                System.out.println("BUKU SUDAH DIKEMBALIKAN");
                System.out.println("TEKAN ENTER UNTUK KEMBALI KE MENU KOLEKSI");
                Tahan = scan.nextLine();
                ha.MenuUtamaAdmin();
            }
        } catch (SQLException e) {
            System.out.println("+==============================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }

    public void BayarDenda() {
        Scanner scan = new Scanner(System.in);
        String Pilih;
        clear();
        System.out.println("| APAKAH ANDA AKAN MENCARI KODE PEMINJAMAN TERLEBIH DAHULU [Y / T]");
        do {
            Pilih = scan.nextLine().toUpperCase();
            switch (Pilih) {
                case "Y":
                    MencariKodePeminjaman();
                    break;
                case "T":
                    simpanBayarDenda();
                    break;
                default:
                    System.out.println("PILIHAN HANYA Y ATAU T,MASUKKAN KEMBALI PILIHAN : ");
                    break;
            }
        } while (Pilih != "Y" || Pilih != "T");
    }

    //GANTI KOLEKSI
    public void inputKoleksi() {
        //DONE
        clear();
        HomeAdmin ha = new HomeAdmin();
        int norak = 0, kode = 0;
        String Tahan;
        Scanner key = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Koneksi());
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
            System.out.println("| KODE BUKU                      : " + kode + "|");
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
                System.out.println("|                                                   TEKAN ENTER !!                                                    |");
                System.out.println("+=====================================================================================================================+");
                Tahan = key.nextLine();
                simpanGantiKoleksi();
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

    public void simpanGantiKoleksi() {
        clear();
        LocalDate Tanggal = java.time.LocalDate.now();
        HomeAdmin ha = new HomeAdmin();
        Scanner scan = new Scanner(System.in);
        String Tahan;
        String KdPeminjaman, pilih;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            PreparedStatement ps = null;
            Statement stmt = con.createStatement();
            System.out.print("MASUKKAN KODE PEMINJAMAN : ");
            KdPeminjaman = scan.nextLine().toUpperCase();
            String sql = "SELECT * FROM T_PEMINJAMAN,T_ANGGOTA,T_KOLEKSI,T_TERBITAN WHERE T_PEMINJAMAN.KDPEMINJAMAN =  '" + KdPeminjaman + "' AND T_PEMINJAMAN.STATUS ='DIPINJAM' "
                    + "AND T_PEMINJAMAN.STATUS = 'DIPINJAM' AND T_PEMINJAMAN.KDANGGOTA = T_ANGGOTA.KDANGGOTA "
                    + "AND T_PEMINJAMAN.KODE = T_KOLEKSI.KODE AND T_KOLEKSI.KDTERBITAN = T_TERBITAN.KDTERBITAN";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                KdPeminjaman = rs.getString("KdPeminjaman");
                String KodeKoleksi = rs.getString("Kode");
                System.out.println("====================================================");
                System.out.println("|                PENGGANTIAN KOLEKSI               |");
                System.out.println("====================================================");
                System.out.println("| MASUKKAN KODE PEMINJAMAN : " + KdPeminjaman + "                     |");
                System.out.println("| TANGGAL PENGGANTIAN BUKU : " + Tanggal + "            |");
                System.out.print("| MASUKKAN KODE BUKU PENGGANTI : ");
                String KodeBukuPenganti = scan.nextLine().toUpperCase();
                System.out.println("====================================================");
                System.out.println("| APAKAH ANDA YAKIN DENGAN DATA DIATAS [Y / T] : ");
                do {
                    pilih = scan.nextLine().toUpperCase();
                    switch (pilih) {
                        case "Y":
                            String sqlUpdatePeminjaman = "UPDATE T_PEMINJAMAN SET STATUS ='HILANG', TGLKEMBALI = '" + Tanggal + "' ,DENDA = 'GANTI BUKU' WHERE T_PEMINJAMAN.KDPEMINJAMAN = '" + KdPeminjaman + "'";
                            ps = con.prepareStatement(sqlUpdatePeminjaman);
                            int BarisPeminjaman = ps.executeUpdate();
                            String sqlUpdateKoleksi = "UPDATE T_KOLEKSI SET STATUS = 'HILANG', ESTIMASIPENGEMBALIAN = NULL WHERE KODE = '" + KodeKoleksi + "'";
                            ps = con.prepareStatement(sqlUpdateKoleksi);
                            int BarisKoleksi = ps.executeUpdate();
                            String sqlInput = "INSERT INTO T_KEHILANGANGANTI(TGLPENGGANTIAN, KODE, KDPEMINJAMAN) VALUES('" + Tanggal + "', " + KodeBukuPenganti + ", '" + KdPeminjaman + "')";
                            ps = con.prepareStatement(sqlInput);
                            int BarisInput = ps.executeUpdate();
                            if (BarisPeminjaman > 0 && BarisKoleksi > 0 && BarisInput > 0) {
                                System.out.println("====================================================");
                                System.out.println("|          PENAMBAHAN DATA BERHASIL DILAKUKAN      |");
                                System.out.println("|                    TEKAN ENTER !!                |");
                                System.out.println("====================================================");
                                Tahan = scan.nextLine();
                                ha.MenuUtamaAdmin();
                            }
                            break;
                        case "T":
                            System.out.println("====================================================");
                            System.out.println("|                    TEKAN ENTER !!                |");
                            System.out.println("====================================================");
                            Tahan = scan.nextLine();
                            ha.MenuUtamaAdmin();
                            break;
                        default:
                            System.out.println("MASUKKAN SALAH, MASUKKAN KEMBALI [Y/T]: ");
                            break;
                    }
                } while (pilih != "Y" || pilih != "T");

            } else if (!rs.next()) {
                System.out.println("====================================================");
                System.out.println("|            KODE PEMINJAMAN YANG SALAH            |");
                System.out.println("|     TEKAN ENTER UNTUK KEMBALI KE MENU UTAMA!!    |");
                System.out.println("====================================================");
                Tahan = scan.nextLine();
                ha.MenuUtamaAdmin();
            }
        } catch (SQLException e) {
            System.out.println("+=====================================================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");

        }
    }

    public void MencariKodePeminjaman2() {
        clear();
        HomeAdmin ha = new HomeAdmin();
        Scanner scan = new Scanner(System.in);
        String CariKodeAnggota, Pilih, Tahan;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Koneksi());
            Statement stmt = con.createStatement();
            System.out.print("| MASUKKAN KODE ANGGOTA : ");
            CariKodeAnggota = scan.nextLine().toUpperCase();
            String sql = "SELECT T_PEMINJAMAN.KdPeminjaman, T_PEMINJAMAN.EstimasiPengembalian, T_KOLEKSI.Judul, T_PEMINJAMAN.KdAnggota, T_PEMINJAMAN.TglPinjam, T_PEMINJAMAN.EstimasiPengembalian, IF(T_PEMINJAMAN.TglKembali IS NULL, ' ',T_PEMINJAMAN.TglKembali) AS TanggalKembali, IF(T_PEMINJAMAN.Denda IS NULL,' ',T_PEMINJAMAN.Denda) AS Denda "
                    + "FROM T_PEMINJAMAN, T_KOLEKSI WHERE T_PEMINJAMAN.KODE = T_KOLEKSI.KODE AND T_PEMINJAMAN.KDANGGOTA = '" + CariKodeAnggota + "' AND T_PEMINJAMAN.STATUS = 'DIPINJAM'";
            String tbl = "| %-6s | %-47s | %-12s | %-20s | %-20s | %-13s | %-11s |";
            System.out.println("+=====================================================================================================================================================+");
            System.out.println("|                                                       DATA PEMINJAMAN KOLEKSI                                                                       |");
            System.out.println("+========+=================================================+==============+======================+======================+===============+=============+");
            System.out.println("|  KODE  |                      JUDUL                      | KODE ANGGOTA |  TANGGAL PEMINJAMAN  | TANGGAL PENGEMBALIAN |    KEMBALI    |    DENDA    |");
            System.out.println("+========+=================================================+==============+======================+======================+===============+=============+");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int KdPeminjaman = rs.getInt("KdPeminjaman");
                String Koleksi = rs.getString("Judul");
                String KdAnggota = rs.getString("KdAnggota");
                String tglPinjam = rs.getString("TglPinjam");
                String esttglKembali = rs.getString("EstimasiPengembalian");
                String tglKembali = rs.getString("TanggalKembali");
                String Denda = rs.getString("Denda");
                System.out.format(tbl, KdPeminjaman, Koleksi, KdAnggota, tglPinjam, esttglKembali, tglKembali, Denda);
                System.out.println("");
            }
            System.out.println("+========+=================================================+==============+======================+======================+===============+=============+");
            System.out.print(" APAKAH ANDA AKAN MELAKUKAN INPUT PENGGANTIAN KOLEKSI DENGAN KOLEKSI YANG SAMA [Y / T] : ");
            do {
                Pilih = scan.nextLine().toUpperCase();
                switch (Pilih) {
                    case "Y":
                        inputKoleksi();
                        Tahan = scan.nextLine();
                        break;
                    case "T":
                        ha.MenuUtamaAdmin();
                        break;
                    default:
                        System.out.println("MASUKKAN SALAH, MASUKKAN KEMBALI [Y/T]: ");
                        break;
                }
            } while (Pilih != "Y" || Pilih != "T");
        } catch (SQLException e) {
            System.out.println("+==============================================================================================+");
            System.out.println("ERROR : " + e);
            Tahan = scan.nextLine();
            ha.MenuUtamaAdmin();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC DRIVER TIDAK DITEMUKAN");
            System.out.println("JIKA ANDA MEMBUKA MENGGUNAKAN CMD, MOHON BUAT VARIABEL BERNAMA CLASSPATH DI ENVIRONMENT VARIABLES ");
            System.out.println("DAN ATUR DIREKTORI NYA DENGAN 'MYSQLCONNECTOR.JAR' YANG SUDAH SAYA SATUKAN BESERTA FILE JAR INI");
        }
    }

    public void GantiKoleksi() {
        clear();
        Scanner scan = new Scanner(System.in);
        String Pilih;
        System.out.println("| APAKAH ANDA AKAN MENCARI KODE PEMINJAMAN TERLEBIH DAHULU [Y / T]");
        do {
            Pilih = scan.nextLine().toUpperCase();
            switch (Pilih) {
                case "Y":
                    MencariKodePeminjaman2();
                    break;
                case "T":
                    inputKoleksi();
                    break;
                default:
                    System.out.println("PILIHAN HANYA Y ATAU T,MASUKKAN KEMBALI PILIHAN : ");
                    break;
            }
        } while (Pilih != "Y" || Pilih != "T");
    }

}
