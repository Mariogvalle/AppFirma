package Configuracion;

public class Transacciones {
    //Nombre de la base de datos
    public static final String DBName = "PMSIGNATURES";

    //Creación de las tablas de las bases de datos.
    public static final String TableSignatures = "signatures";

    //Creacion de los campos de base de datos

    public static final String id = "id";
    public static final String descripcion = "descripcion";
    public static final String sign = "sign";

    // DDL Create
    public static final String CreateTableSignatures = "Create table "+ TableSignatures +"("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT, sign BLOB )";

    //DDL Drop
    public static final String DropTableSignatures = "DROP TABLE IF EXISTS "+ TableSignatures;

    //DML
    public static final String SelectAllSignatures = "SELECT * FROM " + TableSignatures;
}