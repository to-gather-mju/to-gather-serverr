package com.example.togather.domain.place;

import com.example.togather.domain.meeting.Meeting;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    private String placeName;

    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;


}
