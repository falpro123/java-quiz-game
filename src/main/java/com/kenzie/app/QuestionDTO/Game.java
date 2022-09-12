package com.kenzie.app.QuestionDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {
    @JsonProperty("canon")
    private boolean canon;
    @JsonProperty("aired")
    private String aired;

    public boolean getCanon() {
        return canon;
    }

    public void setCanon(boolean canon) {
        this.canon = canon;
    }

    public String getAired() {
        return aired;
    }

    public void setAired(String aired) {
        this.aired = aired;
    }
}
