package com.solvd.domain.builder;

import com.solvd.domain.Client;

public class ClientBuilder extends BaseEntityBuilder<ClientBuilder, Client> {
    private String cuit;
    private String businessName;
    private String address;
    private String phone;
    private String email;

    public ClientBuilder cuit(String cuit) {
        this.cuit = cuit;
        return this;
    }

    public ClientBuilder businessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public ClientBuilder address(String address) {
        this.address = address;
        return this;
    }

    public ClientBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public ClientBuilder email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public Client build() {
        Client client = new Client();
        client.setId(id);
        client.setCuit(cuit);
        client.setBusinessName(businessName);
        client.setAddress(address);
        client.setPhone(phone);
        client.setEmail(email);
        return client;
    }
}
