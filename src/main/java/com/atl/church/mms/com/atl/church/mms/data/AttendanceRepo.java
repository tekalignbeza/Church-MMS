package com.atl.church.mms.com.atl.church.mms.data;

import com.atl.church.mms.com.atl.church.mms.domain.Attendance;
import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance, Long>  {

	List<Attendance> findByMeetingId(Long id);
}
