/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.util.Scanner;

/**
 *
 * @author Fauzanlh
 */
public class HomeAdmin {

    public void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println("" + E);
        }
    }

    public void CariKoleksi() {
        clear();
        Koleksi k = new Koleksi();
        Scanner scan = new Scanner(System.in);
        System.out.println("====================================================");
        System.out.println("|                 CARI DATA KOLEKSI                |");
        System.out.println("====================================================");
        System.out.println("| 1. CARI KOLEKSI BERDASATKAN JUDUL                |");
        System.out.println("| 2. CARI KOLEKSI BERDASATKAN PENGARANG            |");
        System.out.println("| 3. CARI KOLEKSI BERDASATKAN PENERBIT             |");
        System.out.println("| 4. CARI KOLEKSI BERDASATKAN KATEGORI             |");
        System.out.println("====================================================");
        System.out.println("| 5. KEMBALI KE MENU KOLEKSI                       |");
        System.out.println("| 0. KEMBALI KE MENU UTAMA                         |");
        System.out.println("====================================================");
        System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
        String Pilih;
        Pilih = scan.nextLine();
        do {
            switch (Pilih) {
                case "0":
                    MenuUtamaAdmin();
                    break;
                case "1":
                    k.cariKoleksiJudul();
                    break;
                case "2":
                    k.cariKoleksiBdsPengarang();
                    break;
                case "3":
                    k.cariKoleksiBdsPenerbit();
                    break;
                case "4":
                    k.cariKoleksiBdsKategori();
                    break;
                case "5":
                    MenuKoleksi();
                    break;
                default:
                    System.out.println("MENU YANG DIPILIH HANYA DARI 0 SAMPAI 5,MASUKKAN KEMBALI MENU YANG DIPILIH !!!");
                    System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
                    Pilih = scan.nextLine();
            }
        } while (Pilih != "4" || Pilih != "3" || Pilih != "2" || Pilih != "1" || Pilih != "0");
    }

    public void MenuKoleksi() {
        clear();
        Koleksi k = new Koleksi();
        System.out.println("====================================================");
        System.out.println("|                   MENU KOLEKSI                   |");
        System.out.println("====================================================");
        System.out.println("| 1. TAMPIL DATA KOLEKSI                           |");
        System.out.println("| 2. TAMBAH DATA KOLEKSI                           |");
        System.out.println("| 3. UBAH DATA KOLEKSI                             |");
        System.out.println("| 4. CARI DATA KOLEKSI                             |");
        System.out.println("====================================================");
        System.out.println("| 0. KEMBALI KE MENU UTAMA                         |");
        System.out.println("====================================================");
        System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
        Scanner scan = new Scanner(System.in);
        String Pilih;
        Pilih = scan.nextLine();
        do {
            switch (Pilih) {
                case "0":
                    MenuUtamaAdmin();
                    break;
                case "1":
                    k.tampilKoleksi();
                    break;
                case "2":
                    k.inputKoleksi();
                    break;
                case "3":
                    k.updateKoleksi();
                    break;
                case "4":
                    CariKoleksi();
                    break;
                default:
                    System.out.println("MENU YANG DIPILIH HANYA DARI 0 SAMPAI 4,MASUKKAN KEMBALI MENU YANG DIPILIH !!!");
                    System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
                    Pilih = scan.nextLine();
            }
        } while (Pilih != "4" || Pilih != "3" || Pilih != "2" || Pilih != "1" || Pilih != "0");
    }

    public void MenuLaporan() {
        clear();
        Laporan l = new Laporan();
        System.out.println("====================================================");
        System.out.println("|                  MENU LAPORAN                    |");
        System.out.println("====================================================");
        System.out.println("| 1. LAPORAN PEMINJAMAN BULANAN                    |");
        System.out.println("| 2. LAPORAN PEMINJAMAN TAHUNAN                    |");
        System.out.println("| 3. LAPORAN PEMINJAMAN BELUM DIKEMBALIKAN TAHUNAN |");
        System.out.println("| 4. LAPORAN PEMINJAMAN KOLEKSI TERBANYAK TAHUNAN  |");
        System.out.println("| 5. LAPORAN PENGEMBALIAN BULANAN                  |");
        System.out.println("====================================================");
        System.out.println("| 0. KEMBALI KE MENU UTAMA                         |");
        System.out.println("====================================================");
        System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
        Scanner scan = new Scanner(System.in);
        String Pilih;
        Pilih = scan.nextLine();
        do {
            switch (Pilih) {
                case "0":
                    MenuUtamaAdmin();
                    break;
                case "1":
                    l.LaporanPeminjamanBulanan();
                    break;
                case "2":
                    l.LaporanPeminjamanTahunan();
                    break;
                case "3":
                    l.LaporanPeminjamanBelumDikembalikanTahunan();
                    break;
                case "4":
                    l.laporanPeminjamanKoleksiTerbanyakTahunan();
                    break;
                case "5":
                    l.laporanPengembalian();
                    break;
                default:
                    System.out.println("MENU YANG DIPILIH HANYA DARI 0 SAMPAI 5,MASUKKAN KEMBALI MENU YANG DIPILIH !!!");
                    System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
                    Pilih = scan.nextLine();
            }
        } while (Pilih != "4" || Pilih != "3" || Pilih != "2" || Pilih != "1" || Pilih != "0");
    }

    public void MenuTransaksi() {
        //beres
        clear();
        Transaksi t = new Transaksi();
        System.out.println("====================================================");
        System.out.println("|                  MENU TRANSAKSI                  |");
        System.out.println("====================================================");
        System.out.println("| 1. TAMPIL DATA PEMINJAMAN                        |");
        System.out.println("| 2. TAMBAH DATA PEMINJAMAN                        |");
        System.out.println("| 3. TAMBAH DATA PENGEMBALIAN                      |");
        System.out.println("| 4. KOLEKSI HILANG                                |");
        System.out.println("====================================================");
        System.out.println("| 0. KEMBALI KE MENU UTAMA                         |");
        System.out.println("====================================================");
        System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
        Scanner scan = new Scanner(System.in);
        String Pilih;
        Pilih = scan.nextLine();
        do {
            switch (Pilih) {
                case "0":
                    MenuUtamaAdmin();
                    break;
                case "1":
                    t.tampilPeminjaman();
                    break;
                case "2":
                    t.inputPeminjaman();
                    break;
                case "3":
                    t.simpanPengembalian();
                    break;
                case "4":
                    MenuUtamaKoleksiRusak();
                    break;
                default:
                    System.out.println("MENU YANG DIPILIH HANYA DARI 0 SAMPAI 4,MASUKKAN KEMBALI MENU YANG DIPILIH !!!");
                    System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
                    Pilih = scan.nextLine();
            }
        } while (Pilih != "4" || Pilih != "3" || Pilih != "2" || Pilih != "1" || Pilih != "0");
    }

    public void MenuUtamaKoleksiRusak() {
        clear();
        System.out.println("====================================================");
        System.out.println("|                 MENU KOLEKSI HILANG              |");
        System.out.println("====================================================");
        System.out.println("| 1. PENGGANTIAN DENGAN BAYAR DENDA                |");
        System.out.println("| 2. PENGGANTIAN DENGAN BUKU YANG SAMA             |");
        System.out.println("====================================================");
        System.out.println("| 0. KEMBALI KE MENU UTAMA                         |");
        System.out.println("====================================================");
        Transaksi t = new Transaksi();
        Scanner scan = new Scanner(System.in);
        String Pilih;
        System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
        Pilih = scan.nextLine();
        do {
            switch (Pilih) {
                case "0":
                    MenuUtamaAdmin();
                    break;
                case "1":
                    t.BayarDenda();
                    break;
                case "2":
                    t.GantiKoleksi();
                    break;
                default:
                    System.out.println("MENU YANG DIPILIH HANYA DARI 0 SAMPAI 2,MASUKKAN KEMBALI MENU YANG DIPILIH !!!");
                    System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
                    Pilih = scan.nextLine();
            }
        } while (Pilih != "3" || Pilih != "2" || Pilih != "1" || Pilih != "0");

    }

    public void MenuUtamaAnggota() {
        clear();
        System.out.println("====================================================");
        System.out.println("|                    MENU ANGGOTA                  |");
        System.out.println("====================================================");
        System.out.println("| 1. TAMPIL ANGGOTA                                |");
        System.out.println("| 2. TAMBAH ANGGOTA                                |");
        System.out.println("| 3. UBAH STATUS ANGGOTA                           |");
        System.out.println("| 4. KETERANGAN BEBAS PINJAM                       |");
        System.out.println("| 5. CARI ANGGOTA BERDASARKAN KODE ANGGOTA         |");
        System.out.println("====================================================");
        System.out.println("| 0. KEMBALI KE MENU UTAMA                         |");
        System.out.println("====================================================");
        Anggota kh = new Anggota();
        Scanner scan = new Scanner(System.in);
        String Pilih;
        System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
        Pilih = scan.nextLine();
        do {
            switch (Pilih) {
                case "0":
                    MenuUtamaAdmin();
                    break;
                case "1":
                    kh.tampilAnggota();
                    break;
                case "2":
                    kh.tambahAnggota();
                    break;
                case "3":
                    kh.ubahStatus();
                case "4":
                    kh.BebasPinjamPilih();
                case "5":
                    kh.cariAnggota();
                default:
                    System.out.println("MENU YANG DIPILIH HANYA DARI 0 SAMPAI 5,MASUKKAN KEMBALI MENU YANG DIPILIH !!!");
                    System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
                    Pilih = scan.nextLine();
            }
        } while (Pilih != "3" || Pilih != "2" || Pilih != "1" || Pilih != "0");

    }

    public void MenuUtamaTampilan() {
        clear();
        System.out.println("====================================================");
        System.out.println("|                    MENU UTAMA                    |");
        System.out.println("====================================================");
        System.out.println("| 1. DATA ANGGOTA                                  |");
        System.out.println("| 2. DATA KOLEKSI                                  |");
        System.out.println("| 3. TRANSAKSI                                     |");
        System.out.println("| 4. LAPORAN                                       |");
        System.out.println("====================================================");
        System.out.println("| 0. KELUAR                                        |");
        System.out.println("====================================================");
        System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
    }

    public void MenuUtamaKeluar() {
        Scanner scan = new Scanner(System.in);
        String Pilih;
        do {
            System.out.print("APAKAH ANDA YAKIN AKAN KELUAR (Y/T) :");
            Pilih = scan.next().toUpperCase();
            switch (Pilih) {
                case "T":
                    System.out.println("");
                    MenuUtamaAdmin();
                    break;
                case "Y":
                    System.out.println("KELUAR");
                    clear();
                    System.exit(0);
                default:
                    System.out.println("MENU YANG DIPILIH HANYA Y DAN N,MASUKKAN KEMBALI MENU YANG DIPILIH !!!");
            }

        } while (Pilih == "T" || Pilih == "Y");
    }

    public void MenuUtamaPilih() {
        Scanner scan = new Scanner(System.in);
        String Pilih;
        Pilih = scan.nextLine();
        do {
            switch (Pilih) {
                case "0":
                    MenuUtamaKeluar();
                    break;
                case "1":
                    MenuUtamaAnggota();
                    break;
                case "2":
                    MenuKoleksi();
                    break;
                case "3":
                    MenuTransaksi();
                    break;
                case "4":
                    MenuLaporan();
                    break;
                default:
                    System.out.println("MENU YANG DIPILIH HANYA DARI 0 SAMPAI 5,MASUKKAN KEMBALI MENU YANG DIPILIH !!!");
                    System.out.format("| MASUKKAN MENU YANG DIPILIH : ");
                    Pilih = scan.nextLine();
                    break;
            }
        } while (Pilih != "5" || Pilih != "1" || Pilih != "2" || Pilih != "3" || Pilih != "4" || Pilih != "0");
    }

    public void MenuUtamaAdmin() {
        MenuUtamaTampilan();
        MenuUtamaPilih();
    }
}
