package co.mobile.dejavu.retrofitexampletest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BodyWishList {

    @SerializedName("appKey")
    @Expose
    private String appKey;
    @SerializedName("documentoUsuario")
    @Expose
    private String documentoUsuario;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }


    public BodyWishList(String appKey, String documentoUsuario) {
        this.appKey = appKey;
        this.documentoUsuario = documentoUsuario;
    }
}