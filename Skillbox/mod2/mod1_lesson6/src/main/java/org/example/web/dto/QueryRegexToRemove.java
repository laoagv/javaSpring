package org.example.web.dto;

import javax.validation.constraints.NotEmpty;

public class QueryRegexToRemove {
    @NotEmpty
    private String query;
    public String getQuery(){
        return query;
    }
    public void setQuery(String query){
        this.query =query;
    }
}
