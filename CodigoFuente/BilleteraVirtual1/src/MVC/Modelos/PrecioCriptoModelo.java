package MVC.Modelos;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class PrecioCriptoModelo {
    private static final String URL_API = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";

    public JSONObject obtenerPreciosCripto() throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .GET()
                .build();
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() == 200) {
            return new JSONObject(respuesta.body());
        } else {
            throw new IOException("Error al consultar la API. Código: " + respuesta.statusCode());
        }
    }
}