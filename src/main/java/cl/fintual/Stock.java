package cl.fintual;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class Stock {

    Simbolo simbolo;

    public double price(LocalDate fecha) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("from", fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        parameters.put("to", fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        parameters.put("apikey", "9ffdeaa0eab0a352e85bca09af307df1");
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get("https://financialmodelingprep.com/api/v3/historical-price-full/" + simbolo)
                .queryString(parameters)
                .asJson();
        JsonNode body = jsonNodeHttpResponse.getBody();
        Object historical = body.getObject().getJSONArray("historical").get(0);
        return ((JSONObject) historical).getDouble("close");
    }
}
