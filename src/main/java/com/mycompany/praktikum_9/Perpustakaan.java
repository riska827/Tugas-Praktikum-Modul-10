/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.praktikum_9;

import java.util.ArrayList;
import java.util.HashMap;

public class Perpustakaan {
    private String namaPerpus;
    private RuangBaca ruangBacaUtama; // Composition
    private ArrayList<Koleksi> daftarKoleksi; // Aggregation
    private HashMap<String, Anggota> dataAnggota; // Aggregation

    public Perpustakaan(String namaPerpus) {
        this.namaPerpus = namaPerpus;
        // Composition: RuangBaca diciptakan di dalam constructor Perpustakaan
        this.ruangBacaUtama = new RuangBaca("Ruang Baca Utama", 50);
        this.daftarKoleksi = new ArrayList<>();
        this.dataAnggota = new HashMap<>();
    }

    public void bukaPerpustakaan() {
        System.out.println("\n*** Menginisialisasi sistem " + this.namaPerpus + " ***");
        ruangBacaUtama.bukaRuangan();
    }

    public void tambahKoleksi(Koleksi k) {
        daftarKoleksi.add(k);
        System.out.println("Sukses! Koleksi ditambahkan. (Total Koleksi: " + daftarKoleksi.size() + ")");
    }

    public void tambahAnggota(Anggota a) {
        dataAnggota.put(a.getNpm(), a);
        System.out.println("Berhasil menambahkan anggota!");
    }

    public void lihatSemuaKoleksi() {
        if (daftarKoleksi.isEmpty()) {
            System.out.println("Belum ada koleksi yang tersimpan.");
            return;
        }
        for (int i = 0; i < daftarKoleksi.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarKoleksi.get(i).tampilkanInfo();
        }
    }
    
    public void cariKoleksi(String judul) throws KoleksiTidakDitemukanException {
        System.out.println("Mencari Koleksi dengan judul: " + judul);
        boolean ditemukan = false;

        for (Koleksi k : daftarKoleksi) {
            if (k.getJudul().equalsIgnoreCase(judul)) {
                k.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            throw new KoleksiTidakDitemukanException("Gagal: Koleksi dengan judul '" + judul + "' tidak terdaftar.");
        }
    }

    public void cariAnggota(String npm) {
        if (dataAnggota.containsKey(npm)) {
            System.out.println("Ditemukan! Nama: " + dataAnggota.get(npm).getNama());
        } else {
            System.out.println("Anggota dengan NPM tersebut tidak terdaftar.");
        }
    }

    public void lihatSemuaAnggota() {
        System.out.println("Total Anggota Terdaftar: " + dataAnggota.size());
        for (Anggota a : dataAnggota.values()) {
            System.out.println("- NPM: " + a.getNpm() + " | Nama: " + a.getNama());
        }
    }

    public Anggota getAnggota(String npm) { return dataAnggota.get(npm);}
    public Koleksi getKoleksiInt(int index) { return daftarKoleksi.get(index);}
    public int getJumlahKoleksi() { return daftarKoleksi.size();}
}
