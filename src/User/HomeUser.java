/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.util.Scanner;

/**
 *
 * @author Fauzanlh
 */
public class HomeUser {

    public void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println("" + E);
        }
    }

    public void CariKoleksi() {
        clear();
        KoleksiUser k = new KoleksiUser();
        Scanner scan = new Scanner(System.in);
        System.out.println("====================================================");
        System.out.println("|                 CARI DATA KOLEKSI                |");
        System.out.println("====================================================");
        System.out.println("| 1. CARI KOLEKSI BERDASATKAN JUDUL                |");
        System.out.println("| 2. CARI KOLEKSI BERDASATKAN PENGARANG            |");
        System.out.println("| 3. CARI KOLEKSI BERDASATKAN PENERBIT             |");
        System.out.println("| 4. CARI KOLEKSI BERDASATKAN KATEGORI             |");
        System.out.println("====================================================");
        System.out.println("| 0. KEMBALI KE MENU UTAMA                         |");
        System.out.println("====================================================");
        System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
        String Pilih;
        Pilih = scan.nextLine();
        do {
            switch (Pilih) {
                case "0":
                    MenuUtamaUser();
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
                default:
                    System.out.println("MENU YANG DIPILIH HANYA DARI 3 SAMPAI 4,MASUKKAN KEMBALI MENU YANG DIPILIH !!!");
                    System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
                    Pilih = scan.nextLine();
            }
        } while (Pilih != "3" || Pilih != "2" || Pilih != "1" || Pilih != "0");
    }

    public void MenuUtamaTampilan() {
        clear();
        System.out.println("====================================================");
        System.out.println("|                MENU UTAMA USER                   |");
        System.out.println("====================================================");
        System.out.println("| 1. TAMPIL DATA KOLEKSI                           |");
        System.out.println("| 2. CARI DATA KOLEKSI                             |");
        System.out.println("====================================================");
        System.out.println("| 0. KEMBALI KE MENU UTAMA                         |");
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
                    MenuUtamaUser();
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
        KoleksiUser k = new KoleksiUser();
        Scanner scan = new Scanner(System.in);
        String Pilih;
        Pilih = scan.nextLine();
        do {
            switch (Pilih) {
                case "0":
                    MenuUtamaKeluar();
                    break;
                case "1":
                    k.tampilKoleksi();
                    break;
                case "2":
                    CariKoleksi();
                    break;
                default:
                    System.out.println("MENU YANG DIPILIH HANYA DARI 0 SAMPAI 4,MASUKKAN KEMBALI MENU YANG DIPILIH !!!");
                    System.out.print("| MASUKKAN MENU YANG DIPILIH : ");
                    Pilih = scan.nextLine();
            }
        } while (Pilih != "2" || Pilih != "1" || Pilih != "0");
    }

    public void MenuUtamaUser() {
        MenuUtamaTampilan();
        MenuUtamaPilih();
    }
}
