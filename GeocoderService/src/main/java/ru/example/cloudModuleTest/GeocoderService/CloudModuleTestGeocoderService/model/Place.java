package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.dto.NominatimPlace;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "geocoder_place", schema = "public")
@SuppressWarnings("MagicNumber")
public class Place {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 250, nullable = false, unique = false)
    private String name;
    @Column(name = "lat", nullable = false, unique = false)
    private Double lat;
    @Column(name = "lon", nullable = false, unique = false)
    private Double lon;

    public static Place of(final NominatimPlace np) {
        return new Place(np.id(), np.displayName(), np.lat(), np.lon());
    }
}
