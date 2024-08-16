package quran4j.library.ayat.persistence.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import quran4j.library.surah.persistence.domain.SurahDomain;

@Entity
@Table(name = "ayat")
@Getter
@Setter
public class AyatDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int index;
    private String text;
    @ManyToOne
    @JoinColumn(name="fk_surah_id")
    private SurahDomain surah;
}
