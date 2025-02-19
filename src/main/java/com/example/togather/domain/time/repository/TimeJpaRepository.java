package com.example.togather.domain.time.repository;

import com.example.togather.domain.meeting.entity.Meeting;
import com.example.togather.domain.time.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeJpaRepository extends JpaRepository<Time, Long> {
    public List<Time> findAllByMeeting(Meeting meeting);
}
