package com.example.SitPass.service.impl;

import com.example.SitPass.dto.ExerciseDto;
import com.example.SitPass.dto.FacilityDto;
import com.example.SitPass.exception.BadRequestException;
import com.example.SitPass.exception.NotFoundException;
import com.example.SitPass.model.Exercise;
import com.example.SitPass.model.Facility;
import com.example.SitPass.model.User;
import com.example.SitPass.model.WorkDay;
import com.example.SitPass.repository.ExerciseRepository;
import com.example.SitPass.repository.FacilityRepository;
import com.example.SitPass.repository.UserRepository;
import com.example.SitPass.service.ExerciseService;
import com.example.SitPass.service.FacilityService;
import com.example.SitPass.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.SitPass.dto.ExerciseDto.convertToDto;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final FacilityService facilityService;
    private final FacilityRepository facilityRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    @Override
    public List<ExerciseDto> viewAll() {

        return null;
    }

    @Override
    public List<ExerciseDto> findByUser(Long id) {

        List<Exercise> exercises = exerciseRepository.findByUser(id);


        return exercises.stream().map(ExerciseDto::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ExerciseDto create(ExerciseDto exerciseDto, Long facilityId, String email) {

        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new NotFoundException(String.format("Facility with id %s not found.", facilityId)));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("User with email %s not found.", email)));


        validateExerciseTime(exerciseDto, facility);

        exerciseDto.setFacility(facility);
        exerciseDto.setUser(user);

        ExerciseDto savedExercise = convertToDto(exerciseRepository.save(exerciseDto.convertToModel()));
        return savedExercise;
    }

    @Override
    public void validateExerciseTime(ExerciseDto exerciseDto, Facility facility) {
        LocalDateTime from = exerciseDto.getFromDate();
        LocalDateTime until = exerciseDto.getUntilDate();

        if (from.isAfter(until)) {
            throw new BadRequestException("The 'from' time must be before the 'until' time.");
        }
        DayOfWeek dayOfWeek = from.getDayOfWeek();
        List<WorkDay> workDayList = facility.getWorkDays();

        WorkDay workDay = workDayList.stream().filter(wd -> wd.getDay().toString().toUpperCase().equals(dayOfWeek.toString().toUpperCase()))
                .findFirst().orElseThrow(() -> new BadRequestException("Facility is closed on" + dayOfWeek));

        LocalTime startTime = workDay.getStartDate();
        LocalTime endTime = workDay.getEndDate();

        if (from.toLocalTime().isBefore(startTime) || until.toLocalTime().isAfter(endTime)) {
            throw new BadRequestException("Exercise time is outside the facility's working hours on" + dayOfWeek);
        }

    }
}

