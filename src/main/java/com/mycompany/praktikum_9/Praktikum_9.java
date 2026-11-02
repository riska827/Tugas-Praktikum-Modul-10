/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.praktikum_9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Praktikum_9 {
    
public static void cariKoleksi(String judul, ArrayList<Koleksi> daftarKoleksi) throws KoleksiTidakDitemukanException {
        System.out.println("Mencari Koleksi dengan judul: " + judul);
        boolean ditemukan = false;
        
        for (int i = 0; i < daftarKoleksi.size(); i++) {
            if (daftarKoleksi.get(i).getJudul().equalsIgnoreCase(judul)) {
                daftarKoleksi.get(i).tampilkanInfo();
                ditemukan = true;
            }
        }
        
        if (!ditemukan) {
            throw new KoleksiTidakDitemukanException("Gagal: Koleksi dengan judul '" + judul + "' tidak terdaftar di sistem!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Koleksi> daftarKoleksi = new ArrayList<>();
        HashMap<String, String> dataAnggota = new HashMap<>();
        
        boolean isRunning = true;
        
        System.out.println("========================================");
        System.out.println("    SELAMAT DATANG DI SMART LIBRARY!    ");
        System.out.println("========================================");
        
        try {
            daftarKoleksi.add(new BukuCetak("Pembelajaran Java", "Suryani", 2023, 250));
            daftarKoleksi.add(new EBook("Struktur Data", "Khatimah", 2022, 15));
            daftarKoleksi.add(new Majalah("Tech Trends", "Admin", 2026, "Edisi Januari"));
            
            dataAnggota.put("2517051006", "Riska Febriani");
            dataAnggota.put("2517051057", "Khairun Nisa");
        } catch (Exception e) {
            System.out.println("Gagal memuat data awal: " + e.getMessage());
        }
        
        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Koleksi");
            System.out.println("2. Lihat Daftar Koleksi");
            System.out.println("3. Cari Koleksi (Demo Custom Exception)");
            System.out.println("4. Manajemen Anggota (Demo HashMap)");
            System.out.println("5. Keluar");
            System.out.print("Pilih Menu: 1-5: ");
            
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
                        String judulBaru = scanner.nextLine();
                        
                        System.out.print("Masukkan Pengarang: ");
                        String pengarangBaru = scanner.nextLine();
                        
                        System.out.print("Masukkan Tahun Terbit: ");
                        int tahunBaru = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (jenis == 1) {
                            System.out.print("Masukkan Jumlah Halaman: ");
                            int halaman = scanner.nextInt();
                            scanner.nextLine();
                            daftarKoleksi.add(new BukuCetak(judulBaru, pengarangBaru, tahunBaru, halaman));
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Ukuran File (MB): ");
                            int ukuran = scanner.nextInt();
                            scanner.nextLine();
                            daftarKoleksi.add(new EBook(judulBaru, pengarangBaru, tahunBaru, ukuran));
                        } else if (jenis == 3) {
                            System.out.print("Masukkan Edisi/Bulan: ");
                            String edisi = scanner.nextLine();
                            daftarKoleksi.add(new Majalah(judulBaru, pengarangBaru, tahunBaru, edisi));
                        }
                        
                        System.out.println("Sukses! Koleksi berhasil ditambahkan. (Total Koleksi: " + daftarKoleksi.size() + ")");
                    }
                    case 2 -> {
                        System.out.println("\n--- Daftar Koleksi di Perpustakaan ---");
                        if (daftarKoleksi.isEmpty()) {
                            System.out.println("Belum ada koleksi yang tersimpan.");
                        } else {
                            for (int i = 0; i < daftarKoleksi.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                daftarKoleksi.get(i).tampilkanInfo();
                            }
                        }
                    }
                    case 3 -> {
                        System.out.println("\n-- Fitur Cari Koleksi --");
                        System.out.print("Masukkan Judul: ");
                        String kataKunci = scanner.nextLine();
                        
                        try {
                            cariKoleksi(kataKunci, daftarKoleksi);
                        } catch (KoleksiTidakDitemukanException e) {
                            System.out.println("[Peringatan] " + e.getMessage());
                        } finally {
                            System.out.println("-> Sesi pencarian selesai dieksekusi.");
                        }
                    }
                    case 4 -> {
                        System.out.println("\n-- Kelola Anggota (HashMap) --");
                        System.out.println("1. Tambah Anggota Baru");
                        System.out.println("2. Cari Anggota via NPM");
                        System.out.println("3. Tampilkan Semua Anggota");
                        System.out.print("Pilih: ");
                        int menuAnggota = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (menuAnggota == 1) {
                            System.out.print("Masukkan NPM (sebagai Key): ");
                            String npm = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap (sebagai Value): ");
                            String nama = scanner.nextLine();
                            
                            dataAnggota.put(npm, nama);
                            System.out.println("Berhasil menambahkan anggota!");
                        } else if (menuAnggota == 2) {
                            System.out.print("Masukkan NPM yang dicari: ");
                            String searchNpm = scanner.nextLine();
                            
                            if (dataAnggota.containsKey(searchNpm)) {
                                System.out.println("Ditemukan! Nama: " + dataAnggota.get(searchNpm));
                            } else {
                                System.out.println("Anggota dengan NPM tersebut tidak terdaftar.");
                            }
                        } else if (menuAnggota == 3) {
                            System.out.println("Total Anggota Terdaftar: " + dataAnggota.size());
                            for (String keyNpm : dataAnggota.keySet()) {
                                System.out.println("- NPM: " + keyNpm + " | Nama: " + dataAnggota.get(keyNpm));
                            }
                        }
                    }
                    case 5 -> {
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