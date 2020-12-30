package cl.fintual;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Portafolio {


    final List<Stock> stocks;

    public void profit(LocalDate desde, LocalDate hasta) throws UnirestException {
        DecimalFormat df = new DecimalFormat("#.####");
        System.out.println("Fecha Desde: "+desde+" Fecha Hasta: "+hasta);
        for (Stock s : stocks) {
            double valorInicial = s.price(desde);
            double valorFinal = s.price(hasta);
            double profit = (valorFinal - valorInicial) / valorInicial;

            System.out.println(s.getSimbolo().getNombre() + " profit: " + df.format(profit*100) + " annualized return: " + df.format(calculaAnualizado(desde, hasta, valorInicial, valorFinal)*100));
        }
    }

    private double calculaAnualizado(LocalDate desde , LocalDate hasta, double valorInicial, double valorFinal) {
        long dias = ChronoUnit.DAYS.between(desde, hasta);
        double r = (valorFinal / valorInicial) - 1;
        return (Math.pow(1+r, (365.0 / dias)) - 1);
    }
}
