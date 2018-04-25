package smart.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PollutionDataElemDto {
    private String date;

    private String valeur;

    private String couleur_html;

    private String qualificatif;

    private String type_valeur;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getCouleur_html() {
        return couleur_html;
    }

    public void setCouleur_html(String couleur_html) {
        this.couleur_html = couleur_html;
    }

    public String getQualificatif() {
        return qualificatif;
    }

    public void setQualificatif(String qualificatif) {
        this.qualificatif = qualificatif;
    }

    public String getType_valeur() {
        return type_valeur;
    }

    public void setType_valeur(String type_valeur) {
        this.type_valeur = type_valeur;
    }
}
