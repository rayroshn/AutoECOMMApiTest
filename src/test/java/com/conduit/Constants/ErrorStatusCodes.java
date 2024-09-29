package com.conduit.Constants;

public enum ErrorStatusCodes {



    NOCONTENT_ERROR_STATUS_204(204)
   , NOTFOUND_ERROR_STATUS_404(404),
    OK_STATUS_200(200);

    private final int statusCode;

    ErrorStatusCodes(int statusCode) {

        this.statusCode =statusCode;
    }

    public int getStatusCode()
    {
        return statusCode;
    }
}
