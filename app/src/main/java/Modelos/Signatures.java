package Modelos;

public class Signatures {
    private Integer id;
    private String descripcion;
    private byte[] sign;

    //constructores

    public Signatures() {
    }

    public Signatures(Integer id, String descripcion, byte[] sign) {
        this.id = id;
        this.descripcion = descripcion;
        this.sign = sign;
    }

    //getter and setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getSign() {
        return sign;
    }

    public void setSign(byte[] sign) {
        this.sign = sign;
    }
}
