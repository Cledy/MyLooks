package iberotec.edu.pe.mylooks.Item;

/**
 * Created by Margot on 18/12/2017.
 */

public class ParteArriba {

    private int id;
    private String name;
    private String tipo;
    private byte[] image;

    public ParteArriba(String name, String tipo, byte[] image, int id) {
        this.name = name;
        this.tipo = tipo;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
