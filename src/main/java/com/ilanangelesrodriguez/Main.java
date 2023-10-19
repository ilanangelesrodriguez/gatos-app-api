package com.ilanangelesrodriguez;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int menu_option = -1;
        String[] botones = {"1. Ver gatos", "2. Salir"};

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
        } while (menu_option != 1);

        switch (menu_option){
            case 0:
                break;
            default:
                break;

        }

        System.out.println("Hello world!");
    }
}