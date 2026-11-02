package com.mycompany.praktikum_9;

public class Majalah extends Koleksi implements DapatDipinjam {
    private String edisi;

    public Majalah(String judul, String pengarang, int tahunTerbit, String edisi) throws Exception {
        super(judul, pengarang, tahunTerbit);
        if (edisi.isEmpty()) throw new Exception("Edisi harus punya inisialisasi!");
        this.edisi = edisi;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[Majalah]   Judul: %-15s | Penerbit: %-10s | Tahun: %d | Edisi: %s%n", 
                          this.judul, this.pengarang, this.tahunTerbit, this.edisi);
    }

    @Override
    public void caraPinjam() {
        System.out.println("-> Info Pinjam: Majalah terbitan terbaru hanya dapat dibaca di ruang baca, tidak untuk dibawa pulang.");
    }

    @Override
    public void hitungDendaKeterlambatan() {
        System.out.println("-> Aturan Denda: Rp 5.000 / Hari (Majalah sangat dilarang dibawa pulang!).");
    }

    @Override
    public void prosesPinjamFisik() {
        System.out.println("-> [PROSES PINJAM] Anggota menyerahkan KTM untuk membaca Majalah fisik di Ruang Baca.");
    }
}
