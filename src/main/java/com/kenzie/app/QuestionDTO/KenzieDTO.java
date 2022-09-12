package com.kenzie.app.QuestionDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KenzieDTO {

    @JsonProperty("clues")
    private List<Clues> clues;

    public List<Clues> getClues() {
        return clues;
    }

    public void setClues(List<Clues> clues) {
        this.clues = clues;
    }
}
