package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "geocoder_place_request_by_query", schema = "public")
public class PlaceRequestByQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "query", nullable = false, unique = true)
    private String query;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place", referencedColumnName = "id", nullable = false, unique = false)
    private Place place;
}
