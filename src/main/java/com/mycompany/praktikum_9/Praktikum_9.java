/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.praktikum_9;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Praktikum_9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Perpustakaan perpusUnila = new Perpustakaan("Smart Library Unila");
        perpusUnila.bukaPerpustakaan();
        
        boolean isRunning = true;
        
        System.out.println("========================================");
        System.out.println("    SELAMAT DATANG DI SMART LIBRARY!    ");
        System.out.println("========================================");
        
        // Memasukkan data awal (Dummy Data) melalui method Perpustakaan (Aggregation)
        try {
            perpusUnila.tambahKoleksi(new BukuCetak("Pembelajaran Java", "Suryani", 2023, 250));
            perpusUnila.tambahKoleksi(new EBook("Struktur Data", "Khatimah", 2022, 15));
            perpusUnila.tambahKoleksi(new Majalah("Tech Trends", "Admin", 2026, "Edisi Januari"));
            
            perpusUnila.tambahAnggota(new Anggota("2517051006", "Riska Febriani"));
            perpusUnila.tambahAnggota(new Anggota("2517051057", "Khairun Nisa"));
        } catch (Exception e) {
            System.out.println("Gagal memuat data awal: " + e.getMessage());
        }
        
        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Koleksi");
            System.out.println("2. Lihat Daftar Koleksi");
            System.out.println("3. Cari Koleksi");
            System.out.println("4. Manajemen Anggota");
            System.out.println("5. Peminjaman (Demo Association)");
            System.out.println("6. Keluar");
            System.out.print("Pilih Menu: 1-6: ");
            
            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();
                
                switch (pilihan) {
                    case 1 -> {
                        System.out.println("\n-- Pilih Jenis Koleksi --");
                        System.out.println("1. Buku Cetak Fisik");
                        System.out.println("2. E-Book Digital");
                        System.out.println("3. Majalah");
                        System.out.print("Pilihan (1/2/3): ");
                        
                        int jenis = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Masukkan Judul: ");
                        String judul = scanner.nextLine();
                        
                        System.out.print("Masukkan Pengarang/Penerbit: ");
                        String pengarang = scanner.nextLine();
                        
                        System.out.print("Masukkan Tahun Terbit: ");
                        int tahun = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (jenis == 1) {
                            System.out.print("Masukkan Jumlah Halaman: ");
                            int halaman = scanner.nextInt();
                            scanner.nextLine();
                            perpusUnila.tambahKoleksi(new BukuCetak(judul, pengarang, tahun, halaman));
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Ukuran File (MB): ");
                            int ukuran = scanner.nextInt();
                            scanner.nextLine();
                            perpusUnila.tambahKoleksi(new EBook(judul, pengarang, tahun, ukuran));
                        } else if (jenis == 3) {
                            System.out.print("Masukkan Edisi/Bulan: ");
                            String edisi = scanner.nextLine();
                            perpusUnila.tambahKoleksi(new Majalah(judul, pengarang, tahun, edisi));
                        }
                        
                        System.out.println("Sukses! Koleksi berhasil ditambahkan.");
                    }
                    case 2 -> {
                        System.out.println("\n--- Daftar Koleksi di Perpustakaan ---");
                        perpusUnila.lihatSemuaKoleksi();
                    }
                    case 3 -> {
                        System.out.println("\n-- Fitur Cari Koleksi --");
                        System.out.print("Masukkan Judul: ");
                        String kataKunci = scanner.nextLine();
                        
                        try {
                            perpusUnila.cariKoleksi(kataKunci);
                        } catch (KoleksiTidakDitemukanException e) {
                            System.out.println("[Peringatan] " + e.getMessage());
                        } 
                    }
                    case 4 -> {
                        System.out.println("\n-- Kelola Anggota --");
                        System.out.println("1. Tambah Anggota");
                        System.out.println("2. Cari Anggota");
                        System.out.println("3. Lihat Semua");
                        System.out.print("Pilih: ");
                        int menuA = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (menuA == 1) {
                            System.out.print("Masukkan NPM: ");
                            String npm = scanner.nextLine();
                            System.out.print("Masukkan Nama: ");
                            String nama = scanner.nextLine();
                            
                            perpusUnila.tambahAnggota(new Anggota(npm, nama));
                        } else if (menuA == 2) {
                            System.out.print("NPM yang dicari: ");
                            perpusUnila.cariAnggota(scanner.nextLine());
                        } else if (menuA == 3) {
                            perpusUnila.lihatSemuaAnggota();
                        }
                    }
                    case 5 -> {
                        System.out.println("\n--- Sistem Peminjaman (Asosiasi) ---");
                        System.out.print("Masukkan NPM Peminjam: ");
                        String inputNpm = scanner.nextLine();

                        Anggota peminjam = perpusUnila.getAnggota(inputNpm);
                        if (peminjam == null) {
                            System.out.println("Gagal: Anggota belum terdaftar!");
                        } else if (perpusUnila.getJumlahKoleksi() == 0) {
                            System.out.println("Gagal: Belum ada koleksi yang bisa dipinjam.");
                        } else {
                            perpusUnila.lihatSemuaKoleksi();
                            System.out.print("Pilih Nomor Koleksi yg ingin dipinjam: ");
                            int noKoleksi = scanner.nextInt() - 1;
                            scanner.nextLine();

                            if (noKoleksi >= 0 && noKoleksi < perpusUnila.getJumlahKoleksi()) {
                                Koleksi itemPinjam = perpusUnila.getKoleksiInt(noKoleksi);
                                
                                TransaksiPeminjaman.prosesPinjam(peminjam, itemPinjam);
                            } else {
                                System.out.println("Nomor koleksi tidak valid!");
                            }
                        }
                    }
                    case 6 -> {
                        System.out.println("Terima kasih telah menggunakan Smart Library!");
                        isRunning = false;
                    }
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[FATAL ERROR] Input tidak valid! Anda harus memasukkan angka.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n[ERROR SISTEM] Terjadi kegagalan: " + e.getMessage());
            }
        }
        scanner.close();
    }
}