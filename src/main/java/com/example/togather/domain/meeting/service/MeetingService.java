package com.example.togather.domain.meeting.service;

import com.example.togather.domain.meeting.dto.MeetingDto;
import com.example.togather.domain.meeting.entity.Meeting;
import com.example.togather.domain.meeting.repository.MeetingRepository;
import com.example.togather.domain.place.entity.Place;
import com.example.togather.domain.place.repository.PlaceRepository;
import com.example.togather.exception.NotFoundException;
import com.example.togather.exception.errorcode.ErrorCode;
import com.example.togather.global.dto.ErrorResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final PlaceRepository placeRepository;

    public MeetingService(MeetingRepository meetingRepository, PlaceRepository placeRepository) {
        this.meetingRepository = meetingRepository;
        this.placeRepository = placeRepository;
    }

    public Meeting createMeeting(MeetingDto meetingDto) {
        Meeting meeting = dtoToMeeting(meetingDto);
        List<Place> places = new ArrayList<>();

        for (int i = 0; i < meetingDto.getPlaces().size(); i++) {
            Place place = new Place();
            place.setPlaceName(meetingDto.getPlaces().get(i));
            place.setMeeting(meeting);
            places.add(place);
        }
        meeting.setPlaces(places);
        meetingRepository.save(meeting);
        placeRepository.saveAll(places);
        return meeting;
    }

    private Meeting dtoToMeeting(MeetingDto meetingDto) {
        Meeting meeting = new Meeting();
        meeting.setMeetingTitle(meetingDto.getMeetingTitle());
        meeting.setStartDate(meetingDto.getStartDate());
        meeting.setEndDate(meetingDto.getEndDate());
        meeting.setStartTime(meetingDto.getStartTime());
        meeting.setEndTime(meetingDto.getEndTime());
        return meeting;
    }

    public Meeting checkMeeting(UUID id) {
        Optional<Meeting> meeting = meetingRepository.findByMeetingId(id);
        if (meeting.isEmpty()) {
            throw new NotFoundException(ErrorCode.MEETING_NOT_FOUND);
        }
        return meeting.get();
    }
}
