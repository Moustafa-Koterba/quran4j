package quran4j.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ayat")
@Getter
@Setter
public class AyatDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int index;
    @Column(columnDefinition = "TEXT")
    private String text;
    @ManyToOne
    private SurahDomain surah;
}
