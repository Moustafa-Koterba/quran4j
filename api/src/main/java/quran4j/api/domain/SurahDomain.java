package quran4j.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "surah", uniqueConstraints = { @UniqueConstraint(columnNames = { "index", "locale" }) })
@Getter
@Setter
public class SurahDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int index;
    private String title;
    private Locale locale;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "surah", cascade = CascadeType.PERSIST)
    private List<AyatDomain> ayats;
}
