package com.aplicacao.api.validator;

public class NormalizaString {
    public static String primeiraLetraMaiusculo(String nome) {
        String normalized = nome.trim().replaceAll("\\\\s+", " ").toLowerCase();


        String[] palavras = normalized.split(" ");
        StringBuilder nomeTratado = new StringBuilder();
        for(String palavra : palavras){
            if(palavra.length() > 0){
                nomeTratado.append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1))
                        .append(" ");

            }
        }
        return nomeTratado.toString().trim();
    }

}
