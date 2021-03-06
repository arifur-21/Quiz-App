package com.example.quizapp.models;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class WithdrawRequest {
    private String userId;
    private String emailAddress;
    private String requesteBy;

    public WithdrawRequest() {
    }

    public WithdrawRequest(String userId, String emailAddress, String requesteBy) {
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.requesteBy = requesteBy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRequesteBy() {
        return requesteBy;
    }

    public void setRequesteBy(String requesteBy) {
        this.requesteBy = requesteBy;
    }

    @ServerTimestamp
    private Date created;
}
