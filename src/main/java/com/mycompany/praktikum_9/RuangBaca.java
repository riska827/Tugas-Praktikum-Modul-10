/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.praktikum_9;

public class RuangBaca {
    private String namaRuangan;
    private int kapasitasKursi;
    
    public RuangBaca(String namaRuangan, int kapasitasKursi) {
        this.namaRuangan = namaRuangan;
        this.kapasitasKursi = kapasitasKursi;
    }
    
    public void bukaRuangan() {
        System.out.println("-> [" + namaRuangan + "] dibuka. Kapasitas: " + kapasitasKursi + " kursi.");
    }
}
