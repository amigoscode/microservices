package com.amigoscode.clients.apimanagement;


import java.util.Objects;

public class ApiManagementResponse {

    private final boolean isAuthorized;

    public ApiManagementResponse(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    @Override
    public String toString() {
        return "ApiManagementResponse{" +
                "isAuthorized=" + isAuthorized +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiManagementResponse that = (ApiManagementResponse) o;
        return isAuthorized == that.isAuthorized;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAuthorized);
    }
}
