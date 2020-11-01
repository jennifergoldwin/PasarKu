package Model;

public class Pasar {

    String Image;
    String Nama;
    String Alamat;
    String JlhToko;

    public Pasar(){
    }
    public Pasar(String Image,String Nama, String Alamat,String JlhToko){
        this.Image = Image;
        this.Nama = Nama;
        this.Alamat = Alamat;
        this.JlhToko = JlhToko;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        this.Nama = Nama;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        this.Alamat = alamat;
    }

    public String getJlhToko() {
        return JlhToko;
    }

    public void setJlhToko(String jlhToko) {
        this.JlhToko = jlhToko;
    }
}
