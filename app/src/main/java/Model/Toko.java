package Model;

public class Toko {
    String Image;
    String ImagePenjual;
    String Kategori;
    String Nama;
    String Penilaian;
    String Penjual;

    public Toko(){

    }
    public Toko(String nama,String image, String imagePenjual, String kategori, String penilaian, String penjual) {
        Nama = nama;
        Image = image;
        ImagePenjual = imagePenjual;
        Kategori = kategori;
        Penilaian = penilaian;
        Penjual = penjual;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImagePenjual() {
        return ImagePenjual;
    }

    public void setImagePenjual(String imagePenjual) {
        ImagePenjual = imagePenjual;
    }

    public String getKategori() {
        return Kategori;
    }

    public void setKategori(String kategori) {
        Kategori = kategori;
    }

    public String getPenilaian() {
        return Penilaian;
    }

    public void setPenilaian(String penilaian) {
        Penilaian = penilaian;
    }

    public String getPenjual() {
        return Penjual;
    }

    public void setPenjual(String penjual) {
        Penjual = penjual;
    }
}
