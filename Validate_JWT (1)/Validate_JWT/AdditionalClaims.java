//package au.com.cuscal.domain.web.rest.validator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AdditionalClaims {
    @JsonProperty("scope")
    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCdr_arrangement_id() {
        return cdr_arrangement_id;
    }

    public void setCdr_arrangement_id(String cdr_arrangement_id) {
        this.cdr_arrangement_id = cdr_arrangement_id;
    }

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("cdr_arrangement_id")
    private String cdr_arrangement_id;



}
