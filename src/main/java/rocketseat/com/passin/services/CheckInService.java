package rocketseat.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.checkin.Checkin;
import rocketseat.com.passin.domain.checkin.exceptions.CheckedInAlreadyExistsException;
import rocketseat.com.passin.repositories.CheckinRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckinRepository checkinRepository;

    public void registerCheckIn(Attendee attendee){
        this.verifyCheckInExists(attendee.getId());

        Checkin newCheckIn = new Checkin();
        newCheckIn.setAttendee(attendee);
        newCheckIn.setCreatedAt(LocalDateTime.now());

        this.checkinRepository.save(newCheckIn);

    }

    private void verifyCheckInExists(String attendeId){
        Optional<Checkin> isCheckedIn = this.getCheckIn(attendeId);
        if (isCheckedIn.isPresent()){
            throw new CheckedInAlreadyExistsException("attendee already checked in");
        }
    }

    public Optional<Checkin> getCheckIn(String attendeId){
        return this.checkinRepository.findByAttendeeId(attendeId);
    }

}
