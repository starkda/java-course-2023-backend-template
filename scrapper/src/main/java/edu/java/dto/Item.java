package edu.java.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private Owner owner;
    @JsonProperty("is_accepted")
    private boolean isAccepted;
    private int score;
    @JsonProperty("last_activity_date")
    private long lastActivityDate;
    @JsonProperty("creation_date")
    private long creationDate;
    @JsonProperty("last_edit_date")
    private Long lastEditDate;
    @JsonProperty("answer_id")
    private int answerId;
    @JsonProperty("question_id")
    private int questionId;
    @JsonProperty("content_license")
    private String contentLicense;
}
