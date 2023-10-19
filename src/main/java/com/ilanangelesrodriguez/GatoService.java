package com.ilanangelesrodriguez;

import com.google.gson.Gson;
import okhttp3.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static java.lang.StringTemplate.STR;

public class GatoService {
    public static void verGato() throws IOException {
        // 1. vamos a traer datos de la API
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();

        // Cortar corchetes
        responseJson = responseJson.substring(1,responseJson.length());
        responseJson = responseJson.substring(0,responseJson.length()-1);

        // objeto de clase GSON
        Gson gson = new Gson();
        Gato gato = gson.fromJson(responseJson, Gato.class);

        // redimensionar
        Image image = null;

        try {
            URL url = new URL(gato.getUrl());
            image = ImageIO.read(url);

            ImageIcon fondoGato = new ImageIcon(image);
            if(fondoGato.getIconWidth() > 800){
                // Redimensionamos
                Image fondo = fondoGato.getImage();
                Image modificada  = fondo.getScaledInstance(800, 600,
                        Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }

            String menu = STR."""
                    Opciones:
                    1. Ver otra imagen.
                    2. Favorito.
                    3. Volver al menu.
                    """;
            String[] botones = {"Ver otra imagen", "Favorito", "Volver"};
            String idGato = gato.getId();
            String option = (String) JOptionPane.showInputDialog(null, menu, idGato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

            int seleccion = -1;

            for (int i = 0; i < botones.length; i++) {
                if(option.equals(botones[i])){
                    seleccion = i;
                }
            }

            switch (seleccion){
                case 0:
                    verGato();
                    break;
                case 1:
                    favoritoGato(gato);
                    break;
                default:
                    break;
            }

        } catch (IOException e){
            System.out.println(e);
        };
    }

    public static void favoritoGato(Gato gato){

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\": \"bpg\"\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", "live_12uBcrcKucmZQIPAZyBWWlMpacyah9xoh4vN32Sp6zZCyrhFDfUHWjtxtH5Uuarw")
                    .build();
            Response response = client.newCall(request).execute();

        } catch (IOException e){
            System.out.println(e);
        }

    }

    public static void verFavorito(String apikey) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", "live_12uBcrcKucmZQIPAZyBWWlMpacyah9xoh4vN32Sp6zZCyrhFDfUHWjtxtH5Uuarw")
                .build();
        Response response = client.newCall(request).execute();

        String responseJSON = response.body().string();

        // crear el objeto json
        Gson gson = new Gson();

        GatoFav[] gatosArray = gson.fromJson(responseJSON, GatoFav[].class);

        if (gatosArray.length > 0){
            int min = 1;
            int max = gatosArray.length;
            int random = (int) (Math.random()*((max-min)+1)) + min;
            int index = random - 1;

            GatoFav gatoFav = gatosArray[index];

            Image image = null;

            try {
                URL url = new URL(gatoFav.image.getUrl());
                image = ImageIO.read(url);

                ImageIcon fondoGato = new ImageIcon(image);
                if(fondoGato.getIconWidth() > 800){
                    // Redimensionamos
                    Image fondo = fondoGato.getImage();
                    Image modificada  = fondo.getScaledInstance(800, 600,
                            Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(modificada);
                }

                String menu = STR."""
                    Opciones:
                    1. Ver otra imagen.
                    2. Eliminar Favorito.
                    3. Volver al menu.
                    """;
                String[] botones = {"Ver otra imagen", "Eliminar Favorito", "Volver al menu"};
                String idGato = gatoFav.getId();
                String option = (String) JOptionPane.showInputDialog(null, menu, idGato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

                int seleccion = -1;

                for (int i = 0; i < botones.length; i++) {
                    if(option.equals(botones[i])){
                        seleccion = i;
                    }
                }

                switch (seleccion){
                    case 0:
                        verFavorito(apikey);
                        break;
                    case 1:
                        eliminarFavorito(gatoFav);
                        break;
                    default:
                        break;
                }

            } catch (IOException e){
                System.out.println(e);
            };

        }

    }

    public static void eliminarFavorito(GatoFav gatoFav){

        try {

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/"+gatoFav.getId()+"")
                    .delete(null)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", "live_12uBcrcKucmZQIPAZyBWWlMpacyah9xoh4vN32Sp6zZCyrhFDfUHWjtxtH5Uuarw")
                    .build();
            Response response = client.newCall(request).execute();

        }catch (IOException e){
            System.out.println(e);
        }

    }

}
