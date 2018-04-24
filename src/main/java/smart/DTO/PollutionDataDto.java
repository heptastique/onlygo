package smart.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PollutionDataDto {

    private String commune;

    private String code_insee;

    private String licence;

    private String conditions_utilisation;

    private Indices indices;

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getCode_insee() {
        return code_insee;
    }

    public void setCode_insee(String code_insee) {
        this.code_insee = code_insee;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getConditions_utilisation() {
        return conditions_utilisation;
    }

    public void setConditions_utilisation(String conditions_utilisation) {
        this.conditions_utilisation = conditions_utilisation;
    }

    public Indices getIndices() {
        return indices;
    }

    public void setIndices(Indices indices) {
        this.indices = indices;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Indices {
    private int total;

    private int per_page;

    private int current_page;

    private int last_page;

    private String next_page_url;

    private String prev_page_url;

    private int from;

    private int to;

    private List<Elem> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public List<Elem> getData() {
        return data;
    }

    public void setData(List<Elem> data) {
        this.data = data;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Elem {
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
