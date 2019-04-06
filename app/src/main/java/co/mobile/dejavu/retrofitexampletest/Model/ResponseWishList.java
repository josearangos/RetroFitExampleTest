package  co.mobile.dejavu.retrofitexampletest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseWishList {

    @SerializedName("errores")
    @Expose
    private Boolean errores;
    @SerializedName("titlenos")
    @Expose
    private String titlenos;
    @SerializedName("respuesta")
    @Expose
    private String respuesta;






    public Boolean getErrores() {
        return errores;
    }

    public void setErrores(Boolean errores) {
        this.errores = errores;
    }

    public String getTitlenos() {
        return titlenos;
    }

    public void setTitlenos(String titlenos) {
        this.titlenos = titlenos;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}