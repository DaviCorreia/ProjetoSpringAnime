package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
@DataJpaTest
@DisplayName("Tests For Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates anime when Successful")
    void save_PersistAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        Assertions.assertThat(animeSaved).isNotNull(); //verificar se ele foi retornado
        Assertions.assertThat(animeSaved.getId()).isNotNull();//se tem um ID
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());//se o valor salvo foi igual ao que pedi pra ser salvo


    }

    private Anime createAnime(){
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();
    }

}