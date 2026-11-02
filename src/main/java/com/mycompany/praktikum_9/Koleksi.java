package com.mycompany.praktikum_9;

public abstract class Koleksi {
    protected String judul;
    protected String pengarang;
    protected int tahunTerbit;
    
    public static int totalKoleksiBerhasilDibuat = 0;
    
    public Koleksi(String judul, String pengarang, int tahunTerbit) throws Exception {
        if (tahunTerbit <= 0) {
            throw new Exception("Tahun terbit tidak valid (tidak boleh nol atau negatif)!");
        }
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        totalKoleksiBerhasilDibuat++;
    }
    
    public String getJudul() { return this.judul; }
    public int getTahunTerbit() { return this.tahunTerbit; }
    
    public void tampilkanInfo() {
        System.out.printf("Judul: %-20s | Pengarang/Penerbit: %-15s | Tahun: %d%n", 
                          this.judul, this.pengarang, this.tahunTerbit);
    }
    
    public void caraPinjam() {
        System.out.println("Barang dipinjam secara fisik ke meja administrasi.");
    }

    public abstract void hitungDendaKeterlambatan();
}
