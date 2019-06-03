package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

//implemento el controlador para la skin se la asigno desde el scene builder
public class Controller implements Initializable {


    /*declaro los labels que contrendan la hora los minutos y los segundos*/
    @FXML Label labelHoras;
    @FXML Label labelMinutos;
    @FXML Label labelSegundos;
    @FXML Label labelFecha; //declaro uno para la fecha opcional

    int hora, minutos, segundos;

    @Override
    public void initialize(URL location, ResourceBundle resources) { //se inicia cuando la ventana se muetras funciona como constructor
        Calendar cal = new GregorianCalendar(); //instancio el calendario
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); //instacion el date format par para la fecha
        Date date = new Date(); //obtengo la fecha

        hora = cal.get(Calendar.HOUR_OF_DAY); //obtengo la hora de la computadora y se lo asigno a la variable hora
        minutos = cal.get(Calendar.MINUTE); //obtengo los minutos de la computadora y se lo asigno a la variable minuto
        segundos = cal.get(Calendar.SECOND); //obtenfo los segundos de la computadora y se lo asigno a la variable segundo

        /*Los muestro en los labels a los que corresponden*/
        labelHoras.setText(String.valueOf(hora));
        labelMinutos.setText(String.valueOf(minutos));
        labelSegundos.setText(String.valueOf(segundos));
        labelFecha.setText(dateFormat.format(date));


        //declaro el hilo que refrecara los labels con sus nuevos valores
        Thread hilo = new Thread(){
            @Override
            public void run(){
                while (true){ //el siclo sera infinito hasta que se cierre la ventana
                    segundos+=1; //se aunmente en unos los segundos

                    /*se obtiene el formato de la fecha para refrescar el label de fecha*/
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date = new Date();

                    //se utiliza esto para poder refrescar los valores de los componentes en javafx
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //se refrescan los labels con los nuevos valores
                            labelHoras.setText(String.valueOf(hora));
                            labelMinutos.setText(String.valueOf(minutos));
                            labelSegundos.setText(String.valueOf(segundos));
                            labelFecha.setText(dateFormat.format(date));
                        }
                    });



                    if(segundos > 60){ // si segundos llega a 60 pasa a ser 0 y los minutos aumentan
                        segundos = 0;
                        minutos += 1;
                    }

                    if (minutos > 60){ //si minutos es mayor a 60 se reinicia y aumenta en 1 a las horas
                        minutos = 0;
                        hora+=1;
                    }

                    if(hora > 23){ //si es mayor 23 que son las horas que tiene un dia pasará a ser 0
                        hora=0;
                    }

                    try {
                        //le doy el tiempo en que dormirá el hilo
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        //activamos el daemon
        hilo.setDaemon(true);

        //corremos el hilo para que refresque el reloj
        hilo.start();

    }
}
