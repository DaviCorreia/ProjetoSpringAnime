package academy.devdojo.springboot2.Client;

import academy.devdojo.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
        public static void main(String[] args) {
            ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 18);
            log.info(entity);

            Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 18);

            log.info(object);

            Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);

            log.info(Arrays.toString(animes));

            ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    });

            log.info(exchange.getBody());

            Anime samuraiChamploo = Anime.builder().name("Samurai Champloo").build();
            System.out.println(samuraiChamploo.getName());
            ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange("http://localhost:8080/animes/",
                    HttpMethod.POST,
                    new HttpEntity<>(samuraiChamploo,createJsonHearder()),
                    Anime.class);
            log.info("saved anime{}",samuraiChamplooSaved);

            Anime animeToBeUpdate = samuraiChamplooSaved.getBody();
            animeToBeUpdate.setName("Boruto");

            ResponseEntity<Void> samuraiChamplooUpdated = new RestTemplate().exchange("http://localhost:8080/animes/",
                    HttpMethod.PUT,
                    new HttpEntity<>(animeToBeUpdate,createJsonHearder()),
                    Void.class);

            log.info(samuraiChamplooUpdated);

            ResponseEntity<Void> samuraiChamplooDelete = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                    HttpMethod.DELETE,
                    null,
                    Void.class,
                    animeToBeUpdate.getId());

            log.info(samuraiChamplooDelete);
        }

        private static HttpHeaders createJsonHearder(){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            return httpHeaders;
        }
    }


