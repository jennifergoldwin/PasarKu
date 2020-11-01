package Model;

import android.content.SharedPreferences;

public class Produk {
    String Image;
    String Harga;
    String Nama;
    String Rating;
    String Kategori;

    public Produk(){

    }
    public Produk(String image, String harga, String nama, String rating, String kategori) {
        Image = image;
        Harga = harga;
        Nama = nama;
        Rating = rating;
        Kategori = kategori;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getKategori() {
        return Kategori;
    }

    public void setKategori(String kategori) {
        Kategori = kategori;
    }
}
