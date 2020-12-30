package cl.fintual;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Simbolo {


    GOOG("Google"),
    AAPL("Apple"),
    FB("Facebook"),
    AMZN("Amazon"),
    TWTR("Twitter"),
    TSLA("Tesla"),
    BBY("Best Buy"),
    NFLX("Netflix");
    String nombre;

}
