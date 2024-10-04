package com.aproyectousco.appbaucher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class Baucher extends AppCompatActivity {

    private TextView TvReciboValue;
    private TextView tvTipoCuenta;
    private TextView tvTERValue;
    private TextView tvTRXValue;
    private TextView tvCriptogramaValue;
    private TextView tvCompraNetaValue;
    private TextView tvTarjeta;
    private TextView tvTarjetaValue;
    private TextView tvTotalCOPValue;
    private TextView tvReferenciaValue;
    private TextView tvAutorizacionValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baucher);

        // Inicializar los TextViews
        TvReciboValue = findViewById(R.id.tvReciboValue);
        tvTipoCuenta = findViewById(R.id.tvTipoCuenta);
        tvTERValue = findViewById(R.id.tvTERValue);
        tvTRXValue = findViewById(R.id.tvTRXValue);
        tvCriptogramaValue = findViewById(R.id.tvCriptogramaValue);
        tvCompraNetaValue = findViewById(R.id.tvCompraNetaValue);
        tvTarjeta = findViewById(R.id.tvTarjeta);
        tvTarjetaValue = findViewById(R.id.tvTarjetaValue);
        tvTotalCOPValue = findViewById(R.id.tvTotalCOPValue);
        tvReferenciaValue = findViewById(R.id.tvVReferenciaValue);
        tvAutorizacionValue = findViewById(R.id.tvAutorizacionValue);

        //Generar numero de Recibo Aleatorio
        TvReciboValue.setText(String.valueOf(generateRandomReciboValue()));

        // Generar valor hexadecimal aleatorio
        String criptogramaAleatorio = generarHexadecimalAleatorio(16);
        tvCriptogramaValue.setText(criptogramaAleatorio);

        // Generar valor de TER aleatorio
        String cuAleatorio = generarTERAleatorio();
        tvTERValue.setText(cuAleatorio);

        // Generar valor de TRX aleatorio
        String trxAleatorio = generarTRXAleatorio();
        tvTRXValue.setText(trxAleatorio);

        // Generar valor de Referencia Aleatorio
        tvReferenciaValue.setText(generateRandomReference());

        //Generar valor de AUT aleatorio
        tvAutorizacionValue.setText(generarTRXAleatorio());

        //Generar Tarjeta Aleatorio
        tvTarjeta.setText(generateRandomCard());
        tvTarjetaValue.setText(generateRandomCardNumber());
        tvTipoCuenta.setText(chooseRandomAccountType());


        // Obtener el precio enviado
        String precioStr = getIntent().getStringExtra("PRECIO");

        // Mostrar el precio en formato de moneda
        if (precioStr != null) {
            try {
                // Convertir el precio a un número (double)
                double precio = Double.parseDouble(precioStr);

                // Redondear el precio a un entero
                long precioRedondeado = Math.round(precio);

                // Crear un DecimalFormatSymbols para definir el separador de miles como punto
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.'); // Definir punto como separador de miles

                // Crear un DecimalFormat para formatear el precio sin el símbolo de moneda
                DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
                decimalFormat.setGroupingUsed(true); // Activar separación de miles

                // Mostrar el precio en el TextView con el formato deseado (sin símbolo de moneda)
                String precioFormateado = decimalFormat.format(precioRedondeado);
                tvCompraNetaValue.setText(precioFormateado);
                tvTotalCOPValue.setText(precioFormateado);
            } catch (NumberFormatException e) {
                tvTotalCOPValue.setText("Precio no válido");
            }
        }






    }

    // Método para generar un valor hexadecimal aleatorio
    private String generarHexadecimalAleatorio(int longitud) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int valor = random.nextInt(16); // Genera un número entre 0 y 15
            sb.append(Integer.toHexString(valor)); // Convierte el número a hexadecimal
        }
        return sb.toString().toUpperCase(); // Convierte a mayúsculas
    }

    // Método para generar un valor de CU
    private String generarTERAleatorio() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder("000B1L"); // Secuencia base

        // Generar un carácter aleatorio: número (0-9) o letra mayúscula (A-Z)
        char caracterAleatorio;
        if (random.nextBoolean()) { // Decide aleatoriamente si será un número o una letra
            // Generar un número aleatorio
            caracterAleatorio = (char) ('0' + random.nextInt(10)); // '0' a '9'
        } else {
            // Generar una letra mayúscula aleatoria
            caracterAleatorio = (char) ('A' + random.nextInt(26)); // 'A' a 'Z'
        }

        sb.append(caracterAleatorio); // Agregar el carácter aleatorio al final
        return sb.toString(); // Devolver la cadena resultante
    }

    // Método para generar un valor de TRX aleatorio
    private String generarTRXAleatorio() {
        Random random = new Random();
        // Generar un número aleatorio de 6 cifras
        int numeroAleatorio = 100000 + random.nextInt(900000); // Asegura que tenga 6 cifras
        return String.valueOf(numeroAleatorio); // Devuelve el número como cadena
    }

    public String generateRandomReference() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder randomReference = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            randomReference.append(characters.charAt(index));
        }

        return randomReference.toString();
    }

    public String generateRandomCard() {
        String[] cardTypes = {"MASTERCARD", "VISA"};
        Random random = new Random();
        return cardTypes[random.nextInt(cardTypes.length)];
    }

    public String generateRandomCardNumber() {
        Random random = new Random();
        int cardNumber = random.nextInt(10000); // Genera un número entre 0 y 9999
        return String.format("***%04d", cardNumber); // Formatea a 4 dígitos seguido de ***
    }

    // Método para elegir aleatoriamente entre CREDITO y AHORROS
    public String chooseRandomAccountType() {
        Random random = new Random();
        return random.nextBoolean() ? "CRÉDITO" : "AHORROS"; // Devuelve CREDITO o AHORROS
    }

    public int generateRandomReciboValue() {
        Random random = new Random();
        return random.nextInt(900) + 100; // Genera un número entre 100 y 999
    }

}