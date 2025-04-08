package com.ecodeuo.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtaudProblema {

    public static String ecoResonante(List<String> palabras, int k) {
        // 1. Crear una estructura para contar ocurrencias
        // 2. Recorrer la lista y contar cuántas veces aparece cada palabra
        // 3. Verificar si alguna palabra aparece exactamente k veces
        // 4. Si hay varias, decidir cuál devolver (por orden de aparición)
        // 5. Retornar la palabra que cumpla la condición o un valor por defecto (ej. null o "")
        var frase = "sin coincidencias";
        int times = k;
        Map<String, Integer> contador = new HashMap<>();

        for(String palabra: palabras){
            contador.put(palabra, contador.getOrDefault(palabra, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : contador.entrySet()){
            if(entry.getValue() == times){
                if(prueba(entry, palabras)){
                frase = entry.getKey();}
            }

        }


        return frase; // placeholder
    }

    private static Boolean prueba(Map.Entry<String, Integer> entry, List<String> palabras) {

        ArrayList<Integer> contOfIndex = new ArrayList<>();
        for (int i = 0; i < palabras.size(); i++){
            var palabra = palabras.get(i);
            if(palabra.equals(entry.getKey())){ // palabra de palabras igual a la llave?
                    contOfIndex.add(i);
            }
        }
        var medida = contOfIndex.size();
        ArrayList<Integer> iguales = new ArrayList<>();
        for (int i = medida - 1; i > 0; i--) {
            var resta = (contOfIndex.get(i)) - (contOfIndex.get(i - 1));
            iguales.add(resta);
        }
        var resultado = true;
        for (int i = 0; i < iguales.size() - 1; i++) {
            if(!iguales.get(i).equals(iguales.get(i + 1))){
                resultado = false;
                break;
            }
        }
        return resultado;
    }
}
