/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.client.util;

import java.util.Base64;

/**
 *
 * @author Junior
 */
public class ConverterBase64 {
    
    public static String encodarBase64(String texto){
        String encodeToString = Base64.getEncoder().encodeToString(texto.getBytes());
        return encodeToString;
    }
    
}
