package com.felipe.centraldesk.api.dto;

public record ErroResponse(String mensagem) {
     public String getMensagem(){
        return mensagem;
    }
}
