package smart.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PollutionDataDto {

    private String commune;

    private String code_insee;

    private String licence;

    private String conditions_utilisation;

    private PollutionDataIndicesDto indices;

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

    public PollutionDataIndicesDto getIndices() {
        return indices;
    }

    public void setIndices(PollutionDataIndicesDto indices) {
        this.indices = indices;
    }
}

