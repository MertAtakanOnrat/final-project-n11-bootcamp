package com.mertoatakan.restaurantservice.errormessage;

import com.mertoatakan.restaurantservice.general.BaseErrorMessage;

public enum SolrClientErrorMessage implements BaseErrorMessage {
    SOLR_CLIENT_ERROR_MESSAGE("Solr client error occurred!");

    private final String message;

    SolrClientErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
