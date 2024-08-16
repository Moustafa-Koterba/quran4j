package quran4j.library.surah.persistence.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import quran4j.library.ayat.persistence.domain.AyatDomain;
import quran4j.library.surah.business.model.Origin;

import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "surah")
@Getter
@Setter
public class SurahDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int index;
    private String title;
    private Locale locale;
    @Enumerated(EnumType.STRING)
    private Origin origin;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "surah", cascade = CascadeType.PERSIST)
    private List<AyatDomain> ayats;
}
