package cl.fintual;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... arg) throws UnirestException {
        if(arg.length != 2){
            throw new IllegalArgumentException("Debe ingresar la fecha desde y hasta");
        }
        Portafolio portafolio = Portafolio.builder().stocks(inicializarStocks()).build();

        try {
            LocalDate desde = LocalDate.parse(arg[0]);
            LocalDate hasta = LocalDate.parse(arg[1]);
            if(esFinDeSemana(desde) || esFinDeSemana(hasta)){
                throw new IllegalArgumentException("Una de las fechas es fin de semana, ingrese dias habiles");
            }
            portafolio.profit(desde, hasta);
        }catch (DateTimeException e){
            System.out.println("Las fechas deben estar en formato yyyy-MM-dd");
        }
    }

    private static List<Stock> inicializarStocks() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(Stock.builder().simbolo(Simbolo.AAPL).build());
        stocks.add(Stock.builder().simbolo(Simbolo.GOOG).build());
        stocks.add(Stock.builder().simbolo(Simbolo.FB).build());
        stocks.add(Stock.builder().simbolo(Simbolo.AMZN).build());
        stocks.add(Stock.builder().simbolo(Simbolo.TSLA).build());
        stocks.add(Stock.builder().simbolo(Simbolo.TWTR).build());
        stocks.add(Stock.builder().simbolo(Simbolo.BBY).build());
        stocks.add(Stock.builder().simbolo(Simbolo.NFLX).build());
        return stocks;
    }

    private static boolean esFinDeSemana(LocalDate fecha) {
        DayOfWeek dia = fecha.getDayOfWeek();
        return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
    }
}
