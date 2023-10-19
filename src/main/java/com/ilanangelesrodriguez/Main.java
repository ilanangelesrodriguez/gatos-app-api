package com.ilanangelesrodriguez;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int menu_option = -1;
        String[] botones = {"1. Ver gatos", "2. Ver favoritos", "3. Salir"};

        do {
            // menu principal
            String option = (String) JOptionPane.showInputDialog(null,"Gatos Java","Menu Principal",
                    JOptionPane.INFORMATION_MESSAGE, null, botones,botones[0]);

            // Validamos que opcion seleciona el usuario
            for (int i = 0; i < botones.length; i++) {
                if(option.equals(botones[i])){
                    menu_option = i;
                }
            }

            switch (menu_option){
                case 0:
                    GatoService.verGato();
                    break;
                case 1:
                    Gato gato = new Gato();
                    GatoService.verFavorito(gato.getApikey());
                default:
                    break;

            }

        } while (menu_option != 1);



        System.out.println("Hello world!");
    }
}